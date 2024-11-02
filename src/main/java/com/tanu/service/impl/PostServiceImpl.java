package com.tanu.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanu.binding.AddPostForm;
import com.tanu.binding.EditPostForm;
import com.tanu.entity.Posts;
import com.tanu.entity.Users;
import com.tanu.repo.PostRepo;
import com.tanu.repo.UserRepo;
import com.tanu.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private HttpSession session;

	@Autowired
	private UserRepo userRepo;

	@Override
	public boolean savAddPost(AddPostForm addPostForm) {

		Posts postEntity = new Posts();
		BeanUtils.copyProperties(addPostForm, postEntity);
		postEntity.setDeleteStatus("Y");

		Integer userId = (Integer) session.getAttribute("userId");

		Users user = userRepo.findById(userId).get();

		postEntity.setUser(user);

		postRepo.save(postEntity);

		return true;
	}

	@Override
	public List<Posts> getLogedInUserAllPosts() {
		Integer userId = (Integer) session.getAttribute("userId");

		Optional<Users> user = userRepo.findById(userId);
		if (user.isPresent()) {
			Users users = user.get();
			List<Posts> posts = users.getPosts();
			List<Posts> filterPost = posts.stream()
			.filter(p->"Y".equals(p.getDeleteStatus()))
			.collect(Collectors.toList());

			return filterPost;
		}
		return Collections.emptyList();
	}

	@Override
	public List<Posts> getAllFilterPosts(String title, Integer userId) {

		Optional<Users> user = userRepo.findById(userId);
		if (user.isPresent()) {
			Users users = user.get();
			List<Posts> posts = users.getPosts();
			if (title != null && !title.isEmpty()) {
				return posts.stream().filter(post -> post.getTitle().toLowerCase().contains(title.toLowerCase()))
						.collect(Collectors.toList());
			}
			return posts;

		}
		return Collections.emptyList();

	}

	@Override
	public Posts getEditDataByPostId(Integer postId) {
		Optional<Posts> post = postRepo.findById(postId);
		if (post.isPresent()) {
			Posts posts = post.get();
			return posts;
		}
		return null;
	}

	@Override
	public boolean updatePost(EditPostForm editPostForm) {
		Posts postEntity = new Posts();
		BeanUtils.copyProperties(editPostForm, postEntity);
		postEntity.setDeleteStatus("Y");

		Integer userId = (Integer) session.getAttribute("userId");

		Users user = userRepo.findById(userId).get();

		postEntity.setUser(user);

		postRepo.save(postEntity);

		return true;
	}

	@Override
	public void deletePost(Integer postId) {
		Optional<Posts> post = postRepo.findById(postId);
		if (post.isPresent()) {
			Posts posts = post.get();
			posts.setDeleteStatus("N");
			
			postRepo.save(posts);
		 
		}
		
	}

}
