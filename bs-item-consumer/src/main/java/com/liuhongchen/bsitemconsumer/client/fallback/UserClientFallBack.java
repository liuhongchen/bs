package com.liuhongchen.bsitemconsumer.client.fallback;

import com.liuhongchen.bscommonmodule.pojo.User;
import com.liuhongchen.bsitemconsumer.client.RestUserClient;

import java.util.List;
import java.util.Map;

/**
 * ClassName:UserClientFallBack
 * Package:com.liuhongchen.bsitemconsumer.client.fallback
 * Description:
 *
 * @date:2020-04-19 10:19
 * @author:892698613@qq.com
 */
public class UserClientFallBack implements RestUserClient {
    @Override
    public User getUserById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<User> getUserListByMap(Map<String, Object> param) throws Exception {
        return null;
    }
}
