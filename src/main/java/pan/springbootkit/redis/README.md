# Redis

1. 添加 ```spring-boot-starter-redis``` 依赖
```
<!-- Redis -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-redis</artifactId>
    <version>1.3.2.RELEASE</version>
</dependency>
```

2. ```*.yml``` 文件添加配置如下
```
spring:
  # redis
  redis:
    database: 0
    host: 127.0.0.1
    port: 6379
#    password: 123456
    jedis:
      pool:
        max-idle: 80
        min-idle: 0
        max-active: 80
        max-wait: -1s
    timeout: 10s
```

3. 引用 ```RedisService.java``` 即可使用