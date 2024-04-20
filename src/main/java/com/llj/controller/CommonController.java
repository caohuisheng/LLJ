package com.llj.controller;

import com.llj.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传和下载
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

    @Value("${llj.filepath}")
    private String basePath;

    @PostMapping("upload")
    public R<Object> upload(MultipartFile file){
        //原始文件名
        String originFileName = file.getOriginalFilename();
        String suffix = originFileName.substring(originFileName.lastIndexOf("."));
        String prefix =  originFileName.substring(0,originFileName.lastIndexOf("."))+System.currentTimeMillis();
        //生成新文件名
        String fileName = DigestUtils.md5DigestAsHex(prefix.getBytes()) +suffix;
        //根据文件名生成子目录
        String dirPath = basePath+fileName.charAt(0)+'/'+fileName.charAt(1)+'/';
        //创建文件目录
        File dir=new File(dirPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        //将上传的文件保存到指定位置
        try{
            file.transferTo(new File(dirPath+fileName));
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return R.success(fileName);
    }

    @GetMapping("download")
    public R<String> download(String fileName, HttpServletResponse response){
        String path=basePath+fileName.charAt(0)+'/'+fileName.charAt(1)+'/'+fileName;
        try(
                FileInputStream fis = new FileInputStream(new File(path));
                ServletOutputStream sos = response.getOutputStream()
        ){
            byte[] buf=new byte[1024];
            int len=0;
            while((len=fis.read(buf))!=-1){
                sos.write(buf,0,len);
                sos.flush();
            }
        }catch(IOException ex){
            ex.printStackTrace();
            return R.error(ex.getMessage());
        }
        return R.success("下载成功！");
    }

    @PostMapping("/sendcode")
    public R<String> sendCode(String phone, HttpServletRequest request){
        //todo: 发送验证码给指定手机号
        //保存验证码到session中
        request.getSession().setAttribute(phone,111111);
        return R.success("发送成功！");
    }
}
