package org.jsf.core.service.impl;

import org.jsf.core.domain.User;
import org.jsf.core.resposity.UserResposity;
import org.jsf.core.service.UserService;
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