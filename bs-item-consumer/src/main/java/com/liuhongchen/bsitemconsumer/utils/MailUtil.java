package com.liuhongchen.bsitemconsumer.utils;

import com.liuhongchen.bscommonmodule.pojo.Book;
import com.liuhongchen.bscommonmodule.pojo.Goods;
import com.liuhongchen.bscommonmodule.pojo.Mail;
import com.liuhongchen.bscommonmodule.pojo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:MailUtil
 * Package:com.liuhongchen.bsitemconsumer.utils
 * Description:
 *
 * @date:2020-04-19 10:12
 * @author:892698613@qq.com
 */
public class MailUtil {



    /**
     *
     * @param type 邮件发送类型
     *             用户下单 1  给卖家11和买家12.包括对方信息,商品信息+书籍信息
     *             提醒发货(配送方式)21 您出售的 书籍信息 收货 地址 联系方式
     *             提醒收货(自提方式)22 给买家 您购买的 书籍信息 收货,地址 联系方式
     *             完成交易3 给卖家31和买家32,书籍信息完成交易
     *             取消交易4 给卖家41和买家42,书籍信息 取消交易
     * @return  [0] mail to seller,[1] to buyer
     */
    public static List<Mail> getMail(Goods goods, Book book, User buyer,
                                     User seller, Integer type){

        List<Mail> mails=new ArrayList<>();

        switch (type){
            case 1:
                mails.add(getMail11(seller,buyer,goods,book));
                mails.add(getMail12(seller,buyer,goods,book));
                break;
            case 21:
                mails.add(getMail21(seller,buyer,goods,book));
                break;
            case 22:
                mails.add(getMail22(seller,buyer,goods,book));
                break;
            case 3:
                mails.add(getMail31(seller,book));
                mails.add(getMail32(buyer,book));
                break;
            case 4:
                mails.add(getMail41(seller,book));
                mails.add(getMail42(buyer,book));
                break;
        }

        return mails;

    }

    /**
     * 交易取消tobuyer
     */
    private static Mail getMail42(User buyer, Book book) {
        Mail mail=new Mail();
        mail.setTo(buyer.getEmail());
        mail.setTitle("交易取消提醒");
        StringBuilder content=new StringBuilder();
        content.append("您购买的"+"《"+book.getTitle()+"第"+book.getEdition()+"》"+"已取消交易!" +
                "详情请到小程序'我的发布'查看." +"记得将小程序推荐给朋友~");
        mail.setContent(content.toString());

        return mail;
    }

    /**
     * 交易取消toseller
     */
    private static Mail getMail41(User seller, Book book) {
        Mail mail=new Mail();
        mail.setTo(seller.getEmail());
        mail.setTitle("交易取消提醒");
        StringBuilder content=new StringBuilder();
        content.append("您发布的"+"《"+book.getTitle()+"第"+book.getEdition()+"》"+"已取消交易!" +
                "详情请到小程序'我的发布'查看." +"记得将小程序推荐给朋友~");
        mail.setContent(content.toString());

        return mail;

    }

    /**
     * 交易完成tobuyer
     */
    private static Mail getMail32( User buyer,  Book book) {
        Mail mail=new Mail();
        mail.setTo(buyer.getEmail());
        mail.setTitle("恭喜!交易完成");
        StringBuilder content=new StringBuilder();
        content.append("您购买的"+"《"+book.getTitle()+"第"+book.getEdition()+"》"+"已完成交易!" +
                "详情请到小程序'我的购买'查看." +"记得将小程序推荐给朋友~");
        mail.setContent(content.toString());

        return mail;
    }

    /**
     * 交易完成toseller
     */
    private static Mail getMail31(User seller, Book book) {
        Mail mail=new Mail();
        mail.setTo(seller.getEmail());
        mail.setTitle("恭喜!交易完成");
        StringBuilder content=new StringBuilder();
        content.append("您发布的"+"《"+book.getTitle()+"第"+book.getEdition()+"》"+"已完成交易!" +
                "详情请到小程序'我的发布'查看." +"记得将小程序推荐给朋友~");
        mail.setContent(content.toString());

        return mail;
    }

