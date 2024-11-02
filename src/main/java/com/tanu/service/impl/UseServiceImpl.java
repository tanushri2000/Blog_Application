package com.tanu.service.impl;





import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanu.binding.UserLoginForm;
import com.tanu.binding.UserRegisterForm;


import com.tanu.entity.Users;
import com.tanu.repo.UserRepo;
import com.tanu.service.UserService;

@Service
public class UseServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private HttpSession session;

	

	@Override
	public boolean saveUser(UserRegisterForm register) {
		//  check duplicate email
		Users user = userRepo.findByEmail(register.getEmail());
		if(user!=null) {
			return false;
		}
		//copy binding obj data to entity obj
		Users entity= new Users();
		BeanUtils.copyProperties(register, entity);
		//save entity obj into db
		userRepo.save(entity);
		
		return true;
	}



	@Override
	public String loginUser(UserLoginForm login) {
	//  check user based on email and pass
		Users userEntity = userRepo.findByEmailAndPassword(login.getEmail(), login.getPassword());
		
		if(userEntity==null) {
			return "Invalid Cedentials";
		}
		//if condition false then create session 
		session.setAttribute("userId", userEntity.getUserId());
		
		
		return "success";
	}



	
}
