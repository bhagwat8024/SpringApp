package com.bhagwat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bhagwat.dao.UserDAO;
import com.bhagwat.model.User;


public interface UserService {

public void saveUser(User user);

public boolean isUserExist(String username,String password);

}
