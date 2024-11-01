package com.sh.blog.services.implmentation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.blog.entites.Category;
import com.sh.blog.exceptions.ResourceNotFoundException;
import com.sh.blog.payloads.CategoryDTO;
import com.sh.blog.repositories.CategoryRepository;
import com.sh.blog.services.CategoryService;

@Service
public class CategoryServiceImplementation implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) 
	{
		Category ctg = this.modelMapper.map(categoryDTO, Category.class);
		Category addedCTG = this.categoryRepository.save(ctg);
		return this.modelMapper.map(addedCTG, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer id) 
	{
		Category ctg = this.categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID ", id));
		
		ctg.setCategoryTitle(categoryDTO.getCategoryTitle());
		ctg.setCategoryDescription(categoryDTO.getCategoryDescription());
		
		Category updatedCategory = this.categoryRepository.save(ctg);

		return this.modelMapper.map(updatedCategory, CategoryDTO.class);
	}

	@Override
	public void deleteCategory(Integer id) 
	{
		Category ctg = this.categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID ", id));
		
		this.categoryRepository.delete(ctg);
	}

	@Override
	public CategoryDTO getCategory(Integer id) 
	{
		Category ctg = this.categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID ", id));
		
		return this.modelMapper.map(ctg, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getCategory() 
	{
		List<Category> getAllCategories = this.categoryRepository.findAll();
		
		List<CategoryDTO> categoryDTOs= getAllCategories.stream().map((e)-> 	 this.modelMapper.map(e, CategoryDTO.class)).collect(Collectors.toList());
		
		return categoryDTOs;
	}
}
