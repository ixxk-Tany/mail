package com.ixxxk.mail.controller;

import com.ixxxk.mail.pojo.dto.HelloInfoDto;
import com.ixxxk.mail.pojo.vo.Result;
import com.ixxxk.mail.service.HelloService;
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
@Slf4j
@RestController
@RequestMapping("/index")
public class IndexController {
    @Resource
    private HelloService helloService;

    @PostMapping("/hello")
    public Result<Object> hello(@RequestBody HelloInfoDto helloInfoDto) {
        try {
            helloService.save(helloInfoDto);
        } catch (Exception e) {
            log.error("", e);
        }
        return Result.success();
    }
}
