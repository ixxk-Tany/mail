package com.ixxxk.mail.service.impl;

import com.ixxxk.mail.mapper.HelloMapper;
import com.ixxxk.mail.pojo.dto.HelloInfoDto;
import com.ixxxk.mail.pojo.entity.HelloInfo;
import com.ixxxk.mail.service.HelloService;
import com.ixxxk.mail.service.SendMailService;
import com.ixxxk.mail.util.Consts;
import com.ixxxk.mail.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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


    @Resource
    private HelloMapper helloMapper;
    @Resource
    private SendMailService sendMailService;

    @Override
    public boolean save(HelloInfoDto helloInfoDto) {
        HelloInfo helloInfo = new HelloInfo();
        helloInfo.setIp(helloInfoDto.getIp());
        helloInfo.setCity(helloInfoDto.getCity());
        helloInfo.setCreateTime(new Date());
        boolean insert = helloInfo.insert();
        if (insert) {
            int between = 5;
            long l = helloInfo.getId() % between;
            if (l == 0) {
                List<HelloInfo> helloInfoList = helloMapper.findByIdBetween(helloInfo.getId() - between, helloInfo.getId());
                SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.YYYY_MM_DD_HH_MM_SS_SSS);
                String subject = "访客统计";
                StringBuffer sb = new StringBuffer();
                for (HelloInfo info : helloInfoList) {
                    sb.append("ip：").append(info.getIp());
                    sb.append(Consts.BR);
                    sb.append("城市：").append(info.getCity());
                    sb.append(Consts.BR);
                    sb.append("时间：").append(sdf.format(info.getCreateTime()));
                    sb.append("---------------------------------------------------------------");
                    sb.append(Consts.BR);
                }
                sendMailService.sendMail(Consts.MY_EMAIL, subject, sb.toString(), true);
            }
        }
        return insert;
    }
}
