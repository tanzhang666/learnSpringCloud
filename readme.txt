项目技术栈版本
Spring Cloud Hoxton.SR1
Spring Boot 2.2.2.RELEASE
cloud alibaba 2.1.0.RELEASE
Java java8
Maven 3.5及以上
Mysql 5.7及以上

cloud升级
服务注册中心：
eureka 停止更新 很多老项目用 netflix公司
Zookeeper 传统推荐
consul
nacos alibaba
三者区别以及如何选择
什么是服务治理
Spring Cloud封装了Netflix公司开发的Eureka模块来实现服务治理，
在传统的rpc远程调用框架中，管理每个服务与服务之间比较复杂，
所以需要服务治理来管理服务之间的依赖关系，可以实现服务调用，负载均衡，容错等，实现服务发现及注册。
服务调用：
rubbon
loadBalancer
feign 停止维护
OpenFeign 推荐使用
服务降级：
Hystrix 停止更新 国内仍有很多公司再用
Resilience 国外推荐
sentinel alibaba 推荐
服务网关：
zuul 停止更新
zuul2 跳票
gateway 推荐 spring官方
服务配置：
config 停止更新
nacos
服务总线
bus 停止更新
nacos