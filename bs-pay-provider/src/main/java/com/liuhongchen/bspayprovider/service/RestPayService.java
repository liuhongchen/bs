package com.liuhongchen.bspayprovider.service;

import com.liuhongchen.bscommonmodule.pojo.Money;
import com.liuhongchen.bscommonmodule.pojo.MoneyLog;
import com.liuhongchen.bscommonutils.common.IdWorker;
import com.liuhongchen.bscommonutils.common.LogUtils;
import com.liuhongchen.bspayprovider.mapper.MoneyLogMapper;
import com.liuhongchen.bspayprovider.mapper.MoneyMapper;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by liuhongchen
 * 所有核心业务逻辑都写在这里，尽量让consumer只进行请求转发，而不计算
 */
@RestController
public class RestPayService {

    @Autowired
    private MoneyMapper moneyMapper;

    @Autowired
    private MoneyLogMapper moneyLogMapper;

    @Autowired
    private LogUtils logUtils;
    /*
     * 定义重试策略：等待2秒,重试10次
     * 第一个参数：等待时间
     * 第二个参数：重试次数
     */
   public static RetryPolicy policy = new ExponentialBackoffRetry(2000, 10);

    /*
     * 创建客户端向zookeeper请求锁
     * connectString() : zookeeper地址
     * retryPolicy() : 重试策略
     */
    public static CuratorFramework curatorFramework =
            CuratorFrameworkFactory.builder().connectString("192.168.1" +
                    ".180").retryPolicy(policy).build();


    static {
        curatorFramework.start();
    }

