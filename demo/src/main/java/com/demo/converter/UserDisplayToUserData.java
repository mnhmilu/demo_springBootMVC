package com.demo.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.demo.commands.UserDisplay;
import com.demo.domain.security.Role;
import com.demo.domain.security.User;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserDisplayToUserData implements Converter<UserDisplay, User> {

	@Override
	public User convert(UserDisplay frm) {

		User user = new User();
		user.setEmail(frm.getEmail());
		user.setId(frm.getId());
		user.setPassword(frm.getPassword());
		user.setUsername(frm.getUsername());
//		Set<Role> roles = new HashSet<Role>();
//		Role role = new Role();
//		role.setId(Integer.valueOf(frm.getRoles()));
//		roles.add(role);
//		user.setRoles(roles);

		return user;
	}

}
