package com.ixxxk.mail.controller;

import com.ixxxk.mail.pojo.dto.MailDto;
import com.ixxxk.mail.pojo.vo.Result;
import com.ixxxk.mail.service.SendMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@Slf4j
public class MailController {
    @Resource
    private SendMailService sendMailService;

    @PostMapping("/send")
    public Result<Object> sendMail(@RequestBody MailDto mailDto) {
        boolean send = sendMailService.send(mailDto);
        return send ? Result.success() : Result.error("-1", "邮件发送失败，请稍后再试...");
    }
}
