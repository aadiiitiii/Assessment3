package com.pack.assessment.dao;

import java.util.List;
import com.pack.assessment.model.Post;
import com.pack.assessment.model.User;

public interface UserDAO {
	public int creatUser(User user);
	public List<User> listAllUser();
	public User findByIdUser(long id);
	public int updateUser(long id, User user);
	public int deleteUser(long id);
	public int createPost(long id, Post post);
	public List<Post> listAllPost();
	public List<Post> listAllPostById(long id);
	public int deletePostById(long user_id, long post_id);
	public int deleteAllPostByUserId(long id);
	
}
