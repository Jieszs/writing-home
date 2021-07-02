package com.jiesz.writinghome.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@MapperScan("com.jiesz.writinghome.mapper")
public class MybatisPlusConfig {

}

