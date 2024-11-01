package com.sh.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.blog.payloads.ApiResponse;
import com.sh.blog.payloads.CategoryDTO;
import com.sh.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController 
{
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO categoryDTO)
	{
		CategoryDTO created = this.categoryService.createCategory(categoryDTO);
		return new ResponseEntity<CategoryDTO>(created , HttpStatus.CREATED) ;
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<CategoryDTO> update(@Valid @RequestBody CategoryDTO categoryDTO , @PathVariable Integer id)
	{
		CategoryDTO updated = this.categoryService.updateCategory(categoryDTO, id);
		return new ResponseEntity<CategoryDTO>(updated , HttpStatus.OK) ;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable Integer id)
	{
		this.categoryService.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted" , true), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> get(@PathVariable Integer id)
	{
		CategoryDTO categoryDTO = this.categoryService.getCategory(id);
		return new ResponseEntity<CategoryDTO>(categoryDTO,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> getAll()
	{
		return ResponseEntity.ok(this.categoryService.getCategory());
	}
}
