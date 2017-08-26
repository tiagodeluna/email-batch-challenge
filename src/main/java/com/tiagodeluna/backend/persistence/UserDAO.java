package com.tiagodeluna.backend.persistence;

import java.util.ArrayList;
import java.util.List;

import com.tiagodeluna.backend.User;

/**
 * This class emulates a DAO of {@link User}.
 * 
 * @author Tiago Luna
 *
 */
public class UserDAO {

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		
		list.add(new User("frodo@mail.com", true, 5, 5)); //#5
		list.add(new User("sam@mail.com", true, 0, 2));
		list.add(new User("merry@mail.com", false, 4, 0)); //#3
		list.add(new User("pippin@mail.com", false, 1, 1));
		list.add(new User("aragorn@mail.com", true, 2, 0)); //#4
		list.add(new User("gandalf@mail.com", false, 1, 2)); //#2
		list.add(new User("legolas@mail.com", false, 0, 3)); //#2
		list.add(new User("gimli@mail.com", false, 4, 2)); //#1
		
		return list;
	}
}
