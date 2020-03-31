package com.liuhongchen.bscommonclient.fallback;

import com.liuhongchen.bscommonclient.client.UserClient;
import com.liuhongchen.bscommonmodule.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallBack implements UserClient {
    @Override
    public User login() throws Exception {
        User user=new User();
        user.setPhone("bad-phone");
        return user;
    }
//    @Override
//    public User login(String phone, String password) throws Exception {
//        User user=new User();
//        user.setPhone("bad-phone");
//        return user;
//    }

}
