package com.liuhongchen.bsuserprovider.mapper;

import com.liuhongchen.bscommonmodule.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {


//    @Select("select * from user")
    List<User> login(User user);


}
