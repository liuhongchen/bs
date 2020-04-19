package com.liuhongchen.bsitemconsumer.client;

import com.liuhongchen.bscommondto.vo.GoodsVo;
import com.liuhongchen.bscommonmodule.pojo.Book;
import com.liuhongchen.bscommonmodule.pojo.Goods;
import com.liuhongchen.bsitemconsumer.client.fallback.ItemClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by liuhongchen
 */
@FeignClient(name = "bs-item-provider", fallback = ItemClientFallBack.class)
public interface RestItemClient {
    @RequestMapping(value = "/isbn", method = RequestMethod.POST)
    public Book isbn(@RequestParam("isbn") String isbn) throws Exception;

//    @RequestMapping(value = "/getUserCountByMap", method = RequestMethod.POST)
//    public Integer getUserCountByMap(@RequestParam Map<String, Object> param) throws Exception;
//
//
//
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public Object[] update(@RequestBody User user) throws Exception ;


    @RequestMapping(value = "/createGoods", method = RequestMethod.POST)
    public Goods createGoods(@RequestBody Goods goods) throws Exception;

    @RequestMapping(value = "/getGoodsStatus", method = RequestMethod.POST)
    Integer getGoodsStatus(@RequestParam("id") Integer id);

    @RequestMapping(value = "/getGoodsById", method = RequestMethod.POST)
    Goods getGoodsById(@RequestParam("id")Integer id);

    @RequestMapping(value = "/getBookById", method = RequestMethod.POST)
    Book getBookById(@RequestParam("id")Integer id);

    @RequestMapping(value = "/getGoodsBySellerId", method = RequestMethod.POST)
    List<Goods> getGoodsBySellerId(@RequestParam("id")Integer id);

    @RequestMapping(value = "/getGoodsByBuyerId", method = RequestMethod.POST)
    List<Goods> getGoodsByBuyerId(@RequestParam("id")Integer id);

    @RequestMapping(value = "/getGoodsVoBySellerId", method = RequestMethod.POST)
    List<GoodsVo> getGoodsVoBySellerId(@RequestParam("id")Integer id);

    @RequestMapping(value = "/getGoodsVoByBuyerId", method = RequestMethod.POST)
    List<GoodsVo> getGoodsVoByBuyerId(@RequestParam("id")Integer id);
    @RequestMapping(value = "/getGoodsVoByBuyerIdAndStatus", method =
            RequestMethod.POST)
    List<GoodsVo> getGoodsVoByBuyerIdAndStatus(@RequestParam("id")Integer id,
                                               @RequestParam("status")Integer status);


    @RequestMapping(value = "/getGoodsVoById", method = RequestMethod.POST)
    GoodsVo getGoodsVoById(@RequestParam("id") Integer id);

    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    Integer cancelOrder(@RequestParam("id")Integer id);

    @RequestMapping(value = "/getAllGoodsVo", method = RequestMethod.POST)
    List<GoodsVo> getAllGoodsVo();

    @RequestMapping(value = "/getGoodsVoByTypeId", method = RequestMethod.POST)
    List<GoodsVo> getGoodsVoByTypeId(@RequestParam("typeId")Integer typeId);
}