    /**
     * 提醒取货
     */
    private static Mail getMail22(User seller, User buyer, Goods goods, Book book) {

        Mail mail=new Mail();
        mail.setTo(buyer.getEmail());
        mail.setTitle("取货提醒!");
        StringBuilder content=new StringBuilder();
        content.append("您拍下的"+"《"+book.getTitle()+"第"+book.getEdition()+"》"+"收到买家的取货提醒!" +
                "详情请到小程序'我的发布'查看." +
                "卖家QQ:"+((seller.getQqnum()==null)?"":seller.getQqnum())+
                ","+
                "卖家微信:"+((seller.getWxnum()==null)?"":seller.getWxnum())+","+
                "卖家电话:"+((seller.getPhone()==null)?"":seller.getPhone())+".");
        content.append("您需要自取,地址为:"+goods.getAddress());
        mail.setContent(content.toString());

        return mail;
    }

    /**
     * 提醒发货
     */
    private static Mail getMail21(User seller, User buyer, Goods goods, Book book) {
        Mail mail=new Mail();
        mail.setTo(seller.getEmail());
        mail.setTitle("发货提醒!");
        StringBuilder content=new StringBuilder();
        content.append("您出售的"+"《"+book.getTitle()+"第"+book.getEdition()+"》"+"收到买家的发货提醒!" +
                "详情请到小程序'我的发布'查看." +
                "买家QQ:"+((buyer.getQqnum()==null)?"":buyer.getQqnum())+
                ","+
                "买家微信:"+((buyer.getWxnum()==null)?"":buyer.getWxnum())+","+
                "买家电话:"+((buyer.getPhone()==null)?"":buyer.getPhone())+".");
        content.append("收货地址为:"+goods.getAddress());
        mail.setContent(content.toString());

        return mail;

    }

    /**
     * 用户下单,给卖家发邮件
     */
    private static Mail getMail11(User seller, User buyer, Goods goods, Book book) {

        Mail mail=new Mail();
        mail.setTo(seller.getEmail());
        mail.setTitle("您发布的书籍被拍下了!");
        StringBuilder content=new StringBuilder();
        content.append(
                "您发布的"+"《"+book.getTitle()+"第"+book.getEdition()+"》"+"被拍下了!" +
                "详情请到小程序'我的发布'查看." +
                "买家QQ:"+((buyer.getQqnum()==null)?"":buyer.getQqnum())+","+
                "买家微信:"+((buyer.getWxnum()==null)?"":buyer.getWxnum())+","+
                "买家电话:"+((buyer.getPhone()==null)?"":buyer.getPhone())+".");
        content.append("您需要配送,收货地址为:"+goods.getAddress());
        mail.setContent(content.toString());

        return mail;
    }


    /**
     * 用户下单,给买家发邮件
     */
    private static Mail getMail12(User seller, User buyer, Goods goods,
                                  Book book) {

        Mail mail=new Mail();
        mail.setTo(buyer.getEmail());
        mail.setTitle("您成功拍下书籍!");
        StringBuilder content=new StringBuilder();
        content.append("您成功拍下"+"《"+book.getTitle()+"第"+book.getEdition()+"》"+"!" +
                "详情请到小程序'我的购买'查看." +
                "卖家QQ:"+((seller.getQqnum()==null)?"":seller.getQqnum())+
                ","+
                "卖家微信:"+((seller.getWxnum()==null)?"":seller.getWxnum())+","+
                "卖家电话:"+((seller.getPhone()==null)?"":seller.getPhone())+".");
        content.append("您需要自取,地址为:"+goods.getAddress());
        mail.setContent(content.toString());

        return mail;
    }


}
