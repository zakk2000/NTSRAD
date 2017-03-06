package com.nts.rad.conn.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.nts.rad.conn.ConnectionMaker;

public class DConnectionMaker implements ConnectionMaker {

	/*@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/ntsdb", "ntsuser", "ntsuser!*");
		
		return c;
	
	}*/
	
	@Override
	public Connection makeConnection() throws SQLException {
		
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost/ntsdb");
		dataSource.setUsername("ntsuser");
		dataSource.setPassword("ntsuser!*");
		
		return dataSource.getConnection();
	
	}

}
