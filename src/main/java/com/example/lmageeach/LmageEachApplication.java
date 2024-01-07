package com.example.lmageeach;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
@MapperScan("com/example/lmageeach/mapper")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class LmageEachApplication {

    public static void main(String[] args) {
        SpringApplication.run(LmageEachApplication.class, args);
    }

}
