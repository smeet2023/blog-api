package com.sh.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sh.blog.entites.Category;
import com.sh.blog.entites.Post;
import com.sh.blog.entites.User;

public interface PostRepository extends JpaRepository<Post, Integer>
{
	List<Post> findByUser(User user); 
	List<Post> findByCategory(Category ctg);
	
	List<Post> findBypostTitleContaining(String title);
}
