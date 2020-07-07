package com.demo.vchat.vchat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.demo.vchat.vchat.mapper")
public class VchatApplication {

    public static void main(String[] args) {
        SpringApplication.run(VchatApplication.class, args);
    }

}
