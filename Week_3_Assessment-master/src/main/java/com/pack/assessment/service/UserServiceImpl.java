package com.pack.assessment.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pack.assessment.dao.UserDAO;
import com.pack.assessment.model.Post;
import com.pack.assessment.model.User;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

	@Qualifier(value = "hibernateUserDAOImpl")
	private UserDAO userDAO;

	public UserServiceImpl(@Qualifier(value = "hibernateUserDAOImpl") UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Transactional
	public int createUser(User user) {
		return userDAO.createUser(user);
	}

	@Transactional
	public List<User> listAllUser() {
		return userDAO.listAllUser();
	}

	@Transactional
	public User findByIdUser(long id) {
		return userDAO.findByIdUser(id);
	}

	@Transactional
	public int updateUser(long id, User user) {
		return userDAO.updateUser(id, user);
	}

	@Transactional
	public int deleteUser(long id) {
		return userDAO.deleteUser(id);
	}

	@Transactional
	public int createPost(Long id, Post post) {
		return userDAO.createPost(id,post);
	}

	@Transactional
	public List<Post> listAllPost() {
		return userDAO.listAllPost();
	}

	@Transactional
	public List<Post> listAllPostById(long id) {
		return userDAO.listAllPostById(id);
	}

	@Transactional
	public int deletePostById(long user_id, long post_id) {
		return userDAO.deletePostById(user_id, post_id);
	}

	@Transactional
	public int deleteAllPostByUserId(long id) {
		return userDAO.deleteAllPostByUserId(id);
	}

}
