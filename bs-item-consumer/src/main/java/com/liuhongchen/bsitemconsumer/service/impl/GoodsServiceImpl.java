package com.liuhongchen.bsitemconsumer.service.impl;

import com.liuhongchen.bscommondto.vo.GoodsVo;
import com.liuhongchen.bscommonmodule.pojo.Book;
import com.liuhongchen.bscommonmodule.pojo.Goods;
import com.liuhongchen.bscommonmodule.pojo.Mail;
import com.liuhongchen.bscommonmodule.pojo.User;
import com.liuhongchen.bsitemconsumer.client.RestItemClient;
import com.liuhongchen.bsitemconsumer.client.RestMailClient;
import com.liuhongchen.bsitemconsumer.client.RestPayClient;
import com.liuhongchen.bsitemconsumer.client.RestUserClient;
import com.liuhongchen.bsitemconsumer.service.BookService;
import com.liuhongchen.bsitemconsumer.service.GoodsService;
import com.liuhongchen.bsitemconsumer.utils.MailUtil;
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

    @Autowired
    private RestUserClient userClient;

    @Autowired
    private RestMailClient mailClient;

    @Autowired
    private RestPayClient payClient;

    @Autowired
    private BookService bookService;
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
    public List<GoodsVo> getGoodsVoByBuyerIdAndStatus(Integer id,
                                                       Integer status) {

        if (status == 0) {
            return itemClient.getGoodsVoByBuyerId(id);
        } else {
            status++;
        }

        return itemClient.getGoodsVoByBuyerIdAndStatus(id,status);
    }

    @Override
    public List<GoodsVo> getGoodsVoByBuyerId(Integer id) {
        return itemClient.getGoodsVoByBuyerId(id);
    }

    @Override
    public GoodsVo getGoodsVoById(Integer id) {
        return itemClient.getGoodsVoById(id);
    }



    /**
     *
     * @param goodsId
     * @param type 邮件发送类型
     *             用户下单 1  给卖家和买家.包括对方信息,商品信息+书籍信息
     *             提醒收货(自提方式)21 给买家 您购买的 书籍信息 收货,地址 联系方式
     *             提醒发货(配送方式)22 您出售的 书籍信息 收货 地址 联系方式
     *             完成交易3 给卖家和买家,书籍信息完成交易
     *             取消交易4 给卖家和买家,书籍信息 取消交易
     */
    @Override
    public void sendMail(Integer goodsId, Integer type) throws Exception {

        Goods goods = itemClient.getGoodsById(goodsId);
        Book book = itemClient.getBookById(goods.getBookId());
        User buyer =
                userClient.getUserById(Long.parseLong(goods.getBuyerId().toString()));
        User seller =
                userClient.getUserById(Long.parseLong(goods.getSellerId().toString()));

        List<Mail> mails=MailUtil.getMail(goods,book,buyer,seller,type);


        for (Mail mail : mails) {
            mailClient.sendSimple(mail);
        }

    }

    @Override
    public List<GoodsVo> getAllGoodsVo() {
       return itemClient.getAllGoodsVo();
    }

    @Override
    public List<GoodsVo> getGoodsVoByTypeId(Integer typeId) {
        return itemClient.getGoodsVoByTypeId(typeId);
    }

    @Override
    public Integer deleteGoods(Integer id) {

        return itemClient.deleteGoods(id);
    }

    @Override
    public List<GoodsVo> getSellingGoodsVoByTypeId(Integer typeId) {
        //这个1,就是selling的goods status
        return itemClient.getGoodsVoByTypeIdAndStatus(typeId,1);
    }


    @Override
    public Integer createOrder(Goods goods) throws Exception {

        //这里必须重新获取一次price,万一买家下单的同时卖家改价格
        Goods queryGoods = itemClient.getGoodsById(goods.getId());
        Double price=queryGoods.getPrice();

        Book book=bookService.getBookById(queryGoods.getBookId());
        String bookName=book.getTitle()+"第"+book.getEdition();

        Integer minusResult= payClient.minus(goods.getBuyerId(),price,
                bookName,goods.getId());

        Integer addResult=payClient.add(2,price,
                bookName,goods.getId());

        Integer createResult=itemClient.updateGoods(goods);

        return 1;
    }

    @Override
    public Integer finishOrder(Integer id) throws Exception {
        //这里必须重新获取一次price,万一买家下单的同时卖家改价格
        Goods queryGoods = itemClient.getGoodsById(id);
        Double price=queryGoods.getPrice();



        Book book=bookService.getBookById(queryGoods.getBookId());
        String bookName=book.getTitle()+"第"+book.getEdition();

        Integer addResult= payClient.add(queryGoods.getSellerId(),price,
                bookName,id);

        Integer minusResult=payClient.minus(2,price,
                bookName,id);

        Goods goods=new Goods();
        goods.setId(id);
        goods.setStatus(3);

        Integer finishResult=itemClient.updateGoods(goods);

        return 1;
    }


    @Override
    public Integer cancelOrder(Integer id) throws Exception {
        //这里必须重新获取一次price,万一买家下单的同时卖家改价格
        Goods queryGoods = itemClient.getGoodsById(id);
        Double price=queryGoods.getPrice();



        Book book=bookService.getBookById(queryGoods.getBookId());
        String bookName=book.getTitle()+"第"+book.getEdition();

        Integer addResult= payClient.add(queryGoods.getBuyerId(),price,
                bookName,id);

        Integer minusResult=payClient.minus(2,price,
                bookName,id);

        Goods goods=new Goods();
        goods.setId(id);
        goods.setBuyerId(-1);
        goods.setStatus(1);

        Integer cancelResult=itemClient.updateGoods(goods);

        return 1;
    }

}
