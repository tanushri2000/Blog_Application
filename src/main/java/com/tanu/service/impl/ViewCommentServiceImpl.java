
package com.tanu.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanu.binding.AddCommentForm;
import com.tanu.entity.Comments;
import com.tanu.entity.Posts;
import com.tanu.repo.CommentRepo;
import com.tanu.repo.PostRepo;
import com.tanu.repo.UserRepo;
import com.tanu.service.ViewCommentService;

@Service
public class ViewCommentServiceImpl implements ViewCommentService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private HttpSession session;

	@Override
	public Posts getPostById(Integer postId) {
		Optional<Posts> post = postRepo.findById(postId);
		if (post.isPresent()) {
			Posts posts = post.get();
			return posts;
		}
		return null;
	}

	public List<Comments> getAllCommentsByPostId(Integer postId) {
		Posts post = getPostById(postId); // Ensure the post exists
		List<Comments> comments = commentRepo.findByPost(post); // Get comments using the post object
		// Filter comments based on status
		return comments.stream().filter(c -> "Y".equals(c.getCommentStatus())).collect(Collectors.toList());
	}

	public Comments saveComment(AddCommentForm commentForm, Integer postId) {
		Comments comment = new Comments();
		BeanUtils.copyProperties(commentForm, comment);
		comment.setCommentStatus("Y");

		Optional<Posts> post = postRepo.findById(postId);
		if (post.isPresent()) {
			Posts posts = post.get();
			comment.setPost(posts);
			return commentRepo.save(comment); // Return the saved comment
		}
		throw new IllegalArgumentException("Post not found");
	}

	@Override
	public void deleteComment(Integer commentId) {

		Optional<Comments> comment = commentRepo.findById(commentId);
		if (comment.isPresent()) {
			Comments comments = comment.get();
			comments.setCommentStatus("N");
			commentRepo.save(comments);
		}
	}

	@Override
	public List<Comments> getAllComments() {
		
		Integer  userId =(Integer) session.getAttribute("userId");
		List<Comments> allComments = commentRepo.findAll();
		List<Comments> comments = allComments.stream()
				.filter(c -> "Y".equals(c.getCommentStatus()) && c.getPost().getUser().getUserId().equals(userId))

				.collect(Collectors.toList());
		return comments;
	}

}
