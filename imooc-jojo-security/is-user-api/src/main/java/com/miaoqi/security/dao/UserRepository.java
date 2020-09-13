package com.miaoqi.security.dao;

import com.miaoqi.security.user.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaSpecificationExecutor<User>, CrudRepository<User, Long> {

    User findByUsername(String username);

}