    @RequestMapping(value = "/createOrder",method = RequestMethod.POST)
    public Integer createOrder(@RequestParam("userId") String userId,
                       @RequestParam("num") Double num,
                       @RequestParam("goodsName") String goodsName,
                       @RequestParam("goodsId") String goodsId) throws Exception {

        Money money=new Money();
        money.setMoney(num);
        money.setId(userId);
        money.setPaid(num);

        final InterProcessMutex mutex = new InterProcessMutex(curatorFramework, "/money/"+userId);
        try {
            mutex.acquire();
            moneyMapper.createOrder(money);
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("pay_provider_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            throw e;
        }finally {
            try {
                mutex.release();
            } catch (Exception e) {
                logUtils.i("pay_provider_exception"
                        , DateFormat.getDateInstance().format(new Date())+"--"
                                +Thread.currentThread() .getStackTrace()[1].getMethodName()
                                +e.getMessage());
                e.printStackTrace();
            }
        }

        MoneyLog moneyLog=new MoneyLog();
        moneyLog.setId(IdWorker.getId());
        moneyLog.setUserId(userId);
        moneyLog.setType(0);
        moneyLog.setNum(num);
        moneyLog.setName("购买 "+goodsName);
        moneyLog.setGoodsId(goodsId);
        moneyLog.setTime(new Date());
        moneyLogMapper.log(moneyLog);
        return 1;
    }

    @Transactional
    @RequestMapping(value = "/cancelOrder",method = RequestMethod.POST)
    public Integer cancelOrder(@RequestParam("userId") String userId,
                               @RequestParam("num") Double num,
                               @RequestParam("goodsName") String goodsName,
                               @RequestParam("goodsId") String goodsId) throws Exception {

        Money money=new Money();
        money.setMoney(num);
        money.setId(userId);
        money.setPaid(num);
        final InterProcessMutex mutex = new InterProcessMutex(curatorFramework, "/money/"+userId);
        try {
            mutex.acquire();
            moneyMapper.cancelOrder(money);
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("pay_provider_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            throw e;
        }finally {
            try {
                mutex.release();
            } catch (Exception e) {
                e.printStackTrace();
                logUtils.i("pay_provider_exception"
                        , DateFormat.getDateInstance().format(new Date())+"--"
                                +Thread.currentThread() .getStackTrace()[1].getMethodName()
                                +e.getMessage());
            }
        }

        MoneyLog moneyLog=new MoneyLog();
        moneyLog.setId(IdWorker.getId());
        moneyLog.setUserId(userId);
        moneyLog.setType(1);
        moneyLog.setNum(num);
        moneyLog.setName("退款 "+goodsName);
        moneyLog.setGoodsId(goodsId);
        moneyLog.setTime(new Date());
        moneyLogMapper.log(moneyLog);

        return 1;
    }

    @Transactional
    @RequestMapping(value = "/finishOrder",method = RequestMethod.POST)
    public Integer finishOrder(@RequestParam("sellerId") String sellerId,
                               @RequestParam("buyerId") String buyerId,
                               @RequestParam("num") Double num,
                               @RequestParam("goodsName") String goodsName,
                               @RequestParam("goodsId") String goodsId) throws Exception {

        //扣买家的paid字段钱
        Money money1=new Money();
        money1.setId(buyerId);
        money1.setPaid(num);


        //给买家money加钱
        Money money2=new Money();
        money2.setId(sellerId);
        money2.setMoney(num);

        final InterProcessMutex mutex1 = new InterProcessMutex(curatorFramework, "/money/"+sellerId);
        final InterProcessMutex mutex2 = new InterProcessMutex(curatorFramework, "/money/"+buyerId);
        try {
            mutex1.acquire();
            mutex2.acquire();
            moneyMapper.trans1(money1);
            moneyMapper.trans2(money2);
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("pay_provider_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            throw e;
        }finally {
            try {
                mutex1.release();
                mutex2.release();
            } catch (Exception e) {
                e.printStackTrace();
                logUtils.i("pay_provider_exception"
                        , DateFormat.getDateInstance().format(new Date())+"--"
                                +Thread.currentThread() .getStackTrace()[1].getMethodName()
                                +e.getMessage());
            }
        }
        moneyMapper.trans1(money1);
        moneyMapper.trans2(money2);

        MoneyLog moneyLog=new MoneyLog();
        moneyLog.setId(IdWorker.getId());
        moneyLog.setUserId(sellerId);
        moneyLog.setType(1);
        moneyLog.setNum(num);
        moneyLog.setName("卖掉 "+goodsName);
        moneyLog.setGoodsId(goodsId);
        moneyLog.setTime(new Date());
        moneyLogMapper.log(moneyLog);

        return 1;
    }


    @Transactional
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Integer add(@RequestParam("userId") String userId,
                       @RequestParam("num") Double num,
                       @RequestParam("goodsName") String goodsName,
                       @RequestParam("goodsId") String goodsId) throws Exception {

        Money money=new Money();
        money.setMoney(num);
        money.setId(userId);
        final InterProcessMutex mutex = new InterProcessMutex(curatorFramework, "/money/"+userId);
        try {
            mutex.acquire();
            moneyMapper.updateMoney(money);
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("pay_provider_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            throw e;
        }finally {
            try {
                mutex.release();
            } catch (Exception e) {
                e.printStackTrace();
                logUtils.i("pay_provider_exception"
                        , DateFormat.getDateInstance().format(new Date())+"--"
                                +Thread.currentThread() .getStackTrace()[1].getMethodName()
                                +e.getMessage());
            }
        }

        MoneyLog moneyLog=new MoneyLog();
        moneyLog.setId(IdWorker.getId());
        moneyLog.setUserId(userId);
        moneyLog.setType(1);
        moneyLog.setNum(num);
        moneyLog.setName(goodsName);
        moneyLog.setGoodsId(goodsId);
        moneyLog.setTime(new Date());
        moneyLogMapper.log(moneyLog);

        return 1;
    }


    @Transactional
    @RequestMapping(value = "/minus",method = RequestMethod.POST)
    public Integer minus(@RequestParam("userId") String userId,
                       @RequestParam("num") Double num,
                       @RequestParam("goodsName") String goodsName,
                       @RequestParam("goodsId") String goodsId) throws Exception {
        Money money=new Money();
        money.setMoney(-num);
        money.setId(userId);
        final InterProcessMutex mutex = new InterProcessMutex(curatorFramework, "/money/"+userId);
        try {
            mutex.acquire();
            moneyMapper.updateMoney(money);
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("pay_provider_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            throw e;
        }finally {
            try {
                mutex.release();
            } catch (Exception e) {
                e.printStackTrace();
                logUtils.i("pay_provider_exception"
                        , DateFormat.getDateInstance().format(new Date())+"--"
                                +Thread.currentThread() .getStackTrace()[1].getMethodName()
                                +e.getMessage());
            }
        }


        MoneyLog moneyLog=new MoneyLog();
        moneyLog.setId(IdWorker.getId());
        moneyLog.setUserId(userId);
        moneyLog.setType(0);
        moneyLog.setNum(num);
        moneyLog.setName(goodsName);
        moneyLog.setGoodsId(goodsId);
        moneyLog.setTime(new Date());

        moneyLogMapper.log(moneyLog);

        return 1;
    }

    @RequestMapping(value = "/createAccount",method = RequestMethod.POST)
    public Integer createAccount(@RequestParam("userId")String userId){
        Double money = moneyMapper.getMoney(userId);
        Integer result=0;
        if (money==null){
            Money newAccount=new Money();
            newAccount.setMoney(0d);
            newAccount.setId(userId);
            newAccount.setPaid(0d);
            result= moneyMapper.insert(newAccount);
        }else{
            result=1;
        }
        return result;

    }


    @RequestMapping(value = "/getMoney",method = RequestMethod.POST)
    public Money getMoney(@RequestParam("userId")String userId){
        Double num = moneyMapper.getMoney(userId);
        Money money=new Money();
        money.setId(userId);
        money.setMoney(num);

        List<MoneyLog> list=moneyLogMapper.getByUserId(userId);
        money.setList(list);
        return money;
    }


    @RequestMapping(value = "/getRestMoney",method = RequestMethod.POST)
    public Double getRestMoney(@RequestParam("id")String id){
       return moneyMapper.getMoney(id);
    }
}
