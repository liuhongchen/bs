package com.liuhongchen.bspayconsumer.controller;


import com.liuhongchen.bscommondto.common.Dto;
import com.liuhongchen.bscommondto.common.DtoUtil;
import com.liuhongchen.bscommonmodule.pojo.Money;
import com.liuhongchen.bscommonutils.common.EmptyUtils;
import com.liuhongchen.bscommonutils.common.LogUtils;
import com.liuhongchen.bspayconsumer.service.PayService;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.Date;

@RestController
public class PayController {



    @Autowired
    private PayService payService;

    @Autowired
    private LogUtils logUtils;


    @GetMapping("/getRestMoney")
    public Dto getRestMoney(String id){
        if (EmptyUtils.isEmpty(id))return DtoUtil.returnFail("参数传递失败","0022");


        Double money;
        try {
             money=payService.getRestMoney(id);
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("pay_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("getRestMoney失败","0022");
        }

        if(EmptyUtils.isEmpty(money))return DtoUtil.returnFail("服务器错误","0033");

        return DtoUtil.returnSuccess("查询money成功",money);
    }


    @GetMapping("/getMoney")
    public Dto getMoney(String id){
        if (EmptyUtils.isEmpty(id))return DtoUtil.returnFail("参数传递失败","0022");
        Money money;
        try {
             money=payService.getMoney(id);
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("pay_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("getMoney失败","0022");
        }

        if(EmptyUtils.isEmpty(money))return DtoUtil.returnFail("服务器错误","0033");

        return DtoUtil.returnSuccess("查询money成功",money);
    }


    @GetMapping("/charge")
    public Dto charge(String id,Double num) throws Exception {
        if (EmptyUtils.isEmpty(id))return DtoUtil.returnFail("参数传递失败","0022");
        Integer result;
        try {
             result=payService.charge(id,num);
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("pay_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("charge失败","0022");
        }

        if (result!=1)return DtoUtil.returnFail("充值失败","0022");
        return DtoUtil.returnSuccess("充值成功",result);
    }

}
