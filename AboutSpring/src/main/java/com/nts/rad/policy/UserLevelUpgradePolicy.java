package com.nts.rad.policy;

import com.nts.rad.domain.user.User;

public interface UserLevelUpgradePolicy {

	public static final int MIN_LOGCOUNT_FOR_BRONZE = 50;
	public static final int MIN_LOGCOUNT_FOR_SILVER = 80;
	public static final int MIN_RECOMMEND_FOR_GOLD = 30;
	
	boolean canUpgradeLevel(User user);
	void upgradeLevel(User user);

}
