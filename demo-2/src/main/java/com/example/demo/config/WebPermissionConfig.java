package com.example.demo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.User;
import com.example.demo.UserRepository;
import com.example.demo.UserSearchRepository;

@Configuration
public class WebPermissionConfig extends WebSecurityConfigurerAdapter {
	
	//@Autowired
	//private AccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserSearchRepository userSearchRepository;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/", "/index", "/register").permitAll()
				.antMatchers("/home").hasAnyRole("USER")
				.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll()
				.and();
         
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		List<User> u = userRepository.findAll();
	    for(int i = 0; i < u.size(); i++) {
	    	auth.inMemoryAuthentication()
            .withUser(u.get(i).getUserName()).password("{noop}"+u.get(i).getPassWord()).roles("USER");
            
	    }
		
	}
}
