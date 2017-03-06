package com.nts.rad.dao.user;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nts.rad.domain.user.User;

public class UserDaoXMLTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
//		ApplicationContext context = new GenericXmlApplicationContext("com/nts/rad/root-context.xml");
		ApplicationContext context = new ClassPathXmlApplicationContext("../../root-context.xml", UserDao.class);
		UserDao userDao = context.getBean("userDao", UserDao.class);
		
		User user = new User();
		user.setId("springTester34");
		user.setName("스프링 테스터34");
		user.setPassword("tester34");
		
		userDao.add(user);
		
		User user4Test = userDao.get("springTester34");
		
		if (!user.getName().equals(user4Test.getName())) {
			
			System.out.println("Test Fail (Name)");
		
		} else if (!user.getPassword().equals(user4Test.getPassword())) {
			
			System.out.println("Test Fail (Password)");
		
		} else {
			
			System.out.println("Test Pass");
		
		}
	
	}

}
