package com.tanu.service;



import com.tanu.binding.UserLoginForm;
import com.tanu.binding.UserRegisterForm;


public interface UserService {

	public boolean saveUser(UserRegisterForm reister);
	
	public String loginUser(UserLoginForm login);
	
	
}
