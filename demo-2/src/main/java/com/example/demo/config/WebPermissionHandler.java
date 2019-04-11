package com.example.demo.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class WebPermissionHandler implements AccessDeniedHandler {
	private static Logger logger = LoggerFactory.getLogger(WebPermissionHandler.class);
	
	/*@Autowired
    private AuthenticationManager authenticationManager;*/
	
			@Override
			public void handle(	HttpServletRequest httpServletRequest,
                    			HttpServletResponse httpServletResponse,
                    			AccessDeniedException e) throws IOException, ServletException {

				 Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			     if (auth != null) {
			         logger.info("User '" + auth.getName()
			                 + "' attempted to access the protected URL: "
			                 + httpServletRequest.getRequestURI());
			     }
			
			     httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/403");

	}
			
	/*public void autoLogin(String username, String password) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

		authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		if (usernamePasswordAuthenticationToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		    logger.debug(String.format("Auto login %s successfully!", username));
		}
	}*/
}
