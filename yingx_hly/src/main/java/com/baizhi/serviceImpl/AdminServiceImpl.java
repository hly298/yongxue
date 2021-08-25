package com.baizhi.serviceImpl;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@Transactional
@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);
    @Resource
    AdminDao adminDao;
    @Resource
    HttpServletRequest request;

    @Override
    public HashMap<String, Object> login(Admin admin, String enCode) {
        //获取验证码
        String code = (String)request.getServletContext().getAttribute("code");

        log.info("用户输入的验证码：{}",enCode);
        log.info("获取存储的验证码：{}",code);

        HashMap<String,Object> map = new HashMap();
        if(code.equals(enCode)){
            Admin a = adminDao.queryByUsername(admin.getUsername());
            if(a!= null){
                if(a.getStatus().equals("1")){
                    if(admin.getPassword().equals(a.getPassword())){
                        request.getServletContext().setAttribute("admin",a);

                        map.put("message",a);
                        map.put("status",200);
                    }else{
                        map.put("message","密码错误");
                        map.put("status",400);
                    }
                }else{
                    map.put("message","该用户已被冻结请联系超级管理员");
                    map.put("status",400);
                }
            }else{
                map.put("message","用户不存在");
                map.put("status",400);
            }
        }else{
            map.put("message","验证码不正确");
            map.put("status",400);
        }
        return map;
    }
}
