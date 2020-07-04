package com.ixxxk.mail.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Tany
 * @Date: 2019/12/14 23:58
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * 乐观锁 插件
     *
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLoker() {
        return new OptimisticLockerInterceptor();
    }
}
