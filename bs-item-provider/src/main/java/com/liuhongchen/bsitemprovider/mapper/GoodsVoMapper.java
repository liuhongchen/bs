package com.liuhongchen.bsitemprovider.mapper;

import com.liuhongchen.bscommondto.vo.GoodsVo;
import com.liuhongchen.bscommonmodule.pojo.Book;
import com.liuhongchen.bscommonmodule.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* Created by liuhongchen
*/
@Mapper
public interface GoodsVoMapper {



	GoodsVo getGoodsById(@Param("id")String id);

	List<GoodsVo> getGoodsListByMap(Map<String,Object> map);

    List<GoodsVo> getAllGoodsVo();

    List<GoodsVo> getNewGoodsVo();

    List<GoodsVo> getByIds(List<String> list);

    GoodsVo getGoodsByBookId(String id);
}
