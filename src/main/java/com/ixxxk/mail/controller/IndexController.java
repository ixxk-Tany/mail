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
@RequestMapping("/index")
public class IndexController {
    @Resource
    private SendMailService sendMailService;

    @PostMapping("/hello")
    public Result<Object> hello(
            @RequestParam(value = "ip", required = false, defaultValue = "未获取到IP") String ip
            , @RequestParam(value = "city", required = false, defaultValue = "未获取到所在地") String city) {
        sendMailService.sendMail("5824519@qq.com", "主页访问", "", ip + " - " + city);
        return Result.success();
    }
}
