package com.tanu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tanu.entity.Posts;
import com.tanu.service.IndexService;

@Controller
public class IndexController {

	@Autowired
	private IndexService indexService;

	@GetMapping("/")
	public String loadIndexPage(Model model) {
		List<Posts> allPosts = indexService.getAllPosts();
		model.addAttribute("posts", allPosts);
		return "index";
	}

	@GetMapping("/filter")
	public String loadIndexFilterPage(@RequestParam String title, Model model) {
		List<Posts> filterAllPosts = indexService.getFilterAllPosts(title);
		model.addAttribute("posts", filterAllPosts);
		return "index-filter";
	}

}
