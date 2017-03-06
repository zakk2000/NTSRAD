package com.nts.rad.dao.user.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.nts.rad.dao.user.IUserDao;
import com.nts.rad.domain.user.Level;
import com.nts.rad.domain.user.User;

public class UserDaoJdbcTemplate implements IUserDao {

//	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<User> userMapper = new RowMapper<User>() {
		
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			User user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			user.setLevel(Level.valueOf(rs.getInt("user_level")));
			user.setLogin(rs.getInt("login"));
			user.setRecommend(rs.getInt("recommend"));
			user.setLastUpdated(rs.getDate("last_updated"));
			
			return user;
		
		}
	
	};
	
	public void setDataSource(DataSource dataSource) {
		
		this.jdbcTemplate = new JdbcTemplate(dataSource);
//		this.dataSource = dataSource;
	
	}
	
	public void deleteAll() {
		
		/*this.jdbcTemplate.update(
			
			new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					
					return con.prepareStatement("delete from user_rad");
				
				}
			
			}
		
		);*/
		
		this.jdbcTemplate.update("delete from users_rad");
	
	}
	
//	public void add(User user) throws DuplicatedUserIdException {
	public void add(User user) {
		
//		try {
			
			this.jdbcTemplate.update("insert into users_rad (id, name, password, user_level, login, recommend) values (?, ?, ?, ?, ?, ?)"
					, user.getId()
					, user.getName()
					, user.getPassword()
					, user.getLevel().intValue()
					, user.getLogin()
					, user.getRecommend());
		
		/*} catch(DuplicateKeyException dke) {
			
			throw new DuplicatedUserIdException(dke);
		
		}*/
	
	}
	
	public int getCount() {
		
		/*return this.jdbcTemplate.query(
			
			new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					
					return con.prepareStatement("select count(id) from user_rad");
				
				}
			
			}, new ResultSetExtractor<Integer>() {
				
				public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
					
					rs.next();
					
					return rs.getInt(1);
				
				}
			
			}
		
		);*/
		
		return this.jdbcTemplate.queryForInt("select count(id) from users_rad");
	
	}
	
	public User get(String id) {
		
		return this.jdbcTemplate.queryForObject(
			
			"select * from users_rad where id = ?"
			, new Object[] {id}
			/*, new RowMapper<User>() {
				
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					User user = new User();
					user.setId(rs.getString("id"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					
					return user;
				
				}
			
			}*/
			, this.userMapper
		
		);
	
	}
	
	public List<User> getAll() {
		
		return this.jdbcTemplate.query(
			
			"select * from users_rad order by id"
			/*, new RowMapper<User>() {
				
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					User user = new User();
					user.setId(rs.getString(1));
					user.setName(rs.getString(2));
					user.setPassword(rs.getString(3));
					
					return user;
				
				}
			
			}*/
			, this.userMapper
		
		);
	
	}

	@Override
	public void update(User user) {
		
		this.jdbcTemplate.update("update users_rad set name = ?, password = ?, user_level = ?, login = ?, recommend = ?, last_updated = ? where id = ?"
				, user.getName()
				, user.getPassword()
				, user.getLevel().intValue()
				, user.getLogin()
				, user.getRecommend()
				, user.getLastUpdated()
				, user.getId());
	
	}

}
