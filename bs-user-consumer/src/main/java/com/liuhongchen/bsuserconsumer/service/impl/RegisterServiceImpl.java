package com.liuhongchen.bsuserconsumer.service.impl;

import com.liuhongchen.bscommondto.vo.UserVo;
import com.liuhongchen.bscommonmodule.pojo.User;
import com.liuhongchen.bscommonutils.common.EmptyUtils;
import com.liuhongchen.bsuserconsumer.client.RestPayClient;
import com.liuhongchen.bsuserconsumer.client.RestUserClient;
import com.liuhongchen.bsuserconsumer.service.RegisterService;
import com.liuhongchen.bsuserconsumer.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RestUserClient userClient;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private RestPayClient payClient;


    //TODO:这里需要分布式事务
    @Override
    public UserVo wxRegister(User user) throws Exception {
        //直接传到provider然后让provider判断，√
        User queryUser = userClient.wxRegister(user);
        if (EmptyUtils.isEmpty(queryUser.getId()))return null;
        System.out.println(queryUser.getId());
        Integer createAccountResult=payClient.createAccount(queryUser.getId());

        return tokenService.token(queryUser);
    }
}
