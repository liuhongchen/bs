package com.liuhongchen.bsuserconsumer.service;

import com.liuhongchen.bscommondto.vo.UserVo;
import com.liuhongchen.bscommonmodule.pojo.User;

public interface RegisterService {

    UserVo wxRegister(User user) throws Exception;
}
