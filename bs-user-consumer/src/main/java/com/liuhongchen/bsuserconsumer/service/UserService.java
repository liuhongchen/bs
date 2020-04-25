package com.liuhongchen.bsuserconsumer.service;

import com.liuhongchen.bscommonmodule.pojo.User;

/**
 * ClassName:UserService
 * Package:com.liuhongchen.bsuserconsumer.service
 * Description:
 *
 * @date:2020-04-10 19:38
 * @author:892698613@qq.com
 */
public interface UserService {

    User getUserById(Long id) ;

    Object[] updateInfo(User user);

    Double getMoney(Integer id);


}
