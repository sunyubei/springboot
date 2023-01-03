package com.nynu.springboot.dao;

import com.nynu.springboot.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User> {


    List<User> getPageList(User user);

}
