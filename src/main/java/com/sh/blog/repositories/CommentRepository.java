package com.sh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sh.blog.entites.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>
{
	
}
