package com.liuhongchen.bsitemconsumer.client.fallback;

import com.liuhongchen.bscommonmodule.pojo.User;
import com.liuhongchen.bsitemconsumer.client.RestUserClient;
import org.springframework.stereotype.Component;

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
@Component
public class UserClientFallBack implements RestUserClient {


    @Override
    public User getUserById(String id) throws Exception {
        return null;
    }

}
