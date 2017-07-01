package com.demo.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.demo.commands.UserDisplay;
import com.demo.domain.security.Role;
import com.demo.domain.security.User;

@Component
public class UserDataToUserDisplay implements Converter<User, UserDisplay> {

	@Override
	public UserDisplay convert(User data) {

		UserDisplay frm = new UserDisplay();
		frm.setEmail(data.getEmail());
		frm.setId(data.getId());

		String rolename = "";

	
			for (Role role : data.getRoles())

			{
			
					rolename=role.getRole();
					
				

			}

		
		
		
		

		frm.setRoles(rolename);
		frm.setUsername(data.getUsername());

		return frm;
	}

}
