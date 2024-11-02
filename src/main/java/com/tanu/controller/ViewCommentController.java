
package com.tanu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tanu.binding.AddCommentForm;
import com.tanu.entity.Comments;
import com.tanu.entity.Posts;
import com.tanu.service.ViewCommentService;

@Controller
public class ViewCommentController {

	@Autowired
	private ViewCommentService viewCommentService;

	@GetMapping("/readPost")
	public String readPostPage(@RequestParam Integer postId, Model model) {
		Posts posts = viewCommentService.getPostById(postId);
		   List<Comments> allComments = viewCommentService.getAllCommentsByPostId(postId);
		
		model.addAttribute("post", posts);
		model.addAttribute("comments", allComments);
		model.addAttribute("commentForm", new AddCommentForm());

		return "readPost";
	}

	@PostMapping("/comment")

	@ResponseBody
	public Comments readPostPage(@RequestBody AddCommentForm commentForm, @RequestParam Integer postId) {

		return viewCommentService.saveComment(commentForm, postId);

	}
	

	@GetMapping("/comment")

	public String commentsPage( Model model) {
		List<Comments> allComments = viewCommentService.getAllComments();
		model.addAttribute("comments", allComments);
		return "comments";

	}
	
	


	@GetMapping("/deleteComment")

	public String deletecComments(@RequestParam Integer commentId, Model model) {
		viewCommentService.deleteComment(commentId);
		List<Comments> allComments = viewCommentService.getAllComments();
		model.addAttribute("comments", allComments);
		return "comments";

	}

}
