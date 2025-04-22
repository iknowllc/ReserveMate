package com.reservemate.service.impl;

import com.reservemate.mapper.UserMapper;
import com.reservemate.pojo.dto.UserLoginDto;
import com.reservemate.pojo.dto.UserRegister;
import com.reservemate.pojo.entity.User;
import com.reservemate.pojo.vo.Result;
import com.reservemate.properties.JwtProperties;
import com.reservemate.service.UserService;
import com.reservemate.utils.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 用户注册接口
     * @param userRegister
     * @return
     */
    @Override
    public Result register(UserRegister userRegister) {
        Long userId = userMapper.selectByEmail(userRegister.getEmail());
        if (userId != null) {
            return Result.failure("用户已存在");
        }
        String code = redisTemplate.opsForValue().get("email:"+userRegister.getEmail());
        if (code == null || !code.equals(userRegister.getCode())) {
            return Result.failure("验证码错误");
        }
        //todo 密码加密
        User user = new User();
        BeanUtils.copyProperties(userRegister, user);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userMapper.save(user);
        return Result.success();
    }

    /**
     * 用户登录
     * @param userLoginDto
     * @return
     */
    @Override
    public Result login(UserLoginDto userLoginDto) {
        String email = userLoginDto.getEmail();
        String password = userMapper.getPasswordByEmail(email);
        //todo 加密
        if (password == null || !password.equals(userLoginDto.getPassword())) {
            return Result.failure("密码错误");
        }
        Long id = userMapper.selectIdByEmail(email);
        //生成jwt
        Map<String, Object> claim = new HashMap<>();
        claim.put("userId", id);
        String jwt = JwtUtil.createJWT(jwtProperties.getSecretKey(), jwtProperties.getTtl(), claim);
        return Result.success(jwt);
    }
}
