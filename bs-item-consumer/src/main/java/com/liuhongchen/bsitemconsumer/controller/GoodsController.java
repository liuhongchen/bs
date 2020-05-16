package com.liuhongchen.bsitemconsumer.controller;

import com.liuhongchen.bscommondto.common.Dto;
import com.liuhongchen.bscommondto.common.DtoUtil;
import com.liuhongchen.bscommondto.vo.GoodsVo;
import com.liuhongchen.bscommonmodule.pojo.Goods;
import com.liuhongchen.bscommonutils.common.CheckUtils;
import com.liuhongchen.bscommonutils.common.EmptyUtils;
import com.liuhongchen.bscommonutils.common.LogUtils;
import com.liuhongchen.bsitemconsumer.service.GoodsService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 *
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

    @Autowired
    private LogUtils logUtils;

    @GetMapping("/createGoods")
    public Dto createGoods(String bookid,Integer kindid,Integer collegeid,
                           String price,Integer deliveryid,String place,
                           String notes,String sellerid){

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
        Goods goodsResult;
        try {
            goodsResult = goodsService.createGoods(goods);

        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("item_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("createGoods失败","0022");
        }
        if (goodsResult==null||goodsResult.getId()==null)return DtoUtil.returnFail("商品创建失败","0022");


        return DtoUtil.returnSuccess("创建成功",goodsResult.getId());
    }



    @GetMapping("/getGoodsStatus")
    public Dto getGoodsStatus(String id){
        if (EmptyUtils.isEmpty(id))return DtoUtil.returnFail("id错误","0022");
        Integer goodsStatus;
        try {
            goodsStatus = goodsService.getGoodsStatus(id);
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("item_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("getGoodsStatus失败","0022");
        }
        if (goodsStatus==null)return DtoUtil.returnFail("查询失败","0022");

        return DtoUtil.returnSuccess("查询成功",goodsStatus);
    }

    @GetMapping("/getGoodsById")
    public Dto getGoodsById(String id){
        if (EmptyUtils.isEmpty(id))return DtoUtil.returnFail("id错误","0022");
        Goods goods;
        try {
            goods = goodsService.getGoodsById(id);
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("item_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("getGoodsById失败","0022");
        }
        if (goods==null)return DtoUtil.returnFail("查询失败","0022");

        return DtoUtil.returnSuccess("查询成功",goods);
    }

    @GetMapping("/search")
    public Dto search(String title){
        if (EmptyUtils.isEmpty(title))return DtoUtil.returnFail("title错误","0022");
        List<GoodsVo> goodsVoList;
        try {
            goodsVoList = goodsService.search(title);

        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("item_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("search失败","0022");
        }
        return DtoUtil.returnSuccess("查询成功",goodsVoList);
    }

    @GetMapping("/getGoodsVoById")
    public Dto getGoodsVoById(String id){
        if (EmptyUtils.isEmpty(id))return DtoUtil.returnFail("id错误","0022");
        GoodsVo goods;
        try {
            goods = goodsService.getGoodsVoById(id);
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("item_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("getGoodsVoById失败","0022");
        }
        if (goods==null)return DtoUtil.returnFail("查询失败","0022");

        return DtoUtil.returnSuccess("查询成功",goods);
    }
    @GetMapping("/getNewGoodsVo")
    public Dto getNewGoodsVo(){
        List<GoodsVo> goodsVoList;
        try {
            goodsVoList = goodsService.getNewGoodsVo();
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("item_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("getNewGoodsVo失败","0022");
        }
        return DtoUtil.returnSuccess("查询成功",goodsVoList);
    }

    @GetMapping("/getGoodsVoBySellerId")
    public Dto getGoodsBySellerId(String id){
        if (EmptyUtils.isEmpty(id))return DtoUtil.returnFail("id错误","0022");
        List<GoodsVo> goodsList;
        try {
            goodsList=goodsService.getGoodsVoBySellerId(id);
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("item_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("getGoodsVoBySellerId失败","0022");
        }

        if (goodsList==null)return DtoUtil.returnFail("goodsList查询失败","0022");

        return DtoUtil.returnSuccess("goodsList查询成功",goodsList);
    }
    @GetMapping("/getGoodsVoByBuyerIdAndStatus")
    public Dto getGoodsVoByBuyerIdAndStatus(String id,Integer status){
        if (EmptyUtils.isEmpty(id)||status==null)
            return DtoUtil.returnFail("参数错误", "0022");
        List<GoodsVo> goodsList;
        try {
            goodsList=goodsService.getGoodsVoByBuyerIdAndStatus(id, status);
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("item_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("getGoodsVoByBuyerIdAndStatus失败","0022");
        }


        if (goodsList==null)return DtoUtil.returnFail("getGoodsVoByBuyerIdAndStatus查询失败","0022");

        return DtoUtil.returnSuccess("goodsList查询成功",goodsList);
    }



    @GetMapping("/getAllGoodsVo")
    public Dto getAllGoodsVo(){
        List<GoodsVo> goodsList;
        try {
            goodsList=goodsService.getAllGoodsVo();
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("item_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("getAllGoodsVo失败","0022");
        }

        if (goodsList==null)return DtoUtil.returnFail("goodsList查询失败","0022");

        return DtoUtil.returnSuccess("goodsList查询成功",goodsList);
    }


    @GetMapping("/getGoodsVoByTypeId")
    public Dto getGoodsVoByTypeId(Integer typeId){


        List<GoodsVo> goodsList=null;
        try {
            if (typeId==0){
                goodsList=goodsService.getAllGoodsVo();
            }else{
                goodsList=goodsService.getGoodsVoByTypeId(typeId+2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("item_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("getGoodsVoByTypeId失败","0022");
        }


        if (goodsList==null)return DtoUtil.returnFail("goodsList查询失败","0022");

        return DtoUtil.returnSuccess("goodsList查询成功",goodsList);
    }

    @GetMapping("/getSellingGoodsVoByTypeId")
    public Dto getSellingGoodsVoByTypeId(Integer typeId){

        typeId+=2;
        List<GoodsVo> goodsList;
        try {
            goodsList=goodsService.getSellingGoodsVoByTypeId(typeId);

        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("item_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("getSellingGoodsVoByTypeId失败","0022");
        }



        if (goodsList==null)return DtoUtil.returnFail("goodsList查询失败","0022");

        return DtoUtil.returnSuccess("goodsList查询成功",goodsList);
    }


    @GetMapping("/cancelOrder")
    public Dto cancelOrder(String id) throws Exception {
        if (EmptyUtils.isEmpty(id))return DtoUtil.returnFail("id错误","0022");


        String buyerId;
        try {
            buyerId=goodsService.cancelOrder(id);
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("item_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("cancelOrder失败","0022");
        }
        try {
            goodsService.sendMail(id,buyerId,4);
        }catch (Exception e){
            e.printStackTrace();
            logUtils.i("item_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
        }
        return DtoUtil.returnSuccess("取消成功");
    }

    @GetMapping("/finishOrder")
    public Dto finishOrder(String id) throws Exception {
        if (EmptyUtils.isEmpty(id))return DtoUtil.returnFail("id错误","0022");


        Integer res;
        try {
            res=goodsService.finishOrder(id);
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("item_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("finishOrder失败","0022");
        }

        try {
            goodsService.sendMail(id,3);
        }catch (Exception e){
            e.printStackTrace();
            logUtils.i("item_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
        }


        return (res==null||res!=1)?
                DtoUtil.returnFail("取消失败","0022"):
                DtoUtil.returnSuccess("取消成功");
    }

    @GetMapping("/createOrder")
    public Dto createOrder(String goodsId,String buyerId,String address)  {

        Goods goods=new Goods();
        goods.setId(goodsId);
//        buyerId= String.valueOf((int)(Math.random()*100));
        goods.setBuyerId(buyerId);
        goods.setStatus(2);
        if (!address.equals("address"))goods.setAddress(address);
        goods.setUpdateTime(new Date());
        Integer result= null;
        try {
            result = goodsService.createOrder(goods);

        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("item_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("createOrder失败","0022");
        }
        try {
            goodsService.sendMail(goodsId,1);
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("item_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
        }


        if (result==1)return DtoUtil.returnSuccess("创建成功");
        return DtoUtil.returnFail("创建失败","0022");
    }

    @GetMapping("/deleteOrder")
    public Dto deleteOrder(String id) throws Exception {
        if (EmptyUtils.isEmpty(id))return DtoUtil.returnFail("id错误","0022");

        Integer res;
        try {
            res=goodsService.deleteOrder(id);
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("item_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("deleteOrder失败","0022");
        }


        return (res==null||res!=1)?
                DtoUtil.returnFail("删除失败","0022"):
                DtoUtil.returnSuccess("删除成功");
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
    public Dto sendMail(String goodsId,Integer type) throws Exception {
        if (goodsId==null||type==null)return DtoUtil.returnFail("参数传递失败",
                "0022");
        try {
            goodsService.sendMail(goodsId,type);
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("item_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("sendMail失败","0022");
        }


        return DtoUtil.returnSuccess("发送成功");
    }


    @GetMapping("/deleteGoods")
    public Dto deleteGoods(String id){
        if (EmptyUtils.isEmpty(id))return DtoUtil.returnFail("参数错误","0022");

        Integer res;
        try {
             res=goodsService.deleteGoods(id);
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("item_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("deleteGoods失败","0022");
        }

        if(res==null||res!=1)return DtoUtil.returnFail("服务器错误","0033");

        return DtoUtil.returnSuccess("删除成功");
    }
}
