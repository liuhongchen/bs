package com.liuhongchen.bsitemconsumer.client.fallback;

import com.liuhongchen.bscommondto.vo.GoodsVo;
import com.liuhongchen.bscommonmodule.pojo.Book;
import com.liuhongchen.bscommonmodule.pojo.Goods;
import com.liuhongchen.bsitemconsumer.client.RestItemClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemClientFallBack implements RestItemClient {


    @Override
    public Book isbn(String isbn) throws Exception {
        return null;
    }

    @Override
    public Goods createGoods(Goods goods) throws Exception {
        return null;
    }

    @Override
    public Integer getGoodsStatus(Integer id) {
        return null;
    }

    @Override
    public Goods getGoodsById(Integer id) {
        return null;
    }

    @Override
    public Book getBookById(Integer id) {
        return null;
    }

    @Override
    public List<Goods> getGoodsBySellerId(Integer id) {
        return null;
    }

    @Override
    public List<Goods> getGoodsByBuyerId(Integer id) {
        return null;
    }

    @Override
    public List<GoodsVo> getGoodsVoBySellerId(Integer id) {
        return null;
    }

    @Override
    public List<GoodsVo> getGoodsVoByBuyerId(Integer id) {
        return null;
    }

    @Override
    public List<GoodsVo> getGoodsVoByBuyerIdAndStatus(Integer id, Integer status) {
        return null;
    }

    @Override
    public GoodsVo getGoodsVoById(Integer id) {
        return null;
    }

    @Override
    public Integer cancelOrder(Integer id) {
        return null;
    }

    @Override
    public List<GoodsVo> getAllGoodsVo() {
        return null;
    }

    @Override
    public List<GoodsVo> getGoodsVoByTypeId(Integer typeId) {
        return null;
    }
}
