package com.sky.auth.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 * Desc： MyBatis扫描接口
 * 注意，由于MapperScannerConfigurer执行的比较早，所以必须有注解@AutoConfigureAfter(MyBatisConfig.class)
 * </pre>
 * 
 * @author yangfan
 * 
 */
//@Deprecated   //使用mybatis-spring-boot-starter,不再需要配置
@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.sky.auth.*.dao");
        return mapperScannerConfigurer;
    }

}
