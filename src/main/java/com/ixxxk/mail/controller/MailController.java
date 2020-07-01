package com.ixxxk.mail.controller;

import com.ixxxk.mail.pojo.vo.Result;
import com.ixxxk.mail.service.SendMailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description
 * @author: Tany
 * @email: 5824519@qq.com
 * @createDate: 2020/7/1
 * @version: 1.0.0
 */
@RestController
@RequestMapping("/sys/mail/")
public class MailController {
    @Resource
    private SendMailService sendMailService;

    @PostMapping("/send")
    public Result<Object> sendMail(@RequestParam(value = "toUser")String toUser, @RequestParam(value = "subject")String subject, @RequestParam(value = "text") String text){
        boolean b = sendMailService.sendMail(toUser, subject, text);
        return b? Result.success(): Result.error("-1","邮件发送失败，请稍后再试...");
    }
}
