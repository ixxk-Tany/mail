package com.ixxxk.mail.controller;

import com.ixxxk.mail.pojo.vo.Result;
import com.ixxxk.mail.service.SendMailService;
import com.ixxxk.mail.util.IpUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/index")
public class IndexController {
    @Resource
    private SendMailService sendMailService;

    @PostMapping("/hello")
    public Result<Object> hello(HttpServletRequest request) {
        String ipAddr = IpUtil.getIpAddr(request);
        sendMailService.sendMail("5824519@qq.com", "主页访问", "", ipAddr);
        return Result.success();
    }
}
