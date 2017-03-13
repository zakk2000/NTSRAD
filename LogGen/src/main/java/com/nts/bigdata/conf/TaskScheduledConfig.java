package com.nts.bigdata.conf;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * <pre>
 * com.nts.bigdata.conf
 * TaskScheduledConfig.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 3. 8.
 */
@Configuration
@EnableScheduling
public class TaskScheduledConfig implements SchedulingConfigurer {

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		
		taskRegistrar.setScheduler(taskExecutor());
	
	}
	
	@Bean(destroyMethod = "shutdown")
	public Executor taskExecutor() {
		
		return Executors.newScheduledThreadPool(10);
	
	}

}
