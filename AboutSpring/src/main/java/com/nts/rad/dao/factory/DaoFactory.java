package com.nts.rad.dao.factory;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.nts.rad.conn.ConnectionMaker;
import com.nts.rad.conn.impl.CountingConnectionMaker;
import com.nts.rad.conn.impl.DConnectionMaker;
import com.nts.rad.dao.user.UserDao;

@Configuration
public class DaoFactory {
	
	@Bean
	public UserDao userDao() {
		
		UserDao userDao = new UserDao();
//		userDao.setConnectionMaker(connectionMaker());
		userDao.setDataSource(dataSource());
		
		return userDao;
	
	}
	
	@Bean
	public ConnectionMaker connectionMaker() {
		
		CountingConnectionMaker ccm = new CountingConnectionMaker();
		ccm.setRealConnectionMaker(realConnectionMaker());
		
		return ccm;
	
	}
	
	@Bean
	public ConnectionMaker realConnectionMaker() {
		
		return new DConnectionMaker();
	
	}
	
	@Bean
	public DataSource dataSource() {
		
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost/ntsdb");
		dataSource.setUsername("ntsuser");
		dataSource.setPassword("ntsuser!*");
		
		return dataSource;
	
	}

}
