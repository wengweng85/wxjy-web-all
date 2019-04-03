package com.groupwork;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.groupwork.mapper")
public class GwyWeixinApplication {

	public static void main(String[] args) {
		SpringApplication.run(GwyWeixinApplication.class, args);
	}
}
