package com.softdev.system.demo;

import com.softdev.system.demo.config.Redis;


/**
 * @author Moshow
 */
@Configuration
@SpringBootApplication
@ComponentScan(basePackages = {"com.softdev.system.demo"})
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class,args);
	}
	@Bean
	public Redis redis(){
		return new Redis("123456","localhost");
	}
}
