package com.sh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sh.blog.entites.User; 

@RepositoryRestResource()
public interface UserRepository extends JpaRepository<User , Integer> 
{
//	List<Post> findByUsers(User user);
//	List<Post> findByCategory(Category ctg);
}
