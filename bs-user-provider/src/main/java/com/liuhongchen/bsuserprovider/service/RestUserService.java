package com.liuhongchen.bsuserprovider.service;

import com.alibaba.fastjson.JSONObject;
import com.liuhongchen.bscommonextutils.common.RedisUtils;
import com.liuhongchen.bscommonmodule.pojo.User;
import com.liuhongchen.bscommonutils.common.EmptyUtils;
import com.liuhongchen.bsuserprovider.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuhongchen
 * 所有核心业务逻辑都写在这里，尽量让consumer只进行请求转发，而不计算
 */
@RestController
public class RestUserService {

    @Autowired
    private UserMapper userMapper;

    public static final String PREFIX="cache_user:";

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping(value = "/getUserById", method = RequestMethod.POST)
    public User getUserById(@RequestParam("id") String id) throws Exception {
        String key=PREFIX+id;
//        System.out.println(redisUtils.get(key));
        User user;
        if (!EmptyUtils.isEmpty(redisUtils.get(key))){
            //直接从缓存中拿到了数据
            user= JSONObject.parseObject(redisUtils.get(key).toString(),User.class);
            return user;
        }else{
           user = userMapper.getUserById(id);
            if (EmptyUtils.isEmpty(user))return null;
           redisUtils.set(key, JSONObject.toJSONString(user));
           return user;
        }
    }

    @RequestMapping(value = "/infoCheck", method = RequestMethod.POST)
    public Boolean infoCheck(@RequestParam("id") String id) throws Exception {
        User user = getUserById(id);
        return !EmptyUtils.isEmpty(user.getPhone()) && !EmptyUtils.isEmpty(user.getEmail());
    }

    @RequestMapping(value = "/getUserListByMap", method = RequestMethod.POST)
    public List<User> getUserListByMap(@RequestParam Map<String, Object> param) throws Exception {
        return userMapper.getUserListByMap(param);
    }


    @RequestMapping(value = "/wxRegister", method = RequestMethod.POST)
    public User wxRegister(@RequestBody User user) throws Exception {
        User queryUser = getUserById(user.getId());
        String key=PREFIX+user.getId();
        Integer result;
        if (EmptyUtils.isEmpty(queryUser)) {//此时不存在该用户，需要insert
            user.setCreatedTime(new Date());
            result = userMapper.insertUser(user);
        } else {
            user.setId(queryUser.getId());
            user.setUpdatedTime(new Date());
            result=userMapper.updateUser(user);
        }
        if (result==1)redisUtils.set(key,JSONObject.toJSONString(userMapper.getUserById(user.getId())));
        return user;

    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object[] update(@RequestBody User user) throws Exception {
        Integer result = userMapper.updateUser(user);
        String key=PREFIX+user.getId();
        if (result==1)redisUtils.set(key,JSONObject.toJSONString(userMapper.getUserById(user.getId())));

        return new Object[]{result, userMapper.getUserById(user.getId())};

    }


}
