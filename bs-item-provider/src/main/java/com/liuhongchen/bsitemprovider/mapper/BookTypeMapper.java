package com.liuhongchen.bsitemprovider.mapper;

import com.liuhongchen.bscommonmodule.pojo.BookType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* Created by liuhongchen
*/
@Mapper
public interface BookTypeMapper {

	public BookType getBookTypeById(@Param(value = "id") String id)throws Exception;

	public List<BookType>	getBookTypeListByMap(Map<String, Object> param)throws Exception;

	public Integer getBookTypeCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertBookType(BookType bookType)throws Exception;

	public Integer updateBookType(BookType bookType)throws Exception;

	public Integer deleteBookTypeById(@Param(value = "id") String id)throws Exception;

	public Integer batchDeleteBookType(Map<String, List<String>> params);

}
