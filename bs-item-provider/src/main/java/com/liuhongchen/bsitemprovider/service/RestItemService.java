package com.liuhongchen.bsitemprovider.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liuhongchen.bscommondto.vo.GoodsVo;
import com.liuhongchen.bscommonmodule.pojo.Book;
import com.liuhongchen.bscommonmodule.pojo.Goods;
import com.liuhongchen.bscommonutils.common.EmptyUtils;
import com.liuhongchen.bscommonutils.common.IdWorker;
import com.liuhongchen.bsitemprovider.mapper.BookMapper;
import com.liuhongchen.bsitemprovider.mapper.GoodsMapper;
import com.liuhongchen.bsitemprovider.mapper.GoodsVoMapper;
import com.liuhongchen.bsitemprovider.utils.HttpUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RestItemService {


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
    private BookMapper bookMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsVoMapper goodsVoMapper;

    @Autowired
    private EsBookService esBookService;


    @RequestMapping(value = "/isbn", method = RequestMethod.POST)
//    public Integer isbn()throws Exception{
    public Book isbn(@RequestParam("isbn") String isbn) throws Exception {
//        String isbn="9787040205497";
        if (EmptyUtils.isEmpty(isbn)) {
            return null;
        }
        Book book = (isbn.length() == 10) ? bookMapper.getBookByIsbn10(isbn) : bookMapper.getBookByIsbn13(isbn);
        if (book != null) {
            book.setPubdate(null);
            esBookService.saveBook(book);
            return book;
        }
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("isbn", isbn);

        try {

            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());
            //获取response的body
            HttpEntity entity = response.getEntity();
            String json = EntityUtils.toString(entity);
            JSONObject jsonObject = JSON.parseObject(json);
            JSONObject result = jsonObject.getJSONObject("result");

            book = new Book();
            book.setId(result.getString("isbn"));
            book.setTitle(result.getString("title"));
            book.setSubtitle(result.getString("subtitle"));
            book.setPic(result.getString("pic"));
            book.setAuthor(result.getString("author"));
            book.setPublisher(result.getString("publisher"));
            book.setPubplace(result.getString("pubplace"));
            book.setPubdate(result.getString("pubdate"));
            book.setPage(result.getInteger("page"));
            book.setPrice(result.getDouble("price"));
            book.setBinding(result.getString("binding"));
            book.setIsbn13(result.getString("isbn"));
            book.setIsbn10(result.getString("isbn10"));
            book.setKeyword(result.getString("keyword"));
            book.setEdition(result.getString("edition"));
            book.setImpression(result.getString("impression"));
            book.setLanguage(result.getString("language"));

            Integer integer = bookMapper.insertBook(book);

            book.setPubdate(null);

            esBookService.saveBook(book);

            if (integer == 1) {
                return book;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<GoodsVo> search(@RequestParam("title") String title) throws Exception {
        List<Book> books = esBookService.searchBookByTitle(title);
        System.out.println(books);
        if (EmptyUtils.isEmpty(books)) return null;
        List<String> ids=new ArrayList<>();
        for (Book book : books) {
            if (ids.contains(book.getId())) continue;
            ids.add(book.getId());
        }
        List<GoodsVo> goodsVoList = new ArrayList<>();
        for (String id : ids) {
            goodsVoList.add(goodsVoMapper.getGoodsByBookId(id));
        }

        return goodsVoList;
    }


    @RequestMapping(value = "/createGoods", method = RequestMethod.POST)
    public Goods createGoods(@RequestBody Goods goods) throws Exception {


        String goodsId = IdWorker.getId();
        goods.setId(goodsId);
        goodsMapper.insertGoods(goods);
        return goods;
    }

    @RequestMapping(value = "/getGoodsStatus", method = RequestMethod.POST)
    public Integer getGoodsStatus(@RequestParam("id") String id) throws Exception {
        return goodsMapper.getGoodsById(id).getStatus();

    }


    @RequestMapping(value = "/getGoodsById", method = RequestMethod.POST)
    public Goods getGoodsById(@RequestParam("id") String id) throws Exception {
        return goodsMapper.getGoodsById(id);
    }

    @RequestMapping(value = "/getBookById", method = RequestMethod.POST)
    public Book getBookById(@RequestParam("id") String id) throws Exception {

        return bookMapper.getBookById(id);
    }

    @RequestMapping(value = "/getGoodsVoBySellerId", method = RequestMethod.POST)
    public List<GoodsVo> getGoodsBySellerId(@RequestParam("id") String id) throws Exception {

        Map<String, Object> params = new HashMap<>();
        params.put("sellerId", id);
        return goodsVoMapper.getGoodsListByMap(params);
    }

    @RequestMapping(value = "/getGoodsVoByBuyerId", method = RequestMethod.POST)
    public List<GoodsVo> getGoodsByBuyerId(@RequestParam("id") String id) throws Exception {

        Map<String, Object> params = new HashMap<>();
        params.put("buyerId", id);
        return goodsVoMapper.getGoodsListByMap(params);
    }

    @RequestMapping(value = "/getGoodsVoByBuyerIdAndStatus", method = RequestMethod.POST)
    public List<GoodsVo> getGoodsVoByBuyerIdAndStatus(@RequestParam("id") String id
            , @RequestParam("status") Integer status) throws Exception {

        Map<String, Object> params = new HashMap<>();
        params.put("buyerId", id);
        params.put("status", status);
        return goodsVoMapper.getGoodsListByMap(params);
    }

    @RequestMapping(value = "/getGoodsVoById", method = RequestMethod.POST)
    public GoodsVo getGoodsVoById(@RequestParam("id") String id) throws Exception {
        return goodsVoMapper.getGoodsById(id);
    }

    @RequestMapping(value = "/getNewGoodsVo", method = RequestMethod.POST)
    public List<GoodsVo> getNewGoodsVo() throws Exception {
        return goodsVoMapper.getNewGoodsVo();
    }

    @RequestMapping(value = "/deleteGoods", method = RequestMethod.POST)
    public Integer deleteGoods(@RequestParam("id") String id) throws Exception {
        final InterProcessMutex mutex = new InterProcessMutex(curatorFramework, "/goods/" + id);
        try {
            mutex.acquire();
            return goodsMapper.deleteGoodsById(id);
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

    @RequestMapping(value = "/getGoodsVoByTypeId", method = RequestMethod.POST)
    public List<GoodsVo> getGoodsVoByTypeId(@RequestParam("typeId") Integer typeId) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("typeId", typeId);
        return goodsVoMapper.getGoodsListByMap(params);
    }

    @RequestMapping(value = "/getGoodsVoByTypeIdAndStatus", method = RequestMethod.POST)
    public List<GoodsVo> getGoodsVoByTypeIdAndStatus(@RequestParam("typeId") Integer typeId,
                                                     @RequestParam("status") Integer status) throws Exception {
        Map<String, Object> params = new HashMap<>();
        if (typeId != 0) params.put("typeId", typeId);
        params.put("status", status);
        return goodsVoMapper.getGoodsListByMap(params);
    }

    @RequestMapping(value = "/getAllGoodsVo", method = RequestMethod.POST)
    public List<GoodsVo> getAllGoodsVo() throws Exception {
        return goodsVoMapper.getAllGoodsVo();
    }

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public Integer createOrder(@RequestBody Goods goods) throws Exception {
        final InterProcessMutex mutex = new InterProcessMutex(curatorFramework, "/goods/" + goods.getId());
        try {
            mutex.acquire();
            Integer goodsStatus = this.getGoodsStatus(goods.getId());
            if (goodsStatus != 1) throw new Exception("已被秒杀");
            return goodsMapper.updateGoods(goods);
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
        try {
            mutex.acquire();
            return goodsMapper.updateGoods(goods);
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
