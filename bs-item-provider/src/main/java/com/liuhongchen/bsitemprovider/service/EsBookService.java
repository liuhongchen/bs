package com.liuhongchen.bsitemprovider.service;



import com.liuhongchen.bscommonmodule.pojo.Book;

import java.util.List;

public interface EsBookService {
    void saveBook(Book book);



    void saveBook(List<Book> bookList);



    List<Book> searchBookByTitle(String title);

}
