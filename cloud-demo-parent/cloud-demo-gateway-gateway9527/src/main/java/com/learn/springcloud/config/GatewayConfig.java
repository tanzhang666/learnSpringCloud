package com.learn.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    /**
     *     当访问localhost:9527/guonei时路由转发到https://news.baidu.com/guonei
     */
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_route1",r -> r.path("/guonei")
                .uri("https://news.baidu.com/guonei")).build();
        return routes.build();
    }
}
