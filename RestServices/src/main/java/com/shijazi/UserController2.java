package com.shijazi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// use @RestController = @Controller + 


@RestController
@RequestMapping("api2")
public class UserController2 {

	
	private static List<User> users = new ArrayList<User>();

	public UserController2() {
		User u1 = new User(1L, "shijazi", "password", "shijazi88@gmail.com", "Safwan", "Hijazi");
		User u2 = new User(2L, "test", "password", "test@gmail.com", "test", "test");
		users.add(u1);
		users.add(u2);
	}

	@RequestMapping(value = "users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<>(users,HttpStatus.OK);

	}

	@RequestMapping(value = "/users", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody User user) {
		// can add same user multiple times
		// I will check Id, if already existed, i will not add in the list
		long count = users.stream().filter(u -> u.getId().equals(user.getId())).count();
		if (count > 0) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		} else {
			users.add(user);
			return new ResponseEntity<User>(user,HttpStatus.CREATED);
		}

	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> deleteUser(@PathVariable Long id) {
		Optional<User> user = users.stream().filter(u -> u.getId().equals(id)).findFirst();
		if (user.isPresent()) {
			User u = user.get();
			users.remove(u);
			return new ResponseEntity<User>(u,HttpStatus.OK);
		}

		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

	}
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<User> getUserBy(@PathVariable Long id) {
		Optional<User> user = users.stream().filter(u -> u.getId().equals(id)).findFirst();
		if (user.isPresent()) {
			User u = user.get();
			return new ResponseEntity<User>(u,HttpStatus.OK);
		}
		
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		
	}
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<User> getUserByUsingRequestParam(@RequestParam(name="id") Long id) {
		Optional<User> user = users.stream().filter(u -> u.getId().equals(id)).findFirst();
		if (user.isPresent()) {
			User u = user.get();
			return new ResponseEntity<User>(u,HttpStatus.OK);
		}
		
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUserInformation(@RequestBody User user) {
		Optional<User> selectedUser = users.stream().filter(u -> u.getId().equals(user.getId())).findFirst();
		if (selectedUser.isPresent()) {
			User u = selectedUser.get();
			users.remove(u);
			users.add(user);
			return new ResponseEntity<User>(u,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
}
