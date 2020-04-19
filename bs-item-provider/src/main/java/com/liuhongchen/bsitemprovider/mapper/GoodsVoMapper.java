package com.liuhongchen.bsitemprovider.mapper;

import com.liuhongchen.bscommondto.vo.GoodsVo;
import com.liuhongchen.bscommonmodule.pojo.Book;
import com.liuhongchen.bscommonmodule.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* Created by liuhongchen
*/
@Mapper
public interface GoodsVoMapper {



	GoodsVo getGoodsById(@Param("id")Integer id);

	List<GoodsVo> getGoodsListByMap(Map<String,Object> map);

    List<GoodsVo> getAllGoodsVo();
}
