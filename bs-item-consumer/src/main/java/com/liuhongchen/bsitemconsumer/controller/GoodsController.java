package com.liuhongchen.bsitemconsumer.controller;

import com.liuhongchen.bscommondto.common.Dto;
import com.liuhongchen.bscommondto.common.DtoUtil;
import com.liuhongchen.bscommondto.vo.GoodsVo;
import com.liuhongchen.bscommonmodule.pojo.Goods;
import com.liuhongchen.bscommonutils.common.CheckUtils;
import com.liuhongchen.bscommonutils.common.EmptyUtils;
import com.liuhongchen.bsitemconsumer.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * ClassName:GoodsController
 * Package:com.liuhongchen.bsitemconsumer.controller
 * Description:
 *
 * @date:2020-04-17 17:19
 * @author:892698613@qq.com
 */
@RestController
public class GoodsController {


    @Autowired
    private GoodsService goodsService;


    @GetMapping("/createGoods")
    public Dto createGoods(Integer bookid,Integer kindid,Integer collegeid,
                           String price,Integer deliveryid,String place,
                           String notes,Integer sellerid){

        System.out.println(bookid+" "+kindid+" "+collegeid+" "+price+" "+deliveryid+" "+place+" "+notes+" "+sellerid);

        if (bookid==null||kindid==null||collegeid==null||price==null||deliveryid==null||place==null||notes==null||sellerid==null){
            return DtoUtil.returnFail("参数传入失败","0022");
        }

        Goods goods=new Goods();
        goods.setBookId(bookid);
        goods.setTypeId((kindid==0)?1:(collegeid+2));
        goods.setPrice(Double.parseDouble(price));
        goods.setWay(deliveryid);
        if (deliveryid==0)goods.setAddress(place);//0自取
        goods.setInfo(notes);
        goods.setSellerId(sellerid);
        goods.setStatus(1);
        goods.setCreateTime(new Date());
        goods.setUpdateTime(new Date());

        Goods goodsResult = goodsService.createGoods(goods);
        if (goodsResult==null||goodsResult.getId()==null)return DtoUtil.returnFail("商品创建失败","0022");


        return DtoUtil.returnSuccess("创建成功",goodsResult.getId());
    }



    @GetMapping("/getGoodsStatus")
    public Dto getGoodsStatus(Integer id){
        if (id==null||id<=0)return DtoUtil.returnFail("id错误","0022");

        Integer goodsStatus = goodsService.getGoodsStatus(id);
        if (goodsStatus==null)return DtoUtil.returnFail("查询失败","0022");

        return DtoUtil.returnSuccess("查询成功",goodsStatus);
    }

    @GetMapping("/getGoodsById")
    public Dto getGoodsById(Integer id){
        if (id==null||id<=0)return DtoUtil.returnFail("id错误","0022");

        Goods goods = goodsService.getGoodsById(id);
        if (goods==null)return DtoUtil.returnFail("查询失败","0022");

        return DtoUtil.returnSuccess("查询成功",goods);
    }
    @GetMapping("/getGoodsVoById")
    public Dto getGoodsVoById(Integer id){
        if (id==null||id<=0)return DtoUtil.returnFail("id错误","0022");

        GoodsVo goods = goodsService.getGoodsVoById(id);
        if (goods==null)return DtoUtil.returnFail("查询失败","0022");

        return DtoUtil.returnSuccess("查询成功",goods);
    }

    @GetMapping("/getGoodsVoBySellerId")
    public Dto getGoodsBySellerId(Integer id){
        if (id==null||id<=0)return DtoUtil.returnFail("id错误","0022");

        List<GoodsVo> goodsList=goodsService.getGoodsVoBySellerId(id);

        if (goodsList==null)return DtoUtil.returnFail("goodsList查询失败","0022");

        return DtoUtil.returnSuccess("goodsList查询成功",goodsList);
    }


    @GetMapping("/cancelOrder")
    public Dto cancelOrder(Integer id){
        if (id==null||id<=0)return DtoUtil.returnFail("id错误","0022");


        Integer res=goodsService.cancelOrder(id);

        return (res==null||res!=1)?
                DtoUtil.returnFail("取消失败","0022"):
                DtoUtil.returnSuccess("取消成功");
    }

}
