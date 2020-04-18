package com.liuhongchen.bsitemconsumer.controller;

import com.liuhongchen.bscommondto.common.Dto;
import com.liuhongchen.bscommondto.common.DtoUtil;
import com.liuhongchen.bscommonmodule.pojo.Book;
import com.liuhongchen.bscommonmodule.pojo.Goods;
import com.liuhongchen.bscommonmodule.pojo.Mail;
import com.liuhongchen.bscommonutils.common.EmptyUtils;
import com.liuhongchen.bsitemconsumer.client.RestMailClient;
import com.liuhongchen.bsitemconsumer.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping("/test")
    public ResponseEntity test(){

        Mail mail=new Mail();
        mail.setTo("892698613@qq.com");
        mail.setTitle("hello rabbitmq email");
        mail.setContent("this is a mail from rabbitmq+springboot----Idea");
        Boolean flag=false;
        try {
            flag=mailClient.sendSimple(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(flag);
    }


    @GetMapping("/isbn")
    public Dto isbn(String isbn){
        if (EmptyUtils.isEmpty(isbn)) {
            return DtoUtil.returnFail("isbn为空","0022");
        }

        Book book = bookService.isbn(isbn);
        if (EmptyUtils.isEmpty(book)||EmptyUtils.isEmpty(book.getId())) {
            return DtoUtil.returnFail("isbn查询失败","0022");
        }

        return DtoUtil.returnSuccess("isbn查询成功",book);


    }

    @GetMapping("/getBookById")
    public Dto getBookById(Integer id){
        if (id==null||id<=0)return DtoUtil.returnFail("id错误","0022");

        Book book = bookService.getBookById(id);
        if (book==null)return DtoUtil.returnFail("查询失败","0022");

        return DtoUtil.returnSuccess("查询成功",book);
    }













}
