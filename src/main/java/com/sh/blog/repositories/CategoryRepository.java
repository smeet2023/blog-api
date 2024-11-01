package com.sh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sh.blog.entites.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> 
{
	
}
