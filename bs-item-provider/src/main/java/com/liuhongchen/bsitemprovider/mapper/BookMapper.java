package com.liuhongchen.bsitemprovider.mapper;

import com.liuhongchen.bscommonmodule.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* Created by liuhongchen
*/
@Mapper
public interface BookMapper {

	public Book getBookById(@Param(value = "id") String id)throws Exception;


	public Integer insertBook(Book book)throws Exception;


	Book getBookByIsbn13(@Param("isbn") String isbn);
}
