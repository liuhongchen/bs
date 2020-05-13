package com.liuhongchen.bsuserprovider.mapper;

import com.liuhongchen.bscommonmodule.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * Created by liuhongchen
 */
@Mapper
public interface UserMapper {

	public User getUserById(@Param(value = "id") Long id)throws Exception;

	public List<User>	getUserListByMap(Map<String, Object> param)throws Exception;

	public Integer getUserCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertUser(User user)throws Exception;

	public Integer updateUser(User user)throws Exception;

	public Integer deleteUserById(@Param(value = "id") Long id)throws Exception;

	public Integer batchDeleteUser(Map<String, List<String>> params);

	User checkLoginByPassword(String phone, String password);


}
