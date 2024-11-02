
package com.tanu.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanu.entity.Posts;
import com.tanu.repo.PostRepo;
import com.tanu.service.IndexService;

@Service
public class IndexServiceImpl implements IndexService {

	@Autowired
	private PostRepo postRepo;

	@Override
	public List<Posts> getAllPosts() {
		List<Posts> postsList = postRepo.findAll();
		List<Posts> filterPost = postsList.stream()
				.filter(p->"Y".equals(p.getDeleteStatus()))
				.collect(Collectors.toList());
		return filterPost;
	}

	@Override
	public List<Posts> getFilterAllPosts(String title) {
		List<Posts> postsList = postRepo.findAll();
		List<Posts> filterPost = postsList.stream()
				.filter(p->"Y".equals(p.getDeleteStatus()))
				.collect(Collectors.toList());
		if (title != null && !title.isEmpty()) {
			return filterPost.stream().filter(post -> post.getTitle().toLowerCase().contains(title.toLowerCase()))
					.collect(Collectors.toList());
		}
		return filterPost;
	}

}
