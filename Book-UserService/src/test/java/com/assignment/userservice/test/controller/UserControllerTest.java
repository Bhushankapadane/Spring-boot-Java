package com.assignment.userservice.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.assignment.userservice.controller.UserController;
import com.assignment.userservice.exception.UserAlreadyExistsException;
import com.assignment.userservice.model.User;
import com.assignment.userservice.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;



@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	private MockMvc mockMvc;
	private User user;
	
	@Mock
	UserServiceImpl userService;
	
	@Autowired
	private MockHttpSession session;
	
	@InjectMocks
	UserController authController;
	
	@Autowired
	private WebTestClient webTestClient;
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
        user = new User();
        user.setUserName("john");
        user.setPassword("johnpass");
        user.setFirstName("John");
        user.setLastName("Kumar");
        user.setEmailId("john@gmail.com");
        user.setMobileNumber("1234567789");
		// creatin Session object and setting session
		session = new MockHttpSession();
		session.setAttribute("username", user.getUserName());
	}
	
	 @Test
	 public void registerUserSuccess() throws Exception {

	        when(userService.registerUser(user)).thenReturn(user);
	        mockMvc.perform(post("/adduser")
	                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
	                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());

	 }
	 
	 @Test
	 public void registerUserFailure() throws Exception {

	        when(userService.registerUser(any())).thenThrow(UserAlreadyExistsException.class);
	        mockMvc.perform(post("/adduser")
	                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
	                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());

	  }
	
	@Test
	public void testLoginSuccess() throws Exception {

		when(userService.validateUser(user)).thenReturn(user);
		mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void testLoginFailure() throws Exception {
		when(userService.validateUser(user)).thenReturn(null);
		mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isUnauthorized()).andDo(print());
	}
	
	@Test
	public void testLogoutSuccess() throws Exception {
		mockMvc.perform(get("/logout").session(session)).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void testLogoutFailure() throws Exception {
		mockMvc.perform(get("/logout")).andExpect(status().isBadRequest()).andDo(print());
	}
	
//	@Test
//	public void testGetAllVolumesSuccess() throws Exception {
//		mockMvc.perform(get("/books").queryParam("search", "react").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//		.andExpect(jsonPath("$", hasSize(40))).andDo(print());
//	}
	
	public static String asJsonString(final Object obj) {
		try {

			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
