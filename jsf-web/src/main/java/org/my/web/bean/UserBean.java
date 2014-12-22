package org.my.web.bean;

import javax.annotation.PostConstruct;

import org.my.core.domain.User;
import org.my.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("userBean")
@Scope("view")
public class UserBean {

	@Autowired
	private UserService userService;

	private User user;

	@PostConstruct
	public void init() {
		user = new User();
		user.setUsername("username");
		user.setPassword("password");
	}

	public void queryUser() {
		user = userService.getUser(user.getUsername());
	}

	public void updateUser() {
		userService.updateUser(user);
	}

	public void addUser() {
		userService.saveUser(user);
	}

	public void delUser() {
		userService.delUser(user.getUsername());
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
