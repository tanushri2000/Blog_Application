package com.tanu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanu.entity.Users;



public interface UserRepo  extends JpaRepository<Users,Integer>{
	
    public Users findByEmail(String email);
	
	public Users findByEmailAndPassword(String email,String password);
}
