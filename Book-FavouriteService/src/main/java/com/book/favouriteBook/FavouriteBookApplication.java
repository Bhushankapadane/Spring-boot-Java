package com.book.favouriteBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.book.favouriteBook.filter.FavouriteFilter;

@SpringBootApplication

public class FavouriteBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavouriteBookApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean favFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new FavouriteFilter()); 
		filterRegistrationBean.addUrlPatterns("/api/*");
		return filterRegistrationBean;
	}

}
