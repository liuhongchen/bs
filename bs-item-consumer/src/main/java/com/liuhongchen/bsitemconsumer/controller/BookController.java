package com.liuhongchen.bsitemconsumer.controller;

import com.liuhongchen.bscommondto.common.Dto;
import com.liuhongchen.bscommondto.common.DtoUtil;
import com.liuhongchen.bscommonmodule.pojo.Book;
import com.liuhongchen.bscommonmodule.pojo.Goods;
import com.liuhongchen.bscommonmodule.pojo.Mail;
import com.liuhongchen.bscommonutils.common.EmptyUtils;
import com.liuhongchen.bscommonutils.common.LogUtils;
import com.liuhongchen.bsitemconsumer.client.RestMailClient;
import com.liuhongchen.bsitemconsumer.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.Date;

/**
 * ClassName:BookController
 * Package:com.liuhongchen.bsitemconsumer.controller
 * Description:
 *
 * @date:2020-04-16 18:35
 * @author:892698613@qq.com
 */

@RestController
public class BookController {



    @Autowired
    private BookService bookService;

    @Autowired
    private RestMailClient mailClient;

    @Autowired
    private LogUtils logUtils;

    @GetMapping("/isbn")
    public Dto isbn(String isbn){
        if (EmptyUtils.isEmpty(isbn)) {
            return DtoUtil.returnFail("isbn为空","0022");
        }
        Book book;
        try {
             book = bookService.isbn(isbn);
        } catch (Exception e) {
            logUtils.i("item_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("isbn失败","0022");
        }
        if (EmptyUtils.isEmpty(book)||EmptyUtils.isEmpty(book.getId())) {
            return DtoUtil.returnFail("isbn查询失败","0022");
        }

        return DtoUtil.returnSuccess("isbn查询成功",book);


    }

    @GetMapping("/getBookById")
    public Dto getBookById(String id){
        if (EmptyUtils.isEmpty(id))return DtoUtil.returnFail("id错误","0022");
        Book book;
        try {
             book = bookService.getBookById(id);
        } catch (Exception e) {
            logUtils.i("item_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("getBookById失败","0022");
        }
        if (book==null)return DtoUtil.returnFail("查询失败","0022");

        return DtoUtil.returnSuccess("查询成功",book);
    }













}
