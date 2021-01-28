此模块为spring cloud eureka注册中心示例程序的消费者模块
依赖：
cloud-demo-api-commons 公共实体类
spring-cloud-starter-eureka-client 提供eureka注册中心支持 同时包含提供负载均衡的ribbon
核心配置项：
server.port
spring.application.name
eureka.instance.hostname
eureka.client:register-with-eureka=true
eureka.client.fetch-registry=true
eureka.service-url.defaultZone=http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka,http://eureka7003.com:7003/eureka
注解：
@EnableEurekaClient 注解到主程序类
@LoadBalancer 负载均衡 多个eureka服务端 可自动选择
服务调用：
可通过restTemplate（对httpclient进行了再封装）进行远程服务调用
地址使用服务提供者注册名进行调用

ribbon的核心组件Irule
根据特定算法从服务列表中选择一个要访问的服务
ribbon默认的负载均衡算法为轮询 roundRobinRule
自带的负载规则有七种
RoundRobinRule 轮询
RetryRule 重试
RandomRule 随机
BestAvailableRule
负载规则替换配置：
自定义配置类不能放在@ComponentScan所扫描的包以及子包下 否则我们自定义的这个配置类就会被所有的ribbon客户端共享 达不到特殊化定制的目的了
