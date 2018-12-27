package com.itheima.mapper;

import com.itheima.domain.UserInfo;

import java.util.List;

public interface UserMapper {
    UserInfo findByUsername(String username) throws Exception;
    //遍历所有用户
    List<UserInfo> findAll() throws Exception;

    void save(UserInfo userInfo) throws Exception;
}
