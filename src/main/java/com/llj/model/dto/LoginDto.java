package com.llj.model.dto;

import lombok.Data;

@Data
public class LoginDto {
    private Integer type;
    private String username;
    private String password;
}
