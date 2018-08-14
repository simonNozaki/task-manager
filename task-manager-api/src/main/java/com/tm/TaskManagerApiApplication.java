package com.tm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class})
@ComponentScan({"com.tm.dao","com.tm.service","com.tm.controller","com.tm.config"})
@MapperScan("com.tm.dao")
public class TaskManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApiApplication.class, args);
	}
}
