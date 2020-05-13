package com.liuhongchen.bspayprovider.service;

import com.liuhongchen.bscommonmodule.pojo.Money;
import com.liuhongchen.bscommonmodule.pojo.MoneyLog;
import com.liuhongchen.bscommonmodule.pojo.User;
import com.liuhongchen.bscommonutils.common.EmptyUtils;
import com.liuhongchen.bscommonutils.common.IdWorker;
import com.liuhongchen.bspayprovider.mapper.MoneyLogMapper;
import com.liuhongchen.bspayprovider.mapper.MoneyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @Transactional
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Integer add(@RequestParam("userId") Integer userId,
                       @RequestParam("num") Double num,
                       @RequestParam("goodsName") String goodsName,
                       @RequestParam("goodsId") Integer goodsId) throws Exception {

        Money money=new Money();
        money.setMoney(num);
        money.setUserId(userId);

        moneyMapper.updateMoney(money);

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
    public Integer minus(@RequestParam("userId") Integer userId,
                       @RequestParam("num") Double num,
                       @RequestParam("goodsName") String goodsName,
                       @RequestParam("goodsId") Integer goodsId) throws Exception {
        Money money=new Money();
        money.setMoney(-num);
        money.setUserId(userId);

        moneyMapper.updateMoney(money);

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
    public Integer createAccount(@RequestParam("userId")Integer userId){
        Double money = moneyMapper.getMoney(Long.parseLong(String.valueOf(userId)));
        Integer result=0;
        if (money==null){
            Money newAccount=new Money();
            newAccount.setMoney(0d);
            newAccount.setUserId(userId);
            result= moneyMapper.insert(newAccount);
        }else{
            result=1;
        }
        return result;

    }


    @RequestMapping(value = "/getMoney",method = RequestMethod.POST)
    public Money getMoney(@RequestParam("userId")Integer userId){
        Double num = moneyMapper.getMoney(userId.longValue());
        Money money=new Money();
        money.setUserId(userId);
        money.setMoney(num);

        List<MoneyLog> list=moneyLogMapper.getByUserId(userId);
        money.setList(list);
        return money;
    }
}
