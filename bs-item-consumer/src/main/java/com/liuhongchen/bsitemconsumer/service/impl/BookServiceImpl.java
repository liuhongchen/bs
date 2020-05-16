package com.liuhongchen.bsitemconsumer.service.impl;

import com.liuhongchen.bscommonmodule.pojo.Book;
import com.liuhongchen.bsitemconsumer.client.RestItemClient;
import com.liuhongchen.bsitemconsumer.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName:BookServiceImpl
 * Package:com.liuhongchen.bsitemconsumer.service.impl
 * Description:
 *
 * @date:2020-04-16 18:38
 * @author:892698613@qq.com
 */
@Service
public class BookServiceImpl implements BookService {


    @Autowired
    private RestItemClient itemClient;

    @Override
    public Book isbn(String isbn) {
        try {
            return itemClient.isbn(isbn);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Book getBookById(String id) {
        return itemClient.getBookById(id);
    }
}
