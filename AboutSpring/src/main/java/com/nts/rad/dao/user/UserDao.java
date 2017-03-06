package com.nts.rad.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import com.mysql.jdbc.MysqlErrorNumbers;
import com.nts.rad.domain.user.User;
import com.nts.rad.exception.DuplicatedUserIdException;

public class UserDao {

	/*private ConnectionMaker connectionMaker;
	
	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		
		this.connectionMaker = connectionMaker;
	
	}*/
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		
		this.dataSource = dataSource;
	
	}
	
//	public void add(User user) throws ClassNotFoundException, SQLException {
//	public void add(User user) throws SQLException {
	public void add(User user) throws DuplicatedUserIdException {
		
		try {
			
//			Connection c = connectionMaker.makeConnection();
			Connection c = dataSource.getConnection();
			
			PreparedStatement ps = c.prepareStatement("insert into users_rad values (?, ?, ?)");
			ps.setString(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());
			
			ps.executeUpdate();
			
			ps.close();
			c.close();
		
		} catch(SQLException se) {
			
			if (se.getErrorCode() == MysqlErrorNumbers.ER_DUP_ENTRY)
				
				throw new DuplicatedUserIdException(se);
//				throw new DuplicatedUserIdException().initCause(se);
			
			else
				
				throw new RuntimeException(se);
		
		}
	
	}
	
//	public User get(String id) throws ClassNotFoundException, SQLException {
	public User get(String id) throws SQLException {
	
//		Connection c = connectionMaker.makeConnection();
		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement("select * from users_rad where id = ?");
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		User user = null;
		if (rs.next()) {
			
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		
		}
		
		rs.close();
		ps.close();
		c.close();
		
		if (user == null) throw new EmptyResultDataAccessException(1);
		
		return user;
	
	}
	
	public void deleteAll() throws SQLException {
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = dataSource.getConnection();
			ps = c.prepareStatement("delete from users_rad");
			ps.executeUpdate();
		
		} catch (SQLException se) {
			
			throw se;
		
		} finally {
			
			if (ps != null) {
				
				try {
					
					ps.close();
				
				} catch (SQLException se) {}
			
			}
			
			if (c != null) {
				
				try {
					
					c.close();
				
				} catch (SQLException se) {}
			
			}
		
		}
		
		
		ps.close();
		c.close();
	
	}
	
	public int getCount() throws SQLException {
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			c = dataSource.getConnection();
			ps = c.prepareStatement("select count(id) from users_rad");
			rs = ps.executeQuery();
			
			rs.next();
			int count = rs.getInt(1);
			
			return count;
		
		} catch (SQLException se) {
			
			throw se;
		
		} finally {
			
			if (rs != null) try { rs.close(); } catch (SQLException se) {}
			if (ps != null) try { ps.close(); } catch (SQLException se) {}
			if (c != null) try { c.close(); } catch (SQLException se) {}
		
		}
	
	}

}
