package com.reservemate.pojo.entity;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Long userId;   // 用户ID
    private String username;  // 用户名
    private String password;  // 密码
    private String email;     // 邮箱
    private String phone;     // 电话号码
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
