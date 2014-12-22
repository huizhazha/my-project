package org.my.core.resposity;

import java.io.Serializable;

import org.my.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserResposity extends JpaRepository<User, Serializable>{
	User findByUsername(String username);
}
