package com.llj.model.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.util.Collection;

@Data
public class User {
    private Long id;
    private Integer type; //类型：1-大学生 2-老人
    private String username; //用户名
    private String name;
    private String nickname;
    private LocalDate birth;
    private Integer sex; //性别：0-男 1-女
    private String phone;
    private String email;
    private String password;
    private String avatar;
    private String hometown;
    private String strength;
    private String university;
    private Integer grade; //年级：1-大一 2-大二...
}
