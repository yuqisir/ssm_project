package com.itheima.controller;

import com.itheima.domain.Roles;
import com.itheima.domain.UserInfo;
import com.itheima.service.RolesService;
import com.itheima.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RolesService rolesService;
    //注入加密工具对象
    @Autowired
    private PasswordEncoder passwordEncoder;
    @RequestMapping("/findAll")
    public String findAll(Model model) throws Exception {
        List<UserInfo> userInfos = userService.findAll();
        model.addAttribute("userList",userInfos);
        return "user-list";
    }
    @RequestMapping("/save")
    public String save(UserInfo userInfo) throws Exception {
        //获取页面的密码是明文，需要加密处理之后再进入保存
        String encodePwd = passwordEncoder.encode(userInfo.getPassword());
        userInfo.setPassword(encodePwd);
        userService.save(userInfo);
        return "redirect:/user/findAll";
    }

    @RequestMapping("/findById")
    public String findById(String id, Model model) throws Exception {
        UserInfo userInfo=userService.findById(id);
        model.addAttribute("user",userInfo);
        return "user-update";
    }

    @RequestMapping("/updateUser")
    public String updateUser(UserInfo userInfo,Integer flag) throws Exception {

//        if(!oldPwd.equals(userInfo.getPassword())){//判断页面密码是否被修改，如果修改则加密处理，如果没有修改则以原密文密码存储
//            //修改密码之后的密码加密处理
//            String encodePwd = passwordEncoder.encode(userInfo.getPassword());
//            userInfo.setPassword(encodePwd);
//        }
        if(flag==1){
            String encodePwd = passwordEncoder.encode(userInfo.getPassword());
            userInfo.setPassword(encodePwd);
        }
        userService.updateByUsername(userInfo);
        return "redirect:/user/findAll";
    }

    /////////////////////角色处理/////////////////////////////
    @RequestMapping("/findRoles")
    public String findRoles(String id,Model model) throws Exception {
        List<Roles> rolesList = rolesService.findAll();//flag=null  a  b   c  d
        //根据ID查询当前用户所拥有的角色
        List<Roles> userRoles = rolesService.findByUserId(id);// a d
        for(Roles role:rolesList){
           for(Roles userRole:userRoles){
               if(role.getId().equals(userRole.getId())){
                   role.setFlag(1);
               }
           }
        }
        model.addAttribute("roleList",rolesList);
        return "user-role-add";
    }


    @RequestMapping("/findDetail")
    public String findDetail(String username,Model model) throws Exception{
        UserInfo user = userService.findByUsername(username);
        model.addAttribute("user",user);
        return "user-show";
    }
}
