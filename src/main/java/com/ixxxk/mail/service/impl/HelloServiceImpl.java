package com.ixxxk.mail.service.impl;

import com.ixxxk.mail.pojo.dto.HelloInfoDto;
import com.ixxxk.mail.pojo.entity.HelloInfo;
import com.ixxxk.mail.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * 描述: 
 * @Author: Tany
 * @CreateDate: 2020/7/5
 * @UpdateUser: Tany
 * @UpdateDate: 2020/7/5
 * @Version: 3.0.1
 */
@Slf4j
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public boolean save(HelloInfoDto helloInfoDto) {
        HelloInfo hellolInfo = new HelloInfo();
        hellolInfo.setId(UUID.randomUUID().toString());
        hellolInfo.setIp(helloInfoDto.getIp());
        hellolInfo.setCity(helloInfoDto.getCity());
        hellolInfo.setCreateTime(new Date());
        return hellolInfo.insert();
    }
}
