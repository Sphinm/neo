package com.example.neo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.neo.**.mapper")
public class NeoApplication {
    public static void main(String[] args) {
        SpringApplication.run(NeoApplication.class, args);
    }
}
