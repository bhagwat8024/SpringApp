package com.bhagwat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bhagwat.dao.UserDAO;
import com.bhagwat.model.User;

@Service
public class UserServiceImpl implements UserService {

@Autowired
private UserDAO userDAO;

@Transactional
public void saveUser(User user) {
	userDAO.saveUser(user);
}

@Transactional
public boolean isUserExist(String username,String password) {
	return userDAO.isUserExist(username, password);
}

}
