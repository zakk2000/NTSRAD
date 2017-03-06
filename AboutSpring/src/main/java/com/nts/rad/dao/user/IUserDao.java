package com.nts.rad.dao.user;

import java.util.List;

import com.nts.rad.domain.user.User;

public interface IUserDao {

	void add(User user);
	User get(String id);
	List<User> getAll();
	void deleteAll();
	int getCount();
	void update(User user);

}
