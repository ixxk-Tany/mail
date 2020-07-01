package com.ixxxk.mail.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @description
 * @author: Tany
 * @email: 5824519@qq.com
 * @createDate: 2020/7/1
 * @version: 1.0.0
 */
@Configuration
public class CorsConfig {

    @Value("${api.corss.open}")
    private boolean openCors;

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        if(openCors){
            source.registerCorsConfiguration("/**", buildConfig());
        }
        return new CorsFilter(source);
    }
}
