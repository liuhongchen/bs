package com.liuhongchen.bsuserconsumer.service.impl;

import com.liuhongchen.bscommonmodule.pojo.User;
import com.liuhongchen.bsuserconsumer.client.RestUserClient;
import com.liuhongchen.bsuserconsumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName:UserServiceImpl
 * Package:com.liuhongchen.bsuserconsumer.service.impl
 * Description:
 *
 * @date:2020-04-10 19:38
 * @author:892698613@qq.com
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestUserClient userClient;


    @Override
    public User getUserById(Long id) {
        try {
            return userClient.getUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object[] updateInfo(User user) {

        try {
            return userClient.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
