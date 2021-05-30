package com.spring.security.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MyAuthenticationEntiyPoint implements AuthenticationEntryPoint {
	public static final Logger logger = LoggerFactory.getLogger(MyAuthenticationEntiyPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		logger.error("Unauthorized error: {}", authException.getMessage());
	    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
	    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized Request..........");
	    
	    // display error in the postman software
	    final Map<String, Object> body = new HashMap<>();
	    body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
	    body.put("error", "Unauthorized");
	    body.put("message", authException.getMessage());
	    body.put("path", request.getServletPath());
	    
	    final ObjectMapper mapper=new ObjectMapper();
	    mapper.writeValue(response.getOutputStream(), body);
	}

}
