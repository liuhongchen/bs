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
    public Integer getGoodsStatus(String id) {
        return null;
    }

    @Override
    public Goods getGoodsById(String id) {
        return null;
    }

    @Override
    public Book getBookById(String id) {
        return null;
    }

    @Override
    public List<Goods> getGoodsBySellerId(String id) {
        return null;
    }

    @Override
    public List<Goods> getGoodsByBuyerId(String id) {
        return null;
    }

    @Override
    public List<GoodsVo> getGoodsVoBySellerId(String id) {
        return null;
    }

    @Override
    public List<GoodsVo> getGoodsVoByBuyerId(String id) {
        return null;
    }

    @Override
    public List<GoodsVo> getGoodsVoByBuyerIdAndStatus(String id, Integer status) {
        return null;
    }

    @Override
    public GoodsVo getGoodsVoById(String id) {
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

    @Override
    public Integer deleteGoods(String id) {
        return null;
    }

    @Override
    public List<GoodsVo> getGoodsVoByTypeIdAndStatus(Integer typeId, Integer status) {
        return null;
    }

    @Override
    public Integer updateGoods(Goods goods) {
        return null;
    }

    @Override
    public Integer createOrder(Goods goods) {
        return null;
    }

    @Override
    public List<GoodsVo> getNewGoodsVo() {
//        throw new Exception()
        return null;
    }

    @Override
    public List<GoodsVo> search(String title) {
        return null;
    }
}
