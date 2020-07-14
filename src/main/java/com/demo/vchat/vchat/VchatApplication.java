package com.demo.vchat.vchat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "com.demo")
@MapperScan("com.demo.vchat.vchat.mapper")
public class VchatApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(VchatApplication.class);
    }


    public static void main(String[] args) {

        SpringApplication.run(VchatApplication.class, args);

    }

}
