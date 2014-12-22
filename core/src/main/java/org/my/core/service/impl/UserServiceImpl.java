package org.my.core.service.impl;

import org.my.core.domain.User;
import org.my.core.resposity.UserResposity;
import org.my.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserResposity userResposity;

	public User getUser(String username) {
		return userResposity.findByUsername(username);
	}

	public User saveUser(User user) {
		return userResposity.save(user);
	}

	public User updateUser(User user) {
		return userResposity.save(user);
	}

	public void delUser(String username) {
		userResposity.delete(username);
	}
	
}