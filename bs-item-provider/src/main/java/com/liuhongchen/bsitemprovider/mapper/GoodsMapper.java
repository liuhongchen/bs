package com.liuhongchen.bsitemprovider.mapper;

import com.liuhongchen.bscommonmodule.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* Created by liuhongchen
*/
@Mapper
public interface GoodsMapper {

	public Goods getGoodsById(@Param(value = "id") String id)throws Exception;

	public List<Goods>	getGoodsListByMap(Map<String, Object> param)throws Exception;

	public Integer insertGoods(Goods goods)throws Exception;

	public Integer updateGoods(Goods goods)throws Exception;

	public Integer deleteGoodsById(@Param(value = "id") String id)throws Exception;


}
