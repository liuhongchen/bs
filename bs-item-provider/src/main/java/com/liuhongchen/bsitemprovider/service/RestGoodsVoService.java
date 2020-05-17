package com.liuhongchen.bsitemprovider.service;

import com.liuhongchen.bscommondto.vo.GoodsVo;
import com.liuhongchen.bscommonmodule.pojo.Book;
import com.liuhongchen.bscommonutils.common.EmptyUtils;
import com.liuhongchen.bsitemprovider.mapper.GoodsVoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RestGoodsVoService {


    @Autowired
    private GoodsVoMapper goodsVoMapper;

    @Autowired
    private EsBookService esBookService;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<GoodsVo> search(@RequestParam("title") String title) throws Exception {
        List<Book> books = esBookService.searchBookByTitle(title);
        System.out.println(books);
        if (EmptyUtils.isEmpty(books)) return null;
        List<String> ids=new ArrayList<>();
        for (Book book : books) {
            if (ids.contains(book.getId())) continue;
            ids.add(book.getId());
        }
        List<GoodsVo> goodsVoList = new ArrayList<>();
        for (String id : ids) {
            goodsVoList.addAll(goodsVoMapper.getGoodsByBookId(id));
        }

        return goodsVoList;
    }



    @RequestMapping(value = "/getGoodsVoBySellerId", method = RequestMethod.POST)
    public List<GoodsVo> getGoodsBySellerId(@RequestParam("id") String id) throws Exception {

        Map<String, Object> params = new HashMap<>();
        params.put("sellerId", id);
        return goodsVoMapper.getGoodsListByMap(params);
    }

    @RequestMapping(value = "/getGoodsVoByBuyerId", method = RequestMethod.POST)
    public List<GoodsVo> getGoodsByBuyerId(@RequestParam("id") String id) throws Exception {

        Map<String, Object> params = new HashMap<>();
        params.put("buyerId", id);
        return goodsVoMapper.getGoodsListByMap(params);
    }

    @RequestMapping(value = "/getGoodsVoByBuyerIdAndStatus", method = RequestMethod.POST)
    public List<GoodsVo> getGoodsVoByBuyerIdAndStatus(@RequestParam("id") String id
            , @RequestParam("status") Integer status) throws Exception {

        Map<String, Object> params = new HashMap<>();
        params.put("buyerId", id);
        params.put("status", status);
        return goodsVoMapper.getGoodsListByMap(params);
    }

    @RequestMapping(value = "/getGoodsVoById", method = RequestMethod.POST)
    public GoodsVo getGoodsVoById(@RequestParam("id") String id) throws Exception {
            return  goodsVoMapper.getGoodsById(id);

    }

    @RequestMapping(value = "/getNewGoodsVo", method = RequestMethod.POST)
    public List<GoodsVo> getNewGoodsVo() throws Exception {
        return goodsVoMapper.getNewGoodsVo();
    }

    @RequestMapping(value = "/getGoodsVoByTypeId", method = RequestMethod.POST)
    public List<GoodsVo> getGoodsVoByTypeId(@RequestParam("typeId") Integer typeId) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("typeId", typeId);
        return goodsVoMapper.getGoodsListByMap(params);
    }

    @RequestMapping(value = "/getGoodsVoByTypeIdAndStatus", method = RequestMethod.POST)
    public List<GoodsVo> getGoodsVoByTypeIdAndStatus(@RequestParam("typeId") Integer typeId,
                                                     @RequestParam("status") Integer status) throws Exception {
        Map<String, Object> params = new HashMap<>();
        if (typeId != 0) params.put("typeId", typeId);
        params.put("status", status);
        return goodsVoMapper.getGoodsListByMap(params);
    }

    @RequestMapping(value = "/getAllGoodsVo", method = RequestMethod.POST)
    public List<GoodsVo> getAllGoodsVo() throws Exception {
        return goodsVoMapper.getAllGoodsVo();
    }

}
