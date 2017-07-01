package com.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.domain.security.User;

/**
 * @author ekansh
 * @since 2/4/16
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
    User findById(long id);
    
}
