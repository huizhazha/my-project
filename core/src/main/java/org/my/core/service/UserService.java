package org.my.core.service;

import org.my.core.domain.User;

public interface UserService {
	User getUser(String username);
	User saveUser(User user);
	User updateUser(User user);
	void delUser(String username);
}
