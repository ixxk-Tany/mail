package com.ixxxk.mail.controller;

import com.ixxxk.mail.pojo.vo.Result;
import com.ixxxk.mail.service.SendMailService;
import com.ixxxk.mail.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    public Result<Object> sendMail(
            @RequestParam(value = "toUser") String toUser
            , @RequestParam(value = "subject") String subject
            , @RequestParam(value = "text") String text
            , HttpServletRequest request) {
        log.info("send...............");
        String ipAddr = IpUtil.getIpAddr(request);
        boolean b = sendMailService.sendMail("5824519@qq.com", subject+":"+toUser, text, ipAddr);
        try {
            sendMailService.sendMail(toUser,"来自www.ixxxk.com的回信","您的消息已收到，感谢您的来信。","");
        }catch (Exception e){
            
        }
        return b ? Result.success() : Result.error("-1", "邮件发送失败，请稍后再试...");
    }
}
