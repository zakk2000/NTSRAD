package com.nts.rad.dao.user.junit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.rad.dao.user.IUserDao;
import com.nts.rad.domain.user.Level;
import com.nts.rad.domain.user.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/com/nts/rad/root-context.xml")
public class UserDaoJdbcTemplateTest {

	@Autowired private IUserDao dao;
	@Autowired private DataSource dataSource;
	
	private User user1;
	private User user2;
	private User user3;
	
	@Before public void setUp() {
		
		this.user1 = new User("springTester1", "Tester 1", "tester1", Level.BASIC, 1, 0);
		this.user2 = new User("springTester2", "Tester 2", "tester2", Level.SILVER, 55, 10);
		this.user3 = new User("springTester3", "Tester 3", "tester3", Level.GOLD, 100, 40);
	
	}
	
	@Test public void addAndGet() {
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		User user1Test = dao.get(user1.getId());
		checkSameUser(user1Test, user1);
		
		User user2Test = dao.get(user2.getId());
		checkSameUser(user2Test, user2);
	
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserDaoFailure() throws SQLException {
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.get("Unknown_id");
	
	}
	
	@Test public void getAllTest() {
		
		dao.deleteAll();
		
		List<User> users0 = dao.getAll();
		assertThat(users0.size(), is(0));
		
		dao.add(user1);
		List<User> users1 = dao.getAll();
		assertThat(users1.size(), is(1));
		checkSameUser(user1, users1.get(0));
		
		dao.add(user2);
		List<User> users2 = dao.getAll();
		assertThat(users2.size(), is(2));
		checkSameUser(user1, users2.get(0));
		checkSameUser(user2, users2.get(1));
		
		dao.add(user3);
		List<User> users3 = dao.getAll();
		assertThat(users3.size(), is(3));
		checkSameUser(user1, users3.get(0));
		checkSameUser(user2, users3.get(1));
		checkSameUser(user3, users3.get(2));
	
	}
	
//	@Test(expected=DuplicatedUserIdException.class)
//	@Test
	@Test(expected=DuplicateKeyException.class)
	public void duplicatedKey() {
		
		dao.deleteAll();
		
		dao.add(user1);
		dao.add(user1);
	
	}
	
	private void checkSameUser(User user1, User user2) {
		
		assertThat(user1.getId(), is(user2.getId()));
		assertThat(user1.getName(), is(user2.getName()));
		assertThat(user1.getPassword(), is(user2.getPassword()));
		assertThat(user1.getLevel(), is(user2.getLevel()));
		assertThat(user1.getLogin(), is(user2.getLogin()));
		assertThat(user1.getRecommend(), is(user2.getRecommend()));
	
	}
	
	/*
	 * Checked Exception인 SQLException을 직접 해석하여
	 * Runtime Exception이고 Unchecked Exception인 DataAccessException으로 예외전환(Exception Translation) 
	 */
	@Test
	public void sqlExceptionTranslate() {
		
		dao.deleteAll();
		
		try {
			
			dao.add(user1);
			dao.add(user1);
		
		} catch(DuplicateKeyException dke) {
			
			SQLException sqlEx = (SQLException) dke.getRootCause();
			SQLExceptionTranslator set = new SQLErrorCodeSQLExceptionTranslator(this.dataSource);
			assertThat(set.translate(null, null, sqlEx), is(DuplicateKeyException.class));
		
		}
	
	}
	
	@Test
	public void update() {
		
		dao.deleteAll();
		
		dao.add(user1);
		dao.add(user2);
		
		user1.setName("남택승");
		user1.setPassword("nts123");
		user1.setLevel(Level.GOLD);
		user1.setLogin(1000);
		user1.setRecommend(999);
		
		dao.update(user1);
		
		User user1Test = dao.get(user1.getId());
		checkSameUser(user1Test, user1);
		
		User user2Test = dao.get(user2.getId());
		checkSameUser(user2Test, user2);
	
	}

}
