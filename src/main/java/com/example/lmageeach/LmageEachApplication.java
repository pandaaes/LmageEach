package com.example.lmageeach;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com/example/lmageeach/mapper")
public class LmageEachApplication {

    public static void main(String[] args) {
        SpringApplication.run(LmageEachApplication.class, args);
    }

}
