package com.nts.rad.service.user;

import java.util.List;

import com.nts.rad.dao.user.IUserDao;
import com.nts.rad.domain.user.Level;
import com.nts.rad.domain.user.User;
import com.nts.rad.policy.UserLevelUpgradePolicy;

public class UserService {

	IUserDao userDao;
	UserLevelUpgradePolicy userLevelUpgradePolicy;

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setUserLevelUpgradePolicy(UserLevelUpgradePolicy userLevelUpgradePolicy) {
		this.userLevelUpgradePolicy = userLevelUpgradePolicy;
	}
	
	/*
	 * SILVER: 50회 이상 로그인 기록 달성 시
	 * GOLD: SILVER 레벨이면서 30번 이상 추천 달성 시
	 */
	public void upgradeUserLevels() {
		
		/*List<User> users = userDao.getAll();
		for (User user : users) {
			
			Boolean changed = null;
			
			if ((user.getLevel() == Level.BASIC) && (user.getLogin() >= 50)) {
				
				user.setLevel(Level.SILVER);
				changed = true;
			
			} else if ((user.getLevel() == Level.SILVER) && (user.getRecommend() >= 30)) {
				
				user.setLevel(Level.GOLD);
				changed = true;
			
			} else if (user.getLevel() == Level.GOLD) {
				
				changed = false;
			
			} else {
				
				changed = false;
			
			}
			
			if (changed) userDao.update(user);
		
		}*/
		
		/*
		 * Refactoring
		 */
		List<User> users = userDao.getAll();
		for (User user : users) {
			
			if (userLevelUpgradePolicy.canUpgradeLevel(user)) {
				
				userLevelUpgradePolicy.upgradeLevel(user);
			
			}
		
		}
	
	}
	
	public void add(User user) {
		
		if (user.getLevel() == null) user.setLevel(Level.BASIC);
		
		userDao.add(user);
	
	}
	/*
	private boolean canUpgradeLevel(User user) {
		
		Level currentLevel = user.getLevel();
		switch (currentLevel) {
			
			case BASIC: return (user.getLogin() >= MIN_LOGCOUNT_FOR_BRONZE);
			case BRONZE: return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
			case SILVER: return (user.getRecommend() >= MIN_RECOMMEND_FOR_GOLD);
			case GOLD: return false;
			default: throw new IllegalArgumentException("Unknown Level: " + currentLevel);
		
		}
	
	}
	
	private void upgradeLevel(User user) {
		
		user.upgradeLevel();
		userDao.update(user);
		System.out.println("Level Upgrade Complete ===================================");
	
	}
	*/
}
