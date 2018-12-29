package com.itheima.service;

import com.itheima.domain.Roles;

import java.util.List;

public interface RolesService {
    List<Roles> findAll() throws Exception;
    List<Roles> findByUserId(String uid) throws Exception;
}
