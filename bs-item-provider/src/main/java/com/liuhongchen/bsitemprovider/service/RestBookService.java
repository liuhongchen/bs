package com.liuhongchen.bsitemprovider.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liuhongchen.bscommonextutils.common.RedisUtils;
import com.liuhongchen.bscommonmodule.pojo.Book;
import com.liuhongchen.bscommonutils.common.EmptyUtils;
import com.liuhongchen.bsitemprovider.mapper.BookMapper;
import com.liuhongchen.bsitemprovider.utils.HttpUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RestBookService {


    String host = "https://jisuisbn.market.alicloudapi.com";
    String path = "/isbn/query";
    String method = "GET";

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private EsBookService esBookService;

    @Autowired
    private RedisUtils redisUtils;

    public static final String BOOK_PREFIX = "cache_item_book:";


    @RequestMapping(value = "/isbn", method = RequestMethod.POST)
    public Book isbn(@RequestParam("isbn") String isbn) throws Exception {
//        String isbn="9787040205497";
        if (EmptyUtils.isEmpty(isbn)) {
            return null;
        }
        String key = BOOK_PREFIX + isbn;
        Book book;
        if (EmptyUtils.isEmpty(redisUtils.get(key))) {
            book = bookMapper.getBookByIsbn13(isbn);
        } else {
            book = JSONObject.parseObject(redisUtils.get(key).toString(), Book.class);
        }

        if (!EmptyUtils.isEmpty(book)) {
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

            if (integer == 1) redisUtils.set(key, JSONObject.toJSONString(book));
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


    @RequestMapping(value = "/getBookById", method = RequestMethod.POST)
    public Book getBookById(@RequestParam("id") String id) throws Exception {
        String key=BOOK_PREFIX+id;
        Book book;
        if (EmptyUtils.isEmpty(redisUtils.get(key))) {
            book = bookMapper.getBookById(id);
            redisUtils.set(key,JSONObject.toJSONString(book));
            return book;
        }else{
            return JSONObject.parseObject(redisUtils.get(key).toString(),Book.class);
        }
    }



    private String appcode = "6019f09f9e6341589895c441222a128f";
}
