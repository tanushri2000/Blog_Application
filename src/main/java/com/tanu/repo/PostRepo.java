package com.tanu.repo;



import org.springframework.data.jpa.repository.JpaRepository;

import com.tanu.entity.Posts;



public interface PostRepo  extends JpaRepository<Posts,Integer>{
	
	

}
