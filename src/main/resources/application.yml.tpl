# ===================================================================
# Spring Boot configuration.
#
# This configuration will be overriden by the Spring profile you use,
# for example application-dev.yml if you use the "dev" profile.
# ===================================================================

# ===================================================================
# Standard Spring Boot properties.
# Full reference is available at:
# http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html
# ===================================================================
server:
  port: 8085
  
spring:
  application:
    name: auth
  
  #数据库连接配置
  datasource:
    name: auth
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3307/sperm
    username: admin
    password: Passw0rd

    dbcp:
      time-between-eviction-runs-millis: 60000
      min-evictable-idle-time-millis: 300000
      validation-query: select 'x' from dual
      test-while-idle: true
      test-on-borrow: false
      test-on-return: false
      pool-prepared-statements: true
      max-open-prepared-statements: 20
      min-idle: 1
      max-wait: 60000
      initial-size: 1
      max-active: 20
  
  #redis连接
#  redis:
    # Redis数据库索引（默认为0）
#    database: 12
    # Redis服务器地址
#    host: 127.0.0.1
    # Redis服务器连接端口
#    port: 6379
    # Redis服务器连接密码（默认为空）
#    password: 
    # 超时
#    timeout: 0
    # 连接池
#    pool: 
      # 连接池最大连接数（使用负值表示没有限制）
#      max-active: 50
      # 连接池最大阻塞等待时间（使用负值表示没有限制）
#      max-wait: -1
      # 连接池中的最大空闲连接
#      max-idle: 40
      # 连接池中的最小空闲连接
#      min-idle: 20

# 服务发现与注册中心eureka配置    
#eureka:
#  instance:
#    hostname: 127.0.0.1
#  client:
#    #由于该应用为注册中心,所以设置为false,代表不向注册中心注册自己
#    registerWithEureka: true
#    #从注册中心检索服务列表
#    fetchRegistry: true
#    #注册中心地址
#    serviceUrl:
#      defaultZone: http://127.0.0.1:8761/eureka/