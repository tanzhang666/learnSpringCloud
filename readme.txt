项目技术栈版本
Spring Cloud Hoxton.SR1
Spring Boot 2.2.2.RELEASE
cloud alibaba 2.1.0.RELEASE
Java java8
Maven 3.5及以上
Mysql 5.7及以上

cloud升级
第一部分：
服务注册中心：
eureka 停止更新 很多老项目用 netflix公司
Zookeeper 传统推荐
consul
nacos alibaba 推荐使用
三者区别以及如何选择
什么是服务治理
Spring Cloud封装了Netflix公司开发的Eureka模块来实现服务治理，
在传统的rpc远程调用框架中，管理每个服务与服务之间比较复杂，
所以需要服务治理来管理服务之间的依赖关系，可以实现服务调用，负载均衡，容错等，实现服务发现及注册。
注册服务器的高可用就是 相互注册 互相守望
eureka
#  注册中心关闭自我保护机制，修改检查失效服务的时间。
# eureka:
#  server:
#    enable-self-preservation: false
#    eviction-interval-timer-in-ms: 3000
#  2、 微服务修改减短服务心跳的时间。
# 默认90秒
# lease-expiration-duration-in-seconds:  10
# 默认30秒
# lease-renewal-interval-in-seconds:  3
第二部分：
服务调用：
ribbon 维护模式（停止更新 修复blocked级别的bug）netflix
是基于netflix ribbon实现的一套客户端 在消费者侧负载均衡的工具
简单来说 ribbon是netflix开发的开源项目 主要功能是提供客户端软件的软件负载均衡算法和服务调用
ribbon客户端组件提供一系列的配置项如连接超时 重试
简单地说就是在配置文件中列出loadbalancer后面的机器，ribbon自动帮助你基于某种规则去连接这些机器
ribbon和ngnix区别
ngnix是服务器端负载均衡 客户端请求都会交给ngnix 然后由ngnix实现转发 即负载均衡是由服务器端实现的
ribbon本地负载均衡 在调用微服务接口时
loadBalancer 未来替代方案 spring官方
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