package com.pack.assessment.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pack.assessment.model.Post;
import com.pack.assessment.model.User;
import com.pack.assessment.service.UserService;
import org.springframework.http.HttpStatus;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;
	@GetMapping(value = "/", produces = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public List<User> listAllUser() {
		return userService.listAllUser();
	}

	@GetMapping(value = "/{id}", produces = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE })
	public User findById(@PathVariable int id) {
		return userService.findByIdUser(id);
	}

	@PostMapping(value = "/", consumes = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE })
	public String saveUser(@Valid @RequestBody User user) {
		if (userService.createUser(user) == 1) {
			return "1";
		} else {
			return "0";
		}
	}

	@GetMapping(value = "/{id}/posts", produces = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public List<Post> listAllUSerPost(@PathVariable long id) {
		return userService.listAllPostById(id);
	}
	
	@DeleteMapping(value = "/{id}/posts/{post_id}")
	public String deleteAllPost(@PathVariable long id, @PathVariable long post_id) {
		if (userService.deletePostById(id, post_id) == 1) {
			return "1";
		} else {
			return "0";
		}
	}
	@PutMapping(value = "/{id}", consumes = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE })
	public String updateUser(@PathVariable int id, @RequestBody User user) {
		if (userService.updateUser(id,user) == 1) {
			return "1";
		} else {
			return "0";
		}
	}

	@DeleteMapping(value = "/{id}")
	public String deleteUser(@PathVariable long id) {
		if (userService.deleteUser(id) == 1) {
			return "1";
		} else {
			return "0";
		}
	}
	@PostMapping(value = "/{id}/posts", consumes = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE })
	public String saveUser(@PathVariable long id, @RequestBody Post post) {
		if (userService.createPost(id,post) == 1) {
			return "1";
		} else {
			return "0";
		}
	}
	@DeleteMapping(value = "/{id}/posts")
	public String deleteAllPost(@PathVariable long id) {
		if (userService.deleteAllPostByUserId(id) == 1) {
			return "1";
		} else {
			return "0";
		}
	}


}
