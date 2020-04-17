package com.liuhongchen.bsitemconsumer.client.fallback;

import com.liuhongchen.bscommonmodule.pojo.Book;
import com.liuhongchen.bscommonmodule.pojo.Goods;
import com.liuhongchen.bsitemconsumer.client.RestItemClient;
import org.springframework.stereotype.Component;

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
}
