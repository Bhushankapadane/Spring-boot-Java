package com.assignment.userservice.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.assignment.userservice.exception.UserAlreadyExistsException;
import com.assignment.userservice.exception.UserNotValidException;
import com.assignment.userservice.mail.MailMethod;
import com.assignment.userservice.model.Book;
import com.assignment.userservice.model.BookList;
import com.assignment.userservice.model.User;
import com.assignment.userservice.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin(origins = "*",allowedHeaders="*")
public class UserController {
	

	@Autowired
	private UserService userService;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	
	@PostMapping("/adduser")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		ResponseEntity<?> entity = null;
		try {
			userService.registerUser(user);
			//MailMethod.sendMail(user.getEmailId(), s1, subject, content);
			entity = new ResponseEntity<String>("User Added successfully", HttpStatus.CREATED);
			
		} catch (UserAlreadyExistsException e) {
			// TODO Auto-generated catch block
			entity = new ResponseEntity<String>("User Already Exists", HttpStatus.CONFLICT);
		}
		return entity;
	}
	
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers()
	{
		ResponseEntity<?> entity = null;
		List<User> allUsers=userService.getAllUsers();
		entity = new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
		return entity;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> validateUser(@RequestBody User user,HttpSession session)
	{
		ResponseEntity<?> entity = null;
		User loginUser;
		String token="";
		try {
			loginUser=userService.validateUser(user);
			if(loginUser!=null) {
				session.setAttribute("username", user.getUserName());
				token = getToken(user.getEmailId());
				
				System.out.println("####################Token: " +token);
				System.out.println("####################Token: " +token);
				System.out.println("####################Token: " +token);
				System.out.println("####################Token: " +token);
				System.out.println("####################Token: " +token);
				
				System.out.println("####################Token: " +token);
				System.out.println("####################Token: " +token);
				entity = new ResponseEntity<String>(token,HttpStatus.OK);
			}
			else {
				entity = new ResponseEntity<String>("Invalid Username/Password",HttpStatus.UNAUTHORIZED);
			}
		} catch (UserNotValidException e) 
		{
			entity = new ResponseEntity<String>("Invalid Username/Password",HttpStatus.UNAUTHORIZED);
		}
		return entity;
	}
	
	private String getToken(String username) {
		long expiryTime = System.currentTimeMillis()+(1000*60*5);
		String token =  Jwts.builder()
		.setSubject(username)
		.setIssuedAt(new Date())
		.setExpiration(new Date(expiryTime))
		.signWith(SignatureAlgorithm.HS256, "success")
		.compact();
		return token;
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session)
	{
		ResponseEntity<?> entity = null;
		if(session!=null && session.getAttribute("username")!=null)
		{
			session.invalidate();
			entity = new ResponseEntity<String>("Logged Out Successfully",HttpStatus.OK);
		}
		else
		{
			entity = new ResponseEntity<String>("Not Logged In",HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	
	@GetMapping("/books")
	public ResponseEntity<?> getAllVolumes(@RequestParam("search") String search,@RequestParam("category") String category) {
		ResponseEntity<?> entity = null;
		try {
			String uri="";
			if(category.equals("")) {
				uri="https://www.googleapis.com/books/v1/volumes?q="+search+"&maxResults=40";
			}
			else {
				uri = "https://www.googleapis.com/books/v1/volumes?q="+search+"+subject:"+category+"&maxResults=40";
			}
			BookList bookList = webClientBuilder.build()
			.get()
			.uri(uri)
			.retrieve()
			.bodyToMono(BookList.class)
			.block();
			
			if(bookList!=null) {
				entity = new ResponseEntity<BookList>(bookList, HttpStatus.OK);
			}
			else {
				entity = new ResponseEntity<String>("Book is Empty", HttpStatus.NO_CONTENT);
			}
			
		} catch(Exception e) {
			// TODO Auto-generated catch block
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	/*
	 * @GetMapping("/books/{id}") public ResponseEntity<?>
	 * getAllVolumesById(@PathVariable("id") String id) { ResponseEntity<?> entity =
	 * null; try { Book book = webClientBuilder.build() .get()
	 * .uri("https://www.googleapis.com/books/v1/volumes/"+id) .retrieve()
	 * .bodyToMono(Book.class) .block();
	 * 
	 * if(book!=null) { entity = new ResponseEntity<Book>(book, HttpStatus.OK); }
	 * else { entity = new ResponseEntity<String>("BookId is Empty",
	 * HttpStatus.NO_CONTENT); }
	 * 
	 * } catch(Exception e) { // TODO Auto-generated catch block entity = new
	 * ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST); } return
	 * entity; }
	 */
}
