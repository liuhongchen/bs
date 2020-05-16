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


    Integer getGoodsStatus(String id);

    Goods getGoodsById(String id);

    List<GoodsVo> getGoodsVoBySellerId(String id);

    List<GoodsVo> getGoodsVoByBuyerId(String id);
    List<GoodsVo> getGoodsVoByBuyerIdAndStatus(String id,Integer status);

    GoodsVo getGoodsVoById(String id);

    String cancelOrder(String id) throws Exception;


    void sendMail(String goodsId, Integer type) throws Exception;

    void sendMail(String goodsId, String buyerId, Integer type) throws Exception;

    List<GoodsVo> getAllGoodsVo();

    List<GoodsVo> getGoodsVoByTypeId(Integer typeId);

    Integer deleteGoods(String id);


    List<GoodsVo> getSellingGoodsVoByTypeId(Integer typeId);


    Integer createOrder(Goods goods) throws Exception;

    Integer finishOrder(String id) throws Exception;

    Integer deleteOrder(String id);

    List<GoodsVo> getNewGoodsVo();

    List<GoodsVo> search(String title);
}
