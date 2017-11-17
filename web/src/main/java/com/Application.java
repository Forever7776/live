package com;

import org.aspectj.lang.annotation.Aspect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.filter.CharacterEncodingFilter;
import tools.util.JDKRedisTemplate;

import javax.servlet.Filter;

@Configuration
@EnableAutoConfiguration
@EnableAsync
@MapperScan("com.kz.persistence")
@PropertySource({"classpath:application.yml"})
@ComponentScan(basePackages = "com.kz", includeFilters = {@ComponentScan.Filter(Aspect.class)})
//@ImportResource({"classpath:dubbo.xml"})
//@ImportResource({"classpath:dubbo.xml", "classpath:rabbitmq.xml"})
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }


    // 用于处理编码问题
    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    @Bean
    public JDKRedisTemplate jdkRedisTemplate(RedisConnectionFactory connectionFactory) {
        JDKRedisTemplate jdkRedisTemplate = new JDKRedisTemplate();
        jdkRedisTemplate.setConnectionFactory(connectionFactory);
        return jdkRedisTemplate;
    }

   /* @Bean(name = "syncZkClient")
    public ZkClient client(@Value("${zookeeper.host}")String zookeeperConnectionString){
        ZkClient zkClient = new ZkClientImpl(zookeeperConnectionString);
        zkClient.connect();
        return zkClient;
    }*/

}
