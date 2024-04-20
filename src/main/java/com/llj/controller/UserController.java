package com.llj.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.llj.common.BaseContext;
import com.llj.common.CustomException;
import com.llj.common.R;
import com.llj.model.dto.LoginDto;
import com.llj.model.dto.RegisterDto;
import com.llj.model.pojo.User;
import com.llj.model.vo.LoginVO;
import com.llj.properties.JwtProperties;
import com.llj.service.IUserService;
import com.llj.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/register")
    private R<String> register(@RequestBody RegisterDto params,HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute(params.getPhone()).equals(params.getValidCode())){
            throw new CustomException("验证码错误！");
        }
        User user = userService.register(params);
        return R.success("username:"+user.getUsername());
    }

    @PostMapping("/login")
    public R<LoginVO> login(@RequestBody LoginDto params){
        //进行登录
        User user = userService.login(params);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", user.getId());
        String token = JwtUtils.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);
        //保存用户id到BaseContext
        BaseContext.setCurrentId(user.getId());

        //返回用户基本信息和令牌
        LoginVO loginVO = new LoginVO();
        loginVO.setUserId(user.getId());
        loginVO.setPhone(user.getPhone());
        loginVO.setUsername(user.getUsername());
        loginVO.setToken(token);
        return R.success(loginVO);
    }

    @PostMapping("/logout")
    public R<String> logout(){
        BaseContext.removeCurrentId();
        return R.success("退出登录成功！");
    }

    @PostMapping("/user/edit")
    public R<String> edit(@RequestBody User user){
        userService.updateById(user);
        return R.success("修改用户信息成功！");
    }

    @PostMapping("/user/revoke")
    public R<String> revoke(){
        userService.removeById(BaseContext.getCurrentId());
        return R.success("注销成功！");
    }

    @GetMapping("/user/{userId}")
    public R<User> queryById(@PathVariable Long userId){
        User user = userService.getById(userId);
        return R.success(user);
    }

    @GetMapping("/user/page")
    public R<IPage> queryByPageAndCondition(String keyword,int start,int pageSize){
        IPage<User> page = new Page<>(start,pageSize);
        QueryWrapper<User> qw = new QueryWrapper<User>().like(StringUtils.isNotBlank(keyword),"name",keyword);
        userService.page(page,qw);
        return R.success(page);
    }

}
