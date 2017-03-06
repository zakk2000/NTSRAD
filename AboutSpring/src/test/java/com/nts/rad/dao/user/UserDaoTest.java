package com.nts.rad.dao.user;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.nts.rad.conn.impl.CountingConnectionMaker;
import com.nts.rad.dao.factory.DaoFactory;
import com.nts.rad.domain.user.User;

public class UserDaoTest {

//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	public static void main(String[] args) throws SQLException {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		for (int i = 31; i <= 33; i++) {
			
			User user = new User();
			user.setId("springTester" + i);
			user.setName("스프링 테스터" + i);
			user.setPassword("tester" + i);
			
			dao.add(user);
			
			System.out.println("[ " + user.getName() + "] 등록 성공");
		
		}
		
		CountingConnectionMaker ccm1 = context.getBean("connectionMaker", CountingConnectionMaker.class);
		CountingConnectionMaker ccm2 = context.getBean("connectionMaker", CountingConnectionMaker.class);
		System.out.println("[ Spring Singleton Test ] ccm1 == ccm2 ? " + (ccm1 == ccm2));
		System.out.println("[ DB Connection Count Result ] " + ccm1.getCount());
	
	}

}
