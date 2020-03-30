package com.liuhongchen.bscommondao.mapper;

import com.liuhongchen.bscommonmodule.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> login(User user);


}
