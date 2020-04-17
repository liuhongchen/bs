package com.liuhongchen.bsitemconsumer.service;

import com.liuhongchen.bscommonmodule.pojo.Goods;

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
}
