package com.nts.rad.conn;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {

//	Connection makeConnection() throws ClassNotFoundException, SQLException;
	Connection makeConnection() throws SQLException;

}
