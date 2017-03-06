package com.nts.rad.service.user;

import static com.nts.rad.policy.UserLevelUpgradePolicy.MIN_LOGCOUNT_FOR_BRONZE;
import static com.nts.rad.policy.UserLevelUpgradePolicy.MIN_LOGCOUNT_FOR_SILVER;
import static com.nts.rad.policy.UserLevelUpgradePolicy.MIN_RECOMMEND_FOR_GOLD;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.rad.dao.user.IUserDao;
import com.nts.rad.domain.user.Level;
import com.nts.rad.domain.user.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/com/nts/rad/root-context.xml")
public class UserServiceTest {

	@Autowired UserService userService;
	@Autowired IUserDao userDao;
	List<User> users;
	
	@Before
	public void setUp() {
		
		users = Arrays.asList(
			new User("serviceTester4", "Service Tester 4", "serviceTester4", Level.BASIC, MIN_LOGCOUNT_FOR_BRONZE - 1, 0)
			, new User("serviceTester5", "Service Tester 5", "serviceTester5", Level.BASIC, MIN_LOGCOUNT_FOR_BRONZE, 30)
			, new User("serviceTester6", "Service Tester 6", "serviceTester6", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD - 1)
			, new User("serviceTester7", "Service Tester 7", "serviceTester7", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD)
			, new User("serviceTester8", "Service Tester 8", "serviceTester8", Level.GOLD, 100, Integer.MAX_VALUE)
			, new User("serviceTester9", "Service Tester 9", "serviceTester9", Level.BRONZE, MIN_LOGCOUNT_FOR_SILVER, 0)
		);
	}
	
	@Test
	public void bean() {
		
		assertThat(this.userService, is(notNullValue()));
	
	}
	
	@Test
	public void upgradeLevels() {
		
		userDao.deleteAll();
		
		for (User user : users) {
			
			userDao.add(user);
		
		}
		
		userService.upgradeUserLevels();
		
		checkLevel(users.get(0), false);
		checkLevel(users.get(1), true);
		checkLevel(users.get(2), false);
		checkLevel(users.get(3), true);
		checkLevel(users.get(4), false);
		checkLevel(users.get(5), true);
	
	}
	
	/*@Test
	public void add() {
		
		userDao.deleteAll();
		
		User userWithLevel = users.get(4);
		User userWithoutLevel = users.get(0);
		userWithoutLevel.setLevel(null);
		
		userService.add(userWithLevel);
		userService.add(userWithoutLevel);
		
		User userWithLevelRead = userDao.get(userWithLevel.getId());
		User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());
		
		assertThat(userWithLevelRead.getLevel(), is(userWithLevel.getLevel()));
		assertThat(userWithoutLevelRead.getLevel(), is(Level.BASIC));
	
	}*/
	
	private void checkLevel(User user, boolean upgraded) {
		
		User userUpdate = userDao.get(user.getId());
		
		if (upgraded) assertThat(userUpdate.getLevel(), is(user.getLevel().getNextLevel()));
		else assertThat(userUpdate.getLevel(), is(user.getLevel()));
	
	}

}
