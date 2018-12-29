package com.itheima.service.impl;

import com.itheima.domain.Roles;
import com.itheima.mapper.RolesMapper;
import com.itheima.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class RolesServiceImpl implements RolesService {
    @Autowired
    private RolesMapper rolesMapper;
    @Override
    public List<Roles> findAll() throws Exception {
        return rolesMapper.findAll();
    }

    @Override
    public List<Roles> findByUserId(String uid) throws Exception {
        return rolesMapper.findByUserId(uid);
    }
}
