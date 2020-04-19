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
    @GetMapping("/getGoodsVoByBuyerIdAndStatus")
    public Dto getGoodsVoByBuyerIdAndStatus(Integer id,Integer status){
        if (id==null||id<=0||status==null)
            return DtoUtil.returnFail("参数错误", "0022");

        List<GoodsVo> goodsList
                =goodsService.getGoodsVoByBuyerIdAndStatus(id, status);

        if (goodsList==null)return DtoUtil.returnFail("goodsList查询失败","0022");

        return DtoUtil.returnSuccess("goodsList查询成功",goodsList);
    }



    @GetMapping("/getAllGoodsVo")
    public Dto getAllGoodsVo(){

        List<GoodsVo> goodsList=goodsService.getAllGoodsVo();

        if (goodsList==null)return DtoUtil.returnFail("goodsList查询失败","0022");

        return DtoUtil.returnSuccess("goodsList查询成功",goodsList);
    }


    @GetMapping("/getGoodsVoByTypeId")
    public Dto getGoodsVoByTypeId(Integer typeId){


        List<GoodsVo> goodsList=null;
        if (typeId==0){
            goodsList=goodsService.getAllGoodsVo();
        }else{
            goodsList=goodsService.getGoodsVoByTypeId(typeId+2);
        }

        if (goodsList==null)return DtoUtil.returnFail("goodsList查询失败","0022");

        return DtoUtil.returnSuccess("goodsList查询成功",goodsList);
    }


    @GetMapping("/cancelOrder")
    public Dto cancelOrder(Integer id) throws Exception {
        if (id==null||id<=0)return DtoUtil.returnFail("id错误","0022");


        goodsService.sendMail(id,4);
        Integer res=goodsService.cancelOrder(id);


        return (res==null||res!=1)?
                DtoUtil.returnFail("取消失败","0022"):
                DtoUtil.returnSuccess("取消成功");
    }


    /**
     * 发送邮件
     * @param goodsId 根据goods信息去获取相关的信息,如email等
     * @param type 邮件发送类型.
     *             用户下单 1  给卖家和买家.包括对方信息,商品信息+书籍信息
     *             提醒收货(自提方式)21 给买家 您购买的 书籍信息 收货,地址 联系方式
     *             提醒发货(配送方式)22 您出售的 书籍信息 收货 地址 联系方式
     *             完成交易3 给卖家和买家,书籍信息完成交易
     *             取消交易4 给卖家和买家,书籍信息 取消交易
     * @return
     */
    @SuppressWarnings("JavaDoc")
    @GetMapping("/sendMail")
    public Dto sendMail(Integer goodsId,Integer type) throws Exception {
        if (goodsId==null||type==null)return DtoUtil.returnFail("参数传递失败",
                "0022");

        goodsService.sendMail(goodsId,type);


        return DtoUtil.returnSuccess("发送成功");
    }

}
