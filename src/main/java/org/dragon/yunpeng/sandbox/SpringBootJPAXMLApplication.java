package org.dragon.yunpeng.sandbox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "org.dragon.yunpeng.sandbox" })
@EnableJpaRepositories(basePackages = "org.dragon.yunpeng.sandbox.repositories")
@EnableTransactionManagement
@EntityScan(basePackages = "org.dragon.yunpeng.sandbox.entities")
public class SpringBootJPAXMLApplication {

	
	Logger logger = LoggerFactory.getLogger(SpringBootJPAXMLApplication.class);

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(SpringBootJPAXMLApplication.class, args);

		// Access the Spring Boot main application instance
		SpringBootJPAXMLApplication mainApplication = context.getBean(SpringBootJPAXMLApplication.class);

	}

}
