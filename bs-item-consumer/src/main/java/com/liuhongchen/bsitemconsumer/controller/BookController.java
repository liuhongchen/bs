package com.liuhongchen.bsitemconsumer.controller;

import com.liuhongchen.bscommondto.common.Dto;
import com.liuhongchen.bscommondto.common.DtoUtil;
import com.liuhongchen.bscommonmodule.pojo.Book;
import com.liuhongchen.bscommonmodule.pojo.Goods;
import com.liuhongchen.bscommonutils.common.EmptyUtils;
import com.liuhongchen.bsitemconsumer.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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
