package com.tanu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tanu.binding.UserLoginForm;
import com.tanu.binding.UserRegisterForm;
import com.tanu.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	

	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("regForm", new UserRegisterForm());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerHandler(@ModelAttribute("regForm") UserRegisterForm regForm,Model model) {
		boolean status = userService.saveUser(regForm);
		if(status) {
			model.addAttribute("successMsg", " Registeration successfull");
		}else {
			model.addAttribute("errorMsg", "Registration failed");
		}
		return "register";
	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("loginForm", new UserLoginForm());
		return "login";
	}
	
	@PostMapping("/login")
	public String loginHandler(@ModelAttribute("loginForm") UserLoginForm loginForm,Model model) {
		String status = userService.loginUser(loginForm);
		if(status=="success") {
		return "redirect:/dashboard	";
		}
		model.addAttribute("errorMsg", status);
		return "login";
	}

}
