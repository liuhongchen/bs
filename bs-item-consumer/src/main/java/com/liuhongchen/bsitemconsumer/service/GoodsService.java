package com.liuhongchen.bsitemconsumer.service;

import com.liuhongchen.bscommondto.vo.GoodsVo;
import com.liuhongchen.bscommonmodule.pojo.Goods;

import java.util.List;

/**
 * ClassName:GoodsService
 * Package:com.liuhongchen.bsitemconsumer.service
 * Description:
 *
 * @date:2020-04-17 18:14
 * @author:892698613@qq.com
 */
public interface GoodsService {


    Goods createGoods(Goods goods);


    Integer getGoodsStatus(Integer id);

    Goods getGoodsById(Integer id);

    List<GoodsVo> getGoodsVoBySellerId(Integer id);

    List<GoodsVo> getGoodsVoByBuyerId(Integer id);

    GoodsVo getGoodsVoById(Integer id);

    Integer cancelOrder(Integer id);
}
