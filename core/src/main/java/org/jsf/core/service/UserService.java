package org.jsf.core.service;

import org.jsf.core.domain.User;

public interface UserService {
	User getUser(String username);
	User saveUser(User user);
	User updateUser(User user);
	void delUser(String username);
}
