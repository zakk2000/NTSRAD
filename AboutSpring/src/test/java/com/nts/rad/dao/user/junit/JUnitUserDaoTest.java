package com.nts.rad.dao.user.junit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.rad.dao.user.UserDao;
import com.nts.rad.domain.user.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/com/nts/rad/root-context.xml")
public class JUnitUserDaoTest {

//	@Autowired private ApplicationContext context;
	
	@Autowired private UserDao dao;
	private User user1;
	private User user2;
	private User user3;
	
	@Before
	public void setUp() {
		
//		ApplicationContext context = new GenericXmlApplicationContext("com/nts/rad/root-context.xml");
		/*System.out.println(this.context);
		System.out.println(this);
		this.dao = context.getBean("userDao", UserDao.class);*/
		
		this.user1 = new User("springTester1", "Tester 1", "tester1");
		this.user2 = new User("springTester2", "Tester 2", "tester2");
		this.user3 = new User("springTester3", "Tester 3", "tester3");
	
	}
	
	@Test
	public void addAndGet() throws SQLException {
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		User user1Test = dao.get(user1.getId());
		assertThat(user1Test.getName(), is(user1.getName()));
		assertThat(user1Test.getPassword(), is(user1.getPassword()));
		
		User user2Test = dao.get(user2.getId());
		assertThat(user2Test.getName(), is(user2.getName()));
		assertThat(user2Test.getPassword(), is(user2.getPassword()));
	
	}
	
	@Test
	public void count() throws SQLException {
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		assertThat(dao.getCount(), is(1));
		
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		dao.add(user3);
		assertThat(dao.getCount(), is(3));
	
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserDaoFailure() throws SQLException {
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.get("Unknown_id");
	
	}

}
