package com.example.comments.commentsservice.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class CommentFilter extends GenericFilter{

	@Override
	public void doFilter(ServletRequest request,
			ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String authHeader = req.getHeader("Authorization");
		
		if(req.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name())) {
			
			chain.doFilter(request, response);
		}else {
			
			if (authHeader == null || !authHeader.startsWith("Bearer")) {
				throw new ServletException("Missing or Invalid Authorization "
						+ " Header");
			}
			try {
				String token = authHeader.substring(7);
					
				Claims claim = Jwts.parser().
						setSigningKey("success").
						parseClaimsJws(token).getBody();
				
				req.setAttribute("claims", claim);
				chain.doFilter(request, response);
			} catch (Exception e) {
				resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token. Not Authorized");
			}
		}
	}

}
