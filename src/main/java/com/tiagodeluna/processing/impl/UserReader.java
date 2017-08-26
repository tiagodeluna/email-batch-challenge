package com.tiagodeluna.processing.impl;

import java.util.Iterator;
import java.util.List;

import com.tiagodeluna.backend.User;
import com.tiagodeluna.processing.ItemReader;

/**
 * 
 * @author Tiago Luna
 *
 */
public class UserReader implements ItemReader<User> {

	private List<User> users;
	private Iterator<User> iterator;

	public UserReader(List<User> items) {
		super();
		this.users = items;
		this.iterator = items.iterator();
	}

	public User read() {
		if (iterator.hasNext()) {
			return iterator.next();
		}
		
		iterator = users.iterator();
		return null;
	}

}
