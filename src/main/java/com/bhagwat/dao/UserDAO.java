package com.bhagwat.dao;

import com.bhagwat.model.*;

public interface UserDAO {
	public void saveUser(User user);

	public boolean isUserExist(String username,String password);
}
