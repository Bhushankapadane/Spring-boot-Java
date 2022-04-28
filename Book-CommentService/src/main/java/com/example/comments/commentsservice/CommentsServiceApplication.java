package com.example.comments.commentsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import com.example.comments.commentsservice.filter.CommentFilter;

@SpringBootApplication
public class CommentsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentsServiceApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean favFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new CommentFilter()); 
		filterRegistrationBean.addUrlPatterns("/api/*");
		return filterRegistrationBean;
	}

}
