package com.llj.model.vo;

import lombok.Data;

@Data
public class LoginVO {
    private Long userId;
    private String username;
    private String phone;
    private String token;
}
