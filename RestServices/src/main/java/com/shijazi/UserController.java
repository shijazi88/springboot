package com.shijazi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="api")
public class UserController {

	private static List<User> users = new ArrayList<User>();

	public UserController() {
		User u1 = new User(1L, "shijazi","password","shijazi88@gmail.com","Safwan","Hijazi");
		User u2 = new User(1L, "test","password","test@gmail.com","test","test");
		users.add(u1);
		users.add(u2);
	}
	
	@RequestMapping(value="users", method=RequestMethod.GET)
	public @ResponseBody List<User> getAllUsers()
	{
		return users;
		
	}

}
