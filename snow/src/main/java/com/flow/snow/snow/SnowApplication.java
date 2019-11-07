package com.flow.snow.snow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.flow.snow.snow.mapper")
public class SnowApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnowApplication.class, args);
	}

}
