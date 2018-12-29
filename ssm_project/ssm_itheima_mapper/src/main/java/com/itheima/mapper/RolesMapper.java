package com.itheima.mapper;

import com.itheima.domain.Roles;

import java.util.List;

public interface RolesMapper {
    List<Roles> findByUserId(String uid) throws Exception;
    List<Roles> findAll() throws Exception;
}
