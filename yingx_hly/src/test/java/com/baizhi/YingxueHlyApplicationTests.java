package com.baizhi;

import com.baizhi.dao.AdminDao;
import com.baizhi.dao.FeedbackMapper;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Feedback;
import com.baizhi.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class YingxueHlyApplicationTests {

    @Resource
    AdminDao adminDao;
    @Resource
    FeedbackMapper feedbackMapper;


    @Test
    void contextLoads() {
        Admin ddd = adminDao.queryByUsername("hly");
        System.out.println(ddd);
    }
    @Test
    void test(){
        Feedback f = new Feedback("2","6666","ssss","12",new Date());
        feedbackMapper.insert(f);
    }

}
