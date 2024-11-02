package com.tanu.service;

import java.util.List;

import com.tanu.binding.AddPostForm;
import com.tanu.binding.EditPostForm;
import com.tanu.entity.Posts;

public interface PostService {
	
	public boolean savAddPost(AddPostForm addPostForm);
	
	public List<Posts> getLogedInUserAllPosts();
	
	public List<Posts> getAllFilterPosts(String title,Integer userId);
	
	 public Posts getEditDataByPostId(Integer postId); 
	 
	 public boolean updatePost(EditPostForm editPostForm);
	 
	 public void deletePost(Integer postId);

}
