package com.liuhongchen.bsuserconsumer.service;

import com.liuhongchen.bscommonmodule.pojo.User;

public interface RegisterService {

    String wxRegister(User user) throws Exception;
}
