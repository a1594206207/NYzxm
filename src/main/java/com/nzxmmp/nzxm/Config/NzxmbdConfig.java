package com.nzxmmp.nzxm.Config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.nzxmmp.nzxm.Mapper.*.mapper*")
public class NzxmbdConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor=new PaginationInterceptor();
        paginationInterceptor.setOverflow(true);
        return paginationInterceptor;
    }
}
