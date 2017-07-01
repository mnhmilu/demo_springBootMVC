package com.demo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.commands.UserDisplay;
import com.demo.converter.UserDataToUserDisplay;
import com.demo.converter.UserDisplayToUserData;
import com.demo.domain.security.Role;
import com.demo.domain.security.User;
import com.demo.repositories.RoleRepository;
import com.demo.repositories.UserRepository;

/**
 * @author nahid
 * @since 30/3/16
 */
@Controller

public class UserController {

	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserDataToUserDisplay userDataToUserDisplay;

	@Autowired
	UserDisplayToUserData userDisplayToUserData;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/admin/userList", method = RequestMethod.GET)
	public String index(@ModelAttribute User user, Model model, Pageable pageable) {

		Page<User> userPage = userRepository.findAll(pageable);

		List<UserDisplay> list = new ArrayList();

		List<User> users = userPage.getContent();

		for (User userItem : users) {

			UserDisplay display = userDataToUserDisplay.convert(userItem);

			list.add(display);

		}

		Page<UserDisplay> userpg = new PageImpl(list);

		PageWrapper<UserDisplay> page = new PageWrapper<UserDisplay>(userpg, "/admin/userList");
		List<UserDisplay> userss = page.getContent();
		model.addAttribute("roles", roleRepository.findAll());
		model.addAttribute("users", userss);
		model.addAttribute("page", page);
		return "user/indexofUsers";
	}

	@RequestMapping(value = "/admin/newUser", method = RequestMethod.GET)
	public String addUser(@ModelAttribute UserDisplay user, Model model) {
		User user1 = new User();

		String charsCaps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String chars = "abcdefghijklmnopqrstuvwxyz";
		String nums = "0123456789";
		String passSymbols = charsCaps + chars + nums;
		Random random = new Random();
		String password = "";
		for (int i = 0; i < 5; i++) {
			password = password + passSymbols.charAt(random.nextInt(passSymbols.length()));
		}
		user1.setPassword(password);

		// addRoles(model);
		model.addAttribute("user", user1);
		model.addAttribute("roles", roleRepository.findAll());
		return "user/userNew";
	}

	@RequestMapping(value = "/admin/createUser", method = RequestMethod.POST)
	public String userSubmitforNewUser(@Valid @ModelAttribute("user") UserDisplay user, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("user", user);
			model.addAttribute("roles", roleRepository.findAll());
			return "user/userNew";
		}

		User userData = userDisplayToUserData.convert(user);
		LOGGER.info("UserController : " + userData.toString());

		Role role = roleRepository.findById(Integer.parseInt(user.getRoles()));
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		userData.setRoles(roles);

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(userData.getPassword());
		userData.setPassword(hashedPassword);

		userRepository.save(userData);
		LOGGER.info("UserController : " + userData.getUsername());
		return "redirect:" + "/admin/userList";
	}

	@RequestMapping(value = "/admin/deleteUser/{id}", method = RequestMethod.GET)
	public String userDelete(@PathVariable Integer id, Model model) {
		LOGGER.info("deleteUser :: UserId:: " + id);
		User user = userRepository.findById(id);
		LOGGER.info("deleteUser :: userName :: " + user.getUsername());
		userRepository.delete(user);
		return "redirect:" + "/admin/userList";
	}

	@RequestMapping("/admin/resetUserPassword/{id}")
	public String resetUserPassword(@PathVariable Integer id, Model model) {

		User user = userRepository.findById(id);

		String charsCaps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String chars = "abcdefghijklmnopqrstuvwxyz";
		String nums = "0123456789";
		String passSymbols = charsCaps + chars + nums;
		Random random = new Random();
		String password = "";
		for (int i = 0; i < 5; i++) {
			password = password + passSymbols.charAt(random.nextInt(passSymbols.length()));
		}

		LOGGER.info("UserController :: resetUserPassword :: username is  " + user.getUsername() + " new password is:"
				+ password);

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		user.setPassword(hashedPassword);
		userRepository.save(user);

		return "redirect:" + "/admin/userList";

	}

	@RequestMapping(value = "/admin/searchUser", method = RequestMethod.POST)
	public String userSearch(@ModelAttribute UserDisplay user, Model model, Pageable pageable) {
		LOGGER.info("userSearch :: " + user.getUsername()); // Page<User>
		
		return "redirect:" + "/admin/userList";
	}

	

}
