package com.sh.blog.services;

import java.util.List;

import com.sh.blog.payloads.CategoryDTO;

public interface CategoryService 
{
	public CategoryDTO createCategory(CategoryDTO categoryDTO);
	public CategoryDTO updateCategory(CategoryDTO categoryDTO , Integer id);
	public void deleteCategory(Integer id);
	public CategoryDTO getCategory(Integer id);
	
	List<CategoryDTO> getCategory();
	
}
