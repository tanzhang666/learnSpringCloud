package com.learn.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    //feign日志支持 有四种级别 none basic header full 默认为none
    @Bean
    Logger.Level level(){
        return Logger.Level.FULL;
    }


}
