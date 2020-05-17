package com.liuhongchen.bsitemprovider.service;

import com.alibaba.fastjson.JSONObject;
import com.liuhongchen.bscommondto.vo.GoodsVo;
import com.liuhongchen.bscommonextutils.common.RedisUtils;
import com.liuhongchen.bscommonmodule.pojo.Book;
import com.liuhongchen.bscommonmodule.pojo.Goods;
import com.liuhongchen.bscommonutils.common.EmptyUtils;
import com.liuhongchen.bscommonutils.common.IdWorker;
import com.liuhongchen.bsitemprovider.mapper.BookMapper;
import com.liuhongchen.bsitemprovider.mapper.GoodsMapper;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestGoodsService {


    String host = "https://jisuisbn.market.alicloudapi.com";
    String path = "/isbn/query";
    String method = "GET";
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

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private BookMapper bookMapper;


    @Autowired
    private RedisUtils redisUtils;

    public static final String GOODS_PREFIX = "cache_item_goods:";
    public static final String GOODSVO_PREFIX = "cache_item_goodsvo:";


    @RequestMapping(value = "/createGoods", method = RequestMethod.POST)
    public Goods createGoods(@RequestBody Goods goods) throws Exception {


        String goodsId = IdWorker.getId();
        goods.setId(goodsId);
        Integer result = goodsMapper.insertGoods(goods);
        if (result==1) {
            String key = GOODS_PREFIX + goodsId;
            redisUtils.set(key,JSONObject.toJSONString(goods));
        }
        return goods;
    }

    @RequestMapping(value = "/getGoodsStatus", method = RequestMethod.POST)
    public Integer getGoodsStatus(@RequestParam("id") String id) throws Exception {
        return getGoodsById(id).getStatus();

    }


    @RequestMapping(value = "/getGoodsById", method = RequestMethod.POST)
    public Goods getGoodsById(@RequestParam("id") String id) throws Exception {
        String key=GOODS_PREFIX+id;
        Goods goods;
        if (EmptyUtils.isEmpty(redisUtils.get(key))) {
            goods = goodsMapper.getGoodsById(id);
            redisUtils.set(key,JSONObject.toJSONString(goods));
            return goods;
        }else{
            return JSONObject.parseObject(redisUtils.get(key).toString(),Goods.class);
        }
    }


    @RequestMapping(value = "/deleteGoods", method = RequestMethod.POST)
    public Integer deleteGoods(@RequestParam("id") String id) throws Exception {
        final InterProcessMutex mutex = new InterProcessMutex(curatorFramework, "/goods/" + id);
        String key=GOODS_PREFIX+id;
        String key2=GOODSVO_PREFIX+id;
        try {
            mutex.acquire();
            Integer result = goodsMapper.deleteGoodsById(id);
            if (result==1){
                redisUtils.delete(key);
                redisUtils.delete(key2);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                mutex.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public Integer createOrder(@RequestBody Goods goods) throws Exception {
        final InterProcessMutex mutex = new InterProcessMutex(curatorFramework, "/goods/" + goods.getId());
        String key=GOODS_PREFIX+goods.getId();
        String key2=GOODS_PREFIX+goods.getId();
        try {
            mutex.acquire();
            Integer goodsStatus = this.getGoodsStatus(goods.getId());
            if (goodsStatus != 1) throw new Exception("已被秒杀");
            Integer result = goodsMapper.updateGoods(goods);
            if (result==1){
                goods=goodsMapper.getGoodsById(goods.getId());
                redisUtils.set(key,JSONObject.toJSONString(goods));
                GoodsVo goodsVo=new GoodsVo();
                BeanUtils.copyProperties(goods,goodsVo);
                goodsVo.setBookinfo(bookMapper.getBookById(goods.getBookId()));
                redisUtils.set(key2,JSONObject.toJSONString(goodsVo));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                mutex.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/updateGoods", method = RequestMethod.POST)
    public Integer updateGoods(@RequestBody Goods goods) throws Exception {
        final InterProcessMutex mutex = new InterProcessMutex(curatorFramework, "/goods/" + goods.getId());
        String key=GOODS_PREFIX+goods.getId();
        String key2=GOODSVO_PREFIX+goods.getId();
        try {
            mutex.acquire();
            Integer result = goodsMapper.updateGoods(goods);
            if (result==1){
                goods=goodsMapper.getGoodsById(goods.getId());
                redisUtils.set(key,JSONObject.toJSONString(goods));
                GoodsVo goodsVo=new GoodsVo();
                BeanUtils.copyProperties(goods,goodsVo);
                goodsVo.setBookinfo(bookMapper.getBookById(goods.getBookId()));
                redisUtils.set(key2,JSONObject.toJSONString(goodsVo));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                mutex.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private String appcode = "6019f09f9e6341589895c441222a128f";
}
