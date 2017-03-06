package com.nts.rad.conn.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.nts.rad.conn.ConnectionMaker;

public class CountingConnectionMaker implements ConnectionMaker {

	private int count = 0;
	private ConnectionMaker realConnectionMaker;
	
	public void setRealConnectionMaker(ConnectionMaker realConnectionMaker) {
		
		this.realConnectionMaker = realConnectionMaker;
	
	}
	
	@Override
//	public Connection makeConnection() throws ClassNotFoundException, SQLException {
	public Connection makeConnection() throws SQLException {
		
		this.count++;
		
		return realConnectionMaker.makeConnection();
	
	}
	
	public int getCount() {
		
		return this.count;
	
	}

}
