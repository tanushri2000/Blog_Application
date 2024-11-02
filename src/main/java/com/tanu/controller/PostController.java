package com.tanu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tanu.binding.AddPostForm;
import com.tanu.binding.EditPostForm;
import com.tanu.entity.Posts;
import com.tanu.service.PostService;

@Controller
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private HttpSession session;

	@GetMapping("/logout")
	public String logout() {
		return "redirect:/";
	}

	@GetMapping("/addPost")
	public String addPostPage(Model model) {
		model.addAttribute("addPostForm", new AddPostForm());
		return "addPost";
	}

	@PostMapping("/addPost")
	public String addPostHandler(@ModelAttribute("addPostForm") AddPostForm addPostForm, Model model) {

		boolean status = postService.savAddPost(addPostForm);
		if (status) {
			model.addAttribute("successMsg", "Blog Post successfully");
		} else {
			model.addAttribute("errorMsg", "failed to Post Blog");
		}
		return "addPost";
	}

	@GetMapping("/dashboard")
	public String viewUserPostPage(Model model) {

		List<Posts> allPosts = postService.getLogedInUserAllPosts();
		model.addAttribute("posts", allPosts);
		return "dashboard";
	}

	@GetMapping("/dashboard-filter")
	public String viewPostFilterPage(@RequestParam String title, Model model) {
		Integer userId = (Integer) session.getAttribute("userId");
		List<Posts> posts = postService.getAllFilterPosts(title, userId);
		model.addAttribute("posts", posts);
		return "dashboard-filter";
	}

	@GetMapping("/editPost")
	public String editPage( @RequestParam("postId") Integer postId, Model model) {

		Posts editData = postService.getEditDataByPostId(postId);
		model.addAttribute("editPost", editData);

		return "editPost";

	}

	@PostMapping("/updatePost")
	public String addPostHandler(@ModelAttribute("editPost") EditPostForm editPostForm, Model model) {

		boolean status = postService.updatePost(editPostForm);
		if (status) {
			model.addAttribute("successMsg", "Blog update successfully");
		} else {
			model.addAttribute("errorMsg", "failed to update Blog");
		}
		return "editPost";
	}
	@GetMapping("/deletePost")
	public String deletehandler( @RequestParam("postId") Integer postId, Model model) {

		postService.deletePost(postId);
		List<Posts> allPosts = postService.getLogedInUserAllPosts();
		model.addAttribute("posts", allPosts);
		return "dashboard";

	}
	
	

}
