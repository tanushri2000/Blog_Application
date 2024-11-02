
package com.tanu.service;

import java.util.List;

import com.tanu.binding.AddCommentForm;
import com.tanu.entity.Comments;
import com.tanu.entity.Posts;

public interface ViewCommentService {

	public Posts getPostById(Integer postId);

	public Comments saveComment(AddCommentForm commentForm, Integer postId);

	
	public void deleteComment(Integer commentId);
	
	public List<Comments> getAllCommentsByPostId(Integer postId);
	
	public List<Comments> getAllComments();

}
