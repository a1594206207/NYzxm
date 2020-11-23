package com.nzxmmp.nzxm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.nzxmmp.nzxm.Mapper")
public class NzxmApplication {

    public static void main(String[] args) {

        SpringApplication.run(NzxmApplication.class, args);
    }

}
