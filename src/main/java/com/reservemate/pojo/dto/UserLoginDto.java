package com.reservemate.pojo.dto;

import lombok.Data;

@Data
public class UserLoginDto {
    private String email;//邮箱
    private String password;//密码
}
