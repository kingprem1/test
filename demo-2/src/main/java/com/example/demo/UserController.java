package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserSearchRepository userSearchRepository;
	
	//AuthenticationManagerBuilder auth;
	
	@RequestMapping("/home")
	public String user(Model model) {
		model.addAttribute("userList", userRepository.findAll());
		//userRepository.deleteAll();
		return "home";
	}
	
	@RequestMapping("/login")
	public String getLogin(Model model) {
		//model.addAttribute("userList", userRepository.findAll());
		//userRepository.deleteAll();
		return "login";
	}
	
	/*@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute User user) {
		System.out.println(user.getUserName() + ":" +user.getPassWord());
		List<User> curUser = userSearchRepository.searchUsers(user.getUserName());
		for(int i = 0; i < curUser.size(); i++) {
			if(curUser.get(i).getUserName().equals(user.getUserName())) {
				if(curUser.get(i).getPassWord().equals(user.getPassWord())) {
					System.out.println("Wellcome " + curUser.get(i).getUserName());
					new ModelAndView("index","name",curUser.get(i).getUserName());
					return "index";
				}
			}
				
		}
		//System.out.println(user.getUserName() + ":" +user.getPassWord());
		return "login";
	}*/
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String register() {
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String addUser(@ModelAttribute User user) {
		System.out.println("Wellcome");
		System.out.println(user.getUserName() + ":" +user.getPassWord());
		userRepository.save(user);
		
		//auth.inMemoryAuthentication().withUser(user.getUserName()).password("{noop}"+user.getPassWord()).roles("USER");
		return "index";
	}
	
	@RequestMapping(value = "/search")
	public String search(Model model, @RequestParam String search) {
		System.out.println("-"+search+"-");
		model.addAttribute("userList", userSearchRepository.searchUsers(search));
		model.addAttribute("search", search);
		return "home";
	}
}
