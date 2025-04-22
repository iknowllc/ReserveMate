package com.reservemate.service;

import com.reservemate.pojo.dto.UserLoginDto;
import com.reservemate.pojo.dto.UserRegister;
import com.reservemate.pojo.vo.Result;

public interface UserService {
    /**
     * 用户注册接口
     * @param userRegister
     * @return
     */
    Result register(UserRegister userRegister);

    /**
     * 用户登录
     * @param userLoginDto
     * @return
     */
    Result login(UserLoginDto userLoginDto);
}
