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

    @RequestMapping(value = "/createGoods", method = RequestMethod.POST)
    public Goods createGoods(@RequestBody Goods goods) throws Exception;

    @RequestMapping(value = "/getGoodsStatus", method = RequestMethod.POST)
    Integer getGoodsStatus(@RequestParam("id") String id);

    @RequestMapping(value = "/getGoodsById", method = RequestMethod.POST)
    Goods getGoodsById(@RequestParam("id")String id);

    @RequestMapping(value = "/getBookById", method = RequestMethod.POST)
    Book getBookById(@RequestParam("id")String id);

    @RequestMapping(value = "/getGoodsBySellerId", method = RequestMethod.POST)
    List<Goods> getGoodsBySellerId(@RequestParam("id")String id);

    @RequestMapping(value = "/getGoodsByBuyerId", method = RequestMethod.POST)
    List<Goods> getGoodsByBuyerId(@RequestParam("id")String id);

    @RequestMapping(value = "/getGoodsVoBySellerId", method = RequestMethod.POST)
    List<GoodsVo> getGoodsVoBySellerId(@RequestParam("id")String id);

    @RequestMapping(value = "/getGoodsVoByBuyerId", method = RequestMethod.POST)
    List<GoodsVo> getGoodsVoByBuyerId(@RequestParam("id")String id);
    @RequestMapping(value = "/getGoodsVoByBuyerIdAndStatus", method =
            RequestMethod.POST)
    List<GoodsVo> getGoodsVoByBuyerIdAndStatus(@RequestParam("id")String id,
                                               @RequestParam("status")Integer status);


    @RequestMapping(value = "/getGoodsVoById", method = RequestMethod.POST)
    GoodsVo getGoodsVoById(@RequestParam("id") String id);

    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    Integer cancelOrder(@RequestParam("id")String id);

    @RequestMapping(value = "/getAllGoodsVo", method = RequestMethod.POST)
    List<GoodsVo> getAllGoodsVo();

    @RequestMapping(value = "/getGoodsVoByTypeId", method = RequestMethod.POST)
    List<GoodsVo> getGoodsVoByTypeId(@RequestParam("typeId")Integer typeId);

    @RequestMapping(value = "/deleteGoods", method = RequestMethod.POST)
    Integer deleteGoods(@RequestParam("id")String id);

    @RequestMapping(value = "/getGoodsVoByTypeIdAndStatus", method = RequestMethod.POST)
    List<GoodsVo> getGoodsVoByTypeIdAndStatus(@RequestParam("typeId")Integer typeId,
                                            @RequestParam("status")Integer status);

    @RequestMapping(value = "/updateGoods", method = RequestMethod.POST)
    Integer updateGoods(@RequestBody Goods goods);

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    Integer createOrder(@RequestBody Goods goods);

    @RequestMapping(value = "/getNewGoodsVo", method = RequestMethod.POST)
    List<GoodsVo> getNewGoodsVo();

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    List<GoodsVo> search(@RequestParam("title") String title);
}

