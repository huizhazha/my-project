package org.jsf.web.service;

import org.jsf.web.domain.User;

public interface UserService {
	User getUser(String username);
	User saveUser(User user);
	User updateUser(User user);
	void delUser(String username);
}
