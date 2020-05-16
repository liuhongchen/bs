package com.liuhongchen.bsitemconsumer.service;

import com.liuhongchen.bscommonmodule.pojo.Book;

/**
 * ClassName:BookService
 * Package:com.liuhongchen.bsitemconsumer.service.impl
 * Description:
 *
 * @date:2020-04-16 18:37
 * @author:892698613@qq.com
 */
public interface BookService {

    Book isbn(String isbn);

    Book getBookById(String id);

}
