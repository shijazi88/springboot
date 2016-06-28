package com.shijazi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "api")
public class UserController {

	private static List<User> users = new ArrayList<User>();

	public UserController() {
		User u1 = new User(1L, "shijazi", "password", "shijazi88@gmail.com", "Safwan", "Hijazi");
		User u2 = new User(2L, "test", "password", "test@gmail.com", "test", "test");
		users.add(u1);
		users.add(u2);
	}

	@RequestMapping(value = "users", method = RequestMethod.GET)
	public @ResponseBody List<User> getAllUsers() {
		return users;

	}

	@RequestMapping(value = "/users", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User createUser(@RequestBody User user) {
		// can add same user multiple times
		// I will check Id, if already existed, i will not add in the list
		long count = users.stream().filter(u -> u.getId().equals(user.getId())).count();
		if (count > 0) {
			return null;
		} else {
			users.add(user);
			return user;
		}

	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User deleteUser(@PathVariable Long id) {
		Optional<User> user = users.stream().filter(u -> u.getId().equals(id)).findFirst();
		if (user.isPresent()) {
			User u = user.get();
			users.remove(u);
			return u;
		}

		return null;

	}

	@RequestMapping(value = "/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User updateUserInformation(@RequestBody User user) {
		Optional<User> selectedUser = users.stream().filter(u -> u.getId().equals(user.getId())).findFirst();
		if (selectedUser.isPresent()) {
			User u = selectedUser.get();
			users.remove(u);
			users.add(user);
			return u;
		}
		else
		{
			return null;
		}
	}

}
