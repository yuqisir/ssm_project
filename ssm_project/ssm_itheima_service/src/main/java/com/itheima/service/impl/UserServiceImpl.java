package com.itheima.service.impl;

import com.itheima.domain.Roles;
import com.itheima.domain.UserInfo;
import com.itheima.mapper.UserMapper;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo=null;
        try {
            userInfo=userMapper.findByUsername(username);//根据用户名查询用户信息。注意：用户添加时要保证用户名唯一
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user=new User(userInfo.getUsername(),userInfo.getPassword(),
                            userInfo.getStatus()==1?true:false,true,true,
                            true,getRoles(userInfo.getRoles()));
        return user;
    }


    public List<SimpleGrantedAuthority> getRoles(List<Roles> rolesList){
        List<SimpleGrantedAuthority> roles=new ArrayList<SimpleGrantedAuthority>();
        for (Roles role:rolesList){
            SimpleGrantedAuthority sga=new SimpleGrantedAuthority("ROLE_"+role.getRoleName());
            roles.add(sga);
        }
        return roles;
    }
///////////////////////////////////以上是security安全认证登陆操作////////////////////////////////////////////////////


    @Override
    public List<UserInfo> findAll() throws Exception {
        return userMapper.findAll();
    }

    @Override
    public void save(UserInfo userInfo) throws Exception{
        userMapper.save(userInfo);
    }
}
