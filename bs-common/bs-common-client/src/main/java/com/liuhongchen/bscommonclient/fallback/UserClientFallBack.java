
package com.liuhongchen.bscommonclient.fallback;

import org.springframework.stereotype.Component;

import com.liuhongchen.bscommonmodule.pojo.User;

import java.util.List;
import java.util.Map;
@Component
public class UserClientFallBack implements com.liuhongchen.bsuserconsumer.service.RestUserClient {


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
}
