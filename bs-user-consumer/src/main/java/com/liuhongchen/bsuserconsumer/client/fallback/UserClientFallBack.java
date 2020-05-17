package com.liuhongchen.bsuserconsumer.client.fallback;

import com.liuhongchen.bscommonmodule.pojo.User;
import com.liuhongchen.bsuserconsumer.client.RestUserClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserClientFallBack implements RestUserClient {


    @Override
    public User getUserById(String id) throws Exception {
        return null;
    }

    @Override
    public List<User> getUserListByMap(Map<String, Object> param) throws Exception {
        return null;
    }

    @Override
    public String generateToken(User user) {
        return null;
    }

    @Override
    public User wxRegister(User user) throws Exception {
        return null;
    }

    @Override
    public Object[] update(User user) throws Exception {
        return new Object[0];
    }


    @Override
    public Boolean infoCheck(String id) {
        return null;
    }
}
