package com.ixxxk.mail.task;

import com.ixxxk.mail.service.SendMailService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 描述: 
 * @Author: Tany
 * @CreateDate: 2020/7/5
 * @UpdateUser: Tany
 * @UpdateDate: 2020/7/5
 * @Version: 3.0.1
 */
@Component
public class SendTask {
    @Resource
    private SendMailService sendMailService;

    @Scheduled(cron = "0 0 0 * * ?")
//    @Scheduled(fixedRate = 5000)
    public void send() {
        sendMailService.sendHello();
    }
}
