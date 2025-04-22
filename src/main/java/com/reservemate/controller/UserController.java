package com.reservemate.controller;

import com.reservemate.pojo.dto.EmailRequest;
import com.reservemate.pojo.dto.UserLoginDto;
import com.reservemate.pojo.dto.UserRegister;
import com.reservemate.pojo.vo.Result;
import com.reservemate.service.EmailService;
import com.reservemate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private EmailService emailService; // 注入邮件服务
    @Autowired
    private UserService userService;  // 注入用户服务

    /**
     * 通过邮箱发送验证码
     * @param emailRequest
     * @return
     */
    @PostMapping("/sendCode")
    public Result sendCode(@RequestBody EmailRequest emailRequest) {
        return emailService.sendCode(emailRequest);
    }

    /**
     * 用户注册，
     * 必要参数用户名，验证码，密码，邮箱
     * @param userRegister
     * @return
     */
    @PostMapping("/register")
    public Result<Void> register(@RequestBody UserRegister userRegister) {
        return userService.register(userRegister);
    }

    // 用户登录,用密码
    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDto userLoginDto) {
        return userService.login(userLoginDto);
    }

}
