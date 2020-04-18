package com.liuhongchen.bsitemconsumer.service.impl;

import com.liuhongchen.bscommondto.vo.GoodsVo;
import com.liuhongchen.bscommonmodule.pojo.Goods;
import com.liuhongchen.bsitemconsumer.client.RestItemClient;
import com.liuhongchen.bsitemconsumer.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:GoodsServiceImpl
 * Package:com.liuhongchen.bsitemconsumer.service.impl
 * Description:
 *
 * @date:2020-04-17 18:15
 * @author:892698613@qq.com
 */
@Service
public class GoodsServiceImpl implements GoodsService {


    @Autowired
    private RestItemClient itemClient;


    @Override
    public Goods createGoods(Goods goods) {
        try {
            return itemClient.createGoods(goods);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Integer getGoodsStatus(Integer id) {
        return itemClient.getGoodsStatus(id);
    }

    @Override
    public Goods getGoodsById(Integer id) {
        return itemClient.getGoodsById(id);
    }

    @Override
    public List<GoodsVo> getGoodsVoBySellerId(Integer id) {
        return itemClient.getGoodsVoBySellerId(id);
    }

    @Override
    public List<GoodsVo> getGoodsVoByBuyerId(Integer id) {
        return itemClient.getGoodsVoByBuyerId(id);
    }

    @Override
    public GoodsVo getGoodsVoById(Integer id) {
        return itemClient.getGoodsVoById(id);
    }

    @Override
    public Integer cancelOrder(Integer id) {
        return itemClient.cancelOrder(id);
    }
}
