package com.celizion.app.config.common;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <pre>
 * com.celizion.app.config.common
 * SphinxDataSource.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 3. 28.
 */
@Configuration
@EnableTransactionManagement
public class SphinxDataSource {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private @Value("${jdbc.driverClass}") String searchEngineDriverClass;
	private @Value("${jdbc.searchEngine.jdbcUrl}") String searchEngineJdbcUrl;
	private @Value("${jdbc.initialPoolSize}") String initialPoolSize;
	private @Value("${jdbc.acquireIncrement}") String acquireIncrement;
	private @Value("${jdbc.maxPoolSize}") String maxPoolSize;
	private @Value("${jdbc.minPoolSize}") String minPoolSize;
	private @Value("${jdbc.checkoutTimeout}") String checkoutTimeout;
	
	@Bean(destroyMethod = "close")
	DataSource searchEngineDataSource() {
		
		try {
			
			/*
			 * DBCP Connection Pooling Library
			 */
			BasicDataSource datasource = new BasicDataSource();
			datasource.setDriverClassName(searchEngineDriverClass);
			datasource.setUsername("");
			datasource.setPassword("");
			datasource.setUrl(searchEngineJdbcUrl);
			
			return datasource;
		
		} catch (Exception e) {
			
			logger.error("=================== An error occurred initializing DataSource for Search Engine ===================\n" + e.getMessage());
			throw new RuntimeException("=================== An error occurred initializing DataSource for Search Engine ===================\n", e);
		
		}
	
	}
	
	@Bean
	public JdbcTemplate searchEngineJdbcTemplate() {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(searchEngineDataSource());
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		
		return jdbcTemplate;
	
	}
	
	@Bean
	public PlatformTransactionManager searchEngineTransactionManager() {
		
		return new DataSourceTransactionManager(searchEngineDataSource());
	
	}

}
