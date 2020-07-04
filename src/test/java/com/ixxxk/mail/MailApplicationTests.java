package com.ixxxk.mail;

import com.ixxxk.mail.mapper.HelloMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class MailApplicationTests {
    @Resource
    private HelloMapper helloMapper;


    @Test
    void contextLoads() {
        System.out.println(helloMapper.findByYesterday());
    }

}
