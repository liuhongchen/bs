package com.liuhongchen.bspayconsumer.controller;


import com.alibaba.fastjson.JSONObject;
import com.liuhongchen.bscommondto.common.Dto;
import com.liuhongchen.bscommondto.common.DtoUtil;
import com.liuhongchen.bscommondto.vo.UserVo;
import com.liuhongchen.bscommonmodule.pojo.Money;
import com.liuhongchen.bscommonmodule.pojo.User;
import com.liuhongchen.bscommonutils.common.CheckUtils;
import com.liuhongchen.bscommonutils.common.EmptyUtils;
import com.liuhongchen.bscommonutils.common.LogUtils;
import com.liuhongchen.bspayconsumer.service.*;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

@RestController
public class PayController {


    Logger logger = LoggerFactory.getLogger(PayController.class);


    @Autowired
    private PayService payService;



    @GetMapping("/getRestMoney")
    public Dto getRestMoney(Integer id){
        if (EmptyUtils.isEmpty(id))return DtoUtil.returnFail("参数传递失败","0022");

        Double money=payService.getRestMoney(id);

        if(EmptyUtils.isEmpty(money))return DtoUtil.returnFail("服务器错误","0033");

        return DtoUtil.returnSuccess("查询money成功",money);
    }


    @GetMapping("/getMoney")
    public Dto getMoney(Integer id){
        if (EmptyUtils.isEmpty(id))return DtoUtil.returnFail("参数传递失败","0022");

        Money money=payService.getMoney(id);

        if(EmptyUtils.isEmpty(money))return DtoUtil.returnFail("服务器错误","0033");

        return DtoUtil.returnSuccess("查询money成功",money);
    }


    @GetMapping("/charge")
    public Dto charge(Integer id,Double num) throws Exception {
        if (EmptyUtils.isEmpty(id))return DtoUtil.returnFail("参数传递失败","0022");

        Integer result=payService.charge(id,num);

        if (result!=1)return DtoUtil.returnFail("充值失败","0022");
        return DtoUtil.returnSuccess();
    }


    @GetMapping("/test")
    public ResponseEntity test() throws Exception {

        Integer userId= RandomUtils.nextInt(1000);

        return ResponseEntity.ok(payService.test(userId,1d,"test",-2));
    }
}
