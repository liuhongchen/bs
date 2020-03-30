package com.liuhongchen.bsuserprovider.service;

import com.liuhongchen.bscommonmodule.pojo.User;

import java.util.List;

public interface UserService {


    List<User> login(User user);
}
