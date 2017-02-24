package com.celizion.app.config.common;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * <pre>
 * com.celizion.app.config.common
 * AppContext.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2016. 8. 3.
 */
@Configuration
@MapperScan("com.celizion.app.mapper")
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.celizion.app.service", "com.celizion.app.config.security"}, useDefaultFilters = false, includeFilters = {@Filter(Service.class), @Filter(Configuration.class)})
public class AppContext extends WebMvcConfigurerAdapter implements TransactionManagementConfigurer {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private @Value("${jdbc.driverClass}") String driverClass;
	private @Value("${jdbc.jdbcUrl}") String jdbcUrl;
	private @Value("${jdbc.user}") String user;
	private @Value("${jdbc.password}") String password;
	private @Value("${jdbc.initialPoolSize}") String initialPoolSize;
	private @Value("${jdbc.acquireIncrement}") String acquireIncrement;
	private @Value("${jdbc.maxPoolSize}") String maxPoolSize;
	private @Value("${jdbc.minPoolSize}") String minPoolSize;
	private @Value("${jdbc.checkoutTimeout}") String checkoutTimeout;
	
	@Bean(destroyMethod = "close")
	DataSource dataSource() {
		
		try {
			
			/*
			 * c3p0 Connection Pooling Library
			 */
			ComboPooledDataSource datasource = new ComboPooledDataSource();
			datasource.setDriverClass(driverClass);
			datasource.setJdbcUrl(jdbcUrl);
			datasource.setUser(user);
			datasource.setPassword(password);
			datasource.setInitialPoolSize(Integer.valueOf(initialPoolSize));
			datasource.setAcquireIncrement(Integer.valueOf(acquireIncrement));
			datasource.setMaxPoolSize(Integer.valueOf(maxPoolSize));
			datasource.setMinPoolSize(Integer.valueOf(minPoolSize));
			datasource.setCheckoutTimeout(Integer.valueOf(checkoutTimeout));
			
			return datasource;
		
		} catch (PropertyVetoException e) {
			
			logger.error("=================== An error occurred initializing DataSource ===================\n" + e.getMessage());
			throw new RuntimeException("=================== An error occurred initializing DataSource ===================\n", e);
		
		}
	
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		
		return new DataSourceTransactionManager(dataSource());
	
	}
	
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		
		return transactionManager();
	
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setConfigLocation(new ClassPathResource("com/celizion/app/mapper/config/mybatis-config.xml"));
		
		return sessionFactory.getObject();
	
	}
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("/WEB-INF/messages/message-button"
				  					, "/WEB-INF/messages/message-label"
				  					, "/WEB-INF/messages/message-menu"
				  					, "/WEB-INF/messages/message-view");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(0);	// -1: Never Reload, 0: Always Reload
		
		return messageSource;
	
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		
		configurer.enable();
	
	}

}
