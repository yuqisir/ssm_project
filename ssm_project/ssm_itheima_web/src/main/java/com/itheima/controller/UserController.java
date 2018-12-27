package com.itheima.controller;

import com.itheima.domain.UserInfo;
import com.itheima.service.UserService;
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
}
