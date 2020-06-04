package com.capgemini.loansystem.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.loansystem.entity.User;
import com.capgemini.loansystem.response.JwtResponse;
import com.capgemini.loansystem.response.Response;
import com.capgemini.loansystem.service.JwtUtil;
import com.capgemini.loansystem.service.UserService;

//@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*", exposedHeaders = "Access-Control-Allow-Origin")
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil; 
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) throws Exception{
	
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUser_id() + "", user.getPassword()));
		} catch(DisabledException de) {
			//we should use loggers here
			System.out.println("User is Disabled");
			throw new Exception("USER_DISABLED",de);
			
		} catch(BadCredentialsException bce) {
			//we should  use loggers here
			throw new Exception("INVALID_CREDENTIALS", bce);
		
		}// End of try catch
		
		final UserDetails userDetails = userDetailService.loadUserByUsername(user.getUser_id()+"");
	
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(jwt)); 
	}// End of login()

	
	@GetMapping("/users")
	public Response<List<User>> findAll() {
		List<User> lists = userService.findAll();
		return new Response<> (false,"list retrieved", lists);
	}
	
	@GetMapping("/users/{userId}")
	public Response<User> getUser(@PathVariable int userId) {
		User user = userService.findById(userId);
		
		if(user == null) {
				return new Response<User>(false, "user id found", user);
		} else {
			throw new RuntimeException("user id not found : "+userId);
		}
	}
	
	@PostMapping("/users")
	public Response<User> addUser(@RequestBody User user) {
		user.setUser_id(0);
		User users = userService.save(user);
		if(users != null) {
			return new Response<User>(false, "user saved successfully", users);
		} else {
			throw new RuntimeException("user not saved");
		}
	}
	
	@PutMapping("/users")
	public Response<User> updateUser(@RequestBody User user) {
		User users = userService.save(user);
		if(users != null) {
			return new Response<User>(false, "user updated successfully", users);
		} else {
		throw new RuntimeException("not updated");
		}
	}
	
	@DeleteMapping("/users/{userId}")
	public Response<User> deleteUser(@PathVariable int userId) {
		User tempusers = userService.findById(userId);
		
		if(tempusers != null) {
			userService.deleteById(userId);
			return new Response<User>(false, "user id :" + userId +" "+ "deleted successfully", tempusers);
		} else {
			throw new RuntimeException("user id not found : "+userId);
		}
		
	}

	
	@GetMapping("/users/{pageNo}/{itemsPerPage}")
	public Page<User> getUser(@PathVariable int pageNo , @PathVariable int itemsPerPage) {
		return userService.getUser(pageNo, itemsPerPage);
	}
	
	@GetMapping("/users/{pageNo}/{itemsPerPage}/{fieldName}")
	public Page<User> getSortUser(@PathVariable Integer pageNo,@PathVariable Integer itemsPerPage,@PathVariable String fieldName) {
		return userService.getSortUser(pageNo, itemsPerPage, fieldName);
	}
	

}
