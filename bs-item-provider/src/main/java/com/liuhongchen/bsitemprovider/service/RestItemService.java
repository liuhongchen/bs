package com.liuhongchen.bsitemprovider.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liuhongchen.bscommondto.vo.GoodsVo;
import com.liuhongchen.bscommonmodule.pojo.Book;
import com.liuhongchen.bscommonmodule.pojo.Goods;
import com.liuhongchen.bscommonmodule.pojo.User;
import com.liuhongchen.bscommonutils.common.Constants;
import com.liuhongchen.bscommonutils.common.EmptyUtils;
import com.liuhongchen.bsitemprovider.mapper.BookMapper;
import com.liuhongchen.bsitemprovider.mapper.GoodsMapper;
import com.liuhongchen.bsitemprovider.mapper.GoodsVoMapper;
import com.liuhongchen.bsitemprovider.utils.HttpUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RestItemService {


    String host = "https://jisuisbn.market.alicloudapi.com";
    String path = "/isbn/query";
    String method = "GET";


    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsVoMapper goodsVoMapper;

    @RequestMapping(value = "/isbn", method = RequestMethod.POST)
//    public Integer isbn()throws Exception{
    public Book isbn(@RequestParam("isbn") String isbn) throws Exception {
//        String isbn="9787040205497";
        if (EmptyUtils.isEmpty(isbn)) {
            return null;
        }
        Book book = (isbn.length() == 10) ? bookMapper.getBookByIsbn10(isbn) : bookMapper.getBookByIsbn13(isbn);
        if (book != null) return book;
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
            System.out.println(json);
            JSONObject jsonObject = JSON.parseObject(json);
            JSONObject result = jsonObject.getJSONObject("result");

            book = new Book();
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

    @RequestMapping(value = "/createGoods", method = RequestMethod.POST)
    public Goods createGoods(@RequestBody Goods goods) throws Exception {
        goodsMapper.insertGoods(goods);
        return goods;
    }

    @RequestMapping(value = "/getGoodsStatus", method = RequestMethod.POST)
    public Integer getGoodsStatus(@RequestParam("id") Integer id) throws Exception {
        return goodsMapper.getGoodsById(Long.valueOf(id)).getStatus();

    }
    @RequestMapping(value = "/getGoodsById", method = RequestMethod.POST)
    public Goods getGoodsById(@RequestParam("id") Integer id) throws Exception {
        return goodsMapper.getGoodsById(Long.valueOf(id));
    }
    @RequestMapping(value = "/getBookById", method = RequestMethod.POST)
    public Book getBookById(@RequestParam("id") Integer id) throws Exception {

        return bookMapper.getBookById(Long.valueOf(id));
    }

    @RequestMapping(value = "/getGoodsVoBySellerId", method = RequestMethod.POST)
    public List<GoodsVo> getGoodsBySellerId(@RequestParam("id") Integer id) throws Exception {

        Map<String,Object> params=new HashMap<>();
        params.put("sellerId",id);
        return goodsVoMapper.getGoodsListByMap(params);
    }

    @RequestMapping(value = "/getGoodsVoByBuyerId", method = RequestMethod.POST)
    public List<GoodsVo> getGoodsByBuyerId(@RequestParam("id") Integer id) throws Exception {

        Map<String,Object> params=new HashMap<>();
        params.put("buyerId",id);
        return goodsVoMapper.getGoodsListByMap(params);
    }

    @RequestMapping(value = "/getGoodsVoById", method = RequestMethod.POST)
    public GoodsVo getGoodsVoById(@RequestParam("id") Integer id) throws Exception {
        return goodsVoMapper.getGoodsById(id);
    }

    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    public Integer cancelOrder(@RequestParam("id") Integer id) throws Exception {
        Goods goods=new Goods();
        goods.setId(id);
        goods.setBuyerId(-1);
        goods.setStatus(1);
        //TODO:退钱操作
        return goodsMapper.updateGoods(goods);
    }
















    private String appcode = "6019f09f9e6341589895c441222a128f";
}
