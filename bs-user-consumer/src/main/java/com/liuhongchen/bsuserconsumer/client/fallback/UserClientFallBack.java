package com.liuhongchen.bsuserconsumer.client.fallback;

import com.liuhongchen.bscommonmodule.pojo.User;
import com.liuhongchen.bsuserconsumer.client.RestUserClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserClientFallBack implements RestUserClient {


    @Override
    public User getUserById(Long id)throws Exception{
        return null;
    }

    @Override
    public List<User>	getUserListByMap(Map<String,Object> param)throws Exception{
        return null;
    }

    @Override
    public Integer getUserCountByMap(Map<String,Object> param)throws Exception{
        return null;
    }

    @Override
    public Integer qdtxAddUser(User user)throws Exception{
        return null;
    }

    @Override
    public Integer qdtxModifyUser(User user)throws Exception{
        return null;
    }

    @Override
    public User checkLoginByPassword(User user) {
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
}
