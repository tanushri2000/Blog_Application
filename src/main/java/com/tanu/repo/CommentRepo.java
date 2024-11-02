package com.tanu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanu.entity.Comments;
import com.tanu.entity.Posts;



public interface CommentRepo  extends JpaRepository<Comments,Integer>{
	 public  List<Comments> findByPost(Posts post);
	
	

}
