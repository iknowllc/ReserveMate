package com.reservemate.pojo.dto;

import lombok.Data;

@Data
public class UserRegister {
    private String email;
    private String username;
    private String password;
    private String phone;
    private String code;
}
