
package com.tanu.service;

import java.util.List;

import com.tanu.entity.Posts;

public interface IndexService {

	public List<Posts> getAllPosts();

	public List<Posts> getFilterAllPosts(String title);

}
