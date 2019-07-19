package com.pack.assessment.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.pack.assessment.model.Post;
import com.pack.assessment.model.User;

@SuppressWarnings("unchecked")
@Repository("hibernateUserDAOImpl")
public class HibernateUserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public int creatUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
		return 1;
	}

	public List<User> listAllUser() {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession.createQuery("from User").list();
	}

	public User findByIdUser(long id) {
		return sessionFactory.getCurrentSession().get(User.class, id);

	}

	public int updateUser(long id, User user) {
		Session session = sessionFactory.getCurrentSession();
		User user2 = session.get(User.class,id);
		
		if(user2 !=null) {
			if(user.getUsername() != null)
				user2.setUsername(user.getUsername());
			if(user.getEmail() != null)
				user2.setEmail(user.getEmail());
			if(user.getAddress() != null)
				user2.setAddress(user.getAddress());
		}
		session.saveOrUpdate(user2);
		return 1;
	}

	public int deleteUser(long id) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.byId(User.class).load(id);
		session.delete(user);
		return 1;
	}

	public int createPost(long id, Post post) {
		Session session = sessionFactory.getCurrentSession();
		User user = findByIdUser(id);
		List<Post> posts = user.getPosts();
		posts.add(post);
		user.setPosts(posts);
		session.saveOrUpdate(user);
		return 1;
	}

	public List<Post> listAllPost() {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession.createQuery("from Post").list();
	}

	public List<Post> listAllPostById(long id) {
		return sessionFactory.getCurrentSession().get(User.class, id).getPosts();
	}

	public int deletePostById(long user_id, long post_id) {
		Session session = sessionFactory.getCurrentSession();
		User user = findByIdUser(user_id);
		List<Post> posts = user.getPosts();
		List<Post> userPosts = new ArrayList<Post>();
		Post dummy = new Post();
		boolean check = false;
		for (Post post : posts) {
			if (post.getId() == post_id) {
				dummy = post;
				check = true;
			} else {
				userPosts.add(post);
			}
		}
		user.setPosts(userPosts);
		session.saveOrUpdate(user);
		session.delete(dummy);

		if (check)
			return 1;
		else
			return 0;

	}

	public int deleteAllPostByUserId(long id) {
		Session session = sessionFactory.getCurrentSession();
		User user = findByIdUser(id);
		for (Post post : user.getPosts()) {
			session.delete(post);
		}
		user.setPosts(null);
		session.saveOrUpdate(user);
		return 1;
	}

}
