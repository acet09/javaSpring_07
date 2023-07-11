package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();

	static {
		users.add(new User(1, "Adam", LocalDate.now().minusYears(30)));
		users.add(new User(2, "jim", LocalDate.now().minusYears(15)));
		users.add(new User(3, "cote", LocalDate.now().minusYears(22)));
	}
	
	public List<User> findAll() {
		return users;
	}

}
