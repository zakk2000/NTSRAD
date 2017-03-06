package com.nts.rad.policy.impl;

import com.nts.rad.dao.user.IUserDao;
import com.nts.rad.domain.user.Level;
import com.nts.rad.domain.user.User;
import com.nts.rad.policy.UserLevelUpgradePolicy;

public class CommonLevelUpgradePolicy implements UserLevelUpgradePolicy {

	IUserDao userDao;
	
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean canUpgradeLevel(User user) {
		
		Level currentLevel = user.getLevel();
		switch (currentLevel) {
			
			case BASIC: return (user.getLogin() >= MIN_LOGCOUNT_FOR_BRONZE);
			case BRONZE: return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
			case SILVER: return (user.getRecommend() >= MIN_RECOMMEND_FOR_GOLD);
			case GOLD: return false;
			default: throw new IllegalArgumentException("Unknown Level: " + currentLevel);
		
		}
	
	}

	@Override
	public void upgradeLevel(User user) {
		
		user.upgradeLevel();
		userDao.update(user);
		System.out.println("Level Upgrade Complete ===================================");

	}

}
