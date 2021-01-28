package com.learn.ribbonrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //必须被spring容器管理
public class MyRule {

    @Bean
    public IRule iRule(){
        return new RandomRule();//定义为随机
    }


}
