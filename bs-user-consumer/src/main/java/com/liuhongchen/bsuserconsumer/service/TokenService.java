package com.liuhongchen.bsuserconsumer.service;

import com.liuhongchen.bscommondto.vo.UserVo;
import com.liuhongchen.bscommonmodule.pojo.User;

public interface TokenService {

    String token(User user);
}
