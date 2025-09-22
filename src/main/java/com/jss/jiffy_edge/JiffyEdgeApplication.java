package com.jss.jiffy_edge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EntityScan(basePackages = "com.jss.jiffy_edge.dao.entities")
@EnableJpaRepositories(basePackages = "com.jss.jiffy_edge.dao.repo")
@ComponentScan(basePackages = "com.jss.jiffy_edge")
@EnableScheduling
public class JiffyEdgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JiffyEdgeApplication.class, args);
	}

}
