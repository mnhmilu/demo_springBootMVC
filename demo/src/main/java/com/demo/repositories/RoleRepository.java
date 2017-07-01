package com.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.domain.security.Role;
import com.demo.domain.security.User;

/**
 * @author ekansh
 * @since 2/4/16
 */
public interface RoleRepository extends JpaRepository<Role,Long> {

    User findByRole(String role);
    Role findById(long id);
}
