package com.llj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llj.common.CustomException;
import com.llj.mapper.UserDao;
import com.llj.model.dto.LoginDto;
import com.llj.model.dto.RegisterDto;
import com.llj.model.pojo.User;
import com.llj.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao,User> implements IUserService {

    private static String generateUsername(){
        long time = System.currentTimeMillis();
        String username = Long.toHexString(time);
        return username;
    }

    @Override
    public User register(RegisterDto params) {
        String phone = params.getPhone();
        String validCode = params.getValidCode();
        //判断手机号是否已注册，若已存在直接返回
        String password = params.getPassword();
        QueryWrapper<User> qw = new QueryWrapper<User>().eq("phone", phone);
        if(this.getOne(qw) != null) throw new CustomException("该手机号已注册！");
        //开始注册
        User user = new User();
        user.setPhone(phone);
        user.setUsername(generateUsername());
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        this.save(user);
        return user;
    }

    @Override
    public User login(LoginDto params) {
        String username = params.getUsername();
        String password = DigestUtils.md5DigestAsHex(params.getPassword().getBytes());
        //判断用户是否存在，若不存在直接返回
        QueryWrapper<User> qw = new QueryWrapper<User>().eq("username",username);
        User user = this.getOne(qw);
        if(user == null){
            throw new CustomException("该用户不存在！");
        }
        //开始登录
        if(!user.getPassword().equals(password)){
            throw new CustomException("密码错误，请重试！");
        }
        return user;
    }

    public static void main(String[] args) throws InterruptedException{
        String filename1 = "hello"+System.currentTimeMillis();
        Thread.sleep(1);
        String filename2 = "hello"+System.currentTimeMillis();
        String s1 = DigestUtils.md5DigestAsHex(filename1.getBytes());
        String s2 = DigestUtils.md5DigestAsHex(filename2.getBytes());
        System.out.println(s1);
        System.out.println(s2);
    }
}
