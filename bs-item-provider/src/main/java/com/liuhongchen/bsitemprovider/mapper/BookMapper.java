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

	public List<Book>	getBookListByMap(Map<String, Object> param)throws Exception;

	public Integer getBookCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertBook(Book book)throws Exception;

	public Integer updateBook(Book book)throws Exception;

	public Integer deleteBookById(@Param(value = "id") String id)throws Exception;

	public Integer batchDeleteBook(Map<String, List<String>> params);

	Book getBookByIsbn10(@Param("isbn") String isbn);

	Book getBookByIsbn13(@Param("isbn") String isbn);
}
