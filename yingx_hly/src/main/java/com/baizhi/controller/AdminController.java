package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.util.ImageCodeUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("admin")
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Resource
    AdminService adminService;

    @PostMapping("getImageCodes")
    public String getImageCode(HttpServletRequest request){
        //获取验证码随机字符
        String code = ImageCodeUtil.getSecurityCode();
        log.info("验证码为：{}",code);
        //存储验证码随机字符
        request.getServletContext().setAttribute("code",code);

        String imgBase64 = null;
        
        //生成验证码图片将验证码图片转为Base64格式
        try {
            imgBase64 = ImageCodeUtil.careateImgBase64(code);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgBase64;
    }

    @PostMapping("login")
    public HashMap<String,Object> login(@RequestBody Admin admin, String enCode){
        log.info("接收用户输入的admin:{}",admin);
        log.info("接收用户输入的验证码:{}",enCode);

        HashMap<String,Object> map = adminService.login(admin,enCode);
        return map;
    }

    @PostMapping("logout")
    public void logout(HttpServletRequest request){
        log.info("退出登录");
        request.getServletContext().removeAttribute("admin");
    }

}
