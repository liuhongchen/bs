package com.liuhongchen.bsuserprovider.service.impl;

import com.liuhongchen.bscommondao.mapper.UserMapper;
import com.liuhongchen.bscommonmodule.pojo.User;
import com.liuhongchen.bscommonutils.common.EmptyUtils;
import com.liuhongchen.bsuserprovider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> login(User user) {
        if (EmptyUtils.isEmpty(user)) return new ArrayList<>();
        return userMapper.login(user);
    }
}
