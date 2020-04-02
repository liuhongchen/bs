package com.liuhongchen.bsuserprovider.service;

import com.liuhongchen.bscommonmodule.pojo.User;
import com.liuhongchen.bscommonutils.common.EmptyUtils;
import com.liuhongchen.bsuserprovider.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

     @RequestMapping(value = "/getUserById",method = RequestMethod.POST)
     public User getUserById(@RequestParam("id") Long id)throws Exception{
        return userMapper.getUserById(id);
     }

     @RequestMapping(value = "/getUserListByMap",method = RequestMethod.POST)
     public List<User>	getUserListByMap(@RequestParam Map<String,Object> param)throws Exception{
        return userMapper.getUserListByMap(param);
     }

     @RequestMapping(value = "/getUserCountByMap",method = RequestMethod.POST)
     public Integer getUserCountByMap(@RequestParam Map<String,Object> param)throws Exception{
        return userMapper.getUserCountByMap(param);
     }

     @RequestMapping(value = "/qdtxAddUser",method = RequestMethod.POST)
     public Integer qdtxAddUser(@RequestBody User user)throws Exception{
        user.setCreatedTime(new Date());
        return userMapper.insertUser(user);
     }

     @RequestMapping(value = "/qdtxModifyUser",method = RequestMethod.POST)
     public Integer qdtxModifyUser(@RequestBody User user)throws Exception{
        user.setUpdatedTime(new Date());
        return userMapper.updateUser(user);
     }

    /**
     * 注意一定要在参数列表里面写 @RequestBody
     * @param user
     * @return
     */
    @RequestMapping(value = "/checkLoginByPassword", method = RequestMethod.POST)
    public User checkLoginByPassword(@RequestBody User user){
        User user1 = userMapper.checkLoginByPassword(user.getPhone(), user.getPassword());
        return user1;

    }


    @RequestMapping(value = "/wxRegister", method = RequestMethod.POST)
    public User wxRegister(@RequestBody User user) throws Exception {
        Map<String,Object> map=new HashMap<>();
        map.put("wxUserId",user.getWxUserId());
        List<User> users = userMapper.getUserListByMap(map);
        if (EmptyUtils.isEmpty(users)) {//此时不存在该用户，需要insert
            user.setCreatedTime(new Date());
            userMapper.insertUser(user);
        }else {
            user.setId(users.get(0).getId());
            user.setUpdatedTime(new Date());
            userMapper.updateUser(user);
        }
        return user;//这个user是自动生成key的


    }


}
