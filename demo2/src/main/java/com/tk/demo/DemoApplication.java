package com.tk.demo;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableScheduling
@SpringBootApplication
@ComponentScan({"com.tk.demo"})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@EnableAsync
    @Configuration
    class TaskPoolConfig{
    	@Bean(name = "taskExecutor")
		public Executor taskExecutor() {
			System.out.println("Create thread---");
	        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	        executor.setCorePoolSize(3);
	        executor.setMaxPoolSize(3);
	        executor.setQueueCapacity(500);
	        executor.setThreadNamePrefix("Future-");
	        executor.initialize();
	        return executor;
	    }
	}
}
