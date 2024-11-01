package com.sh.blog.services;

import java.util.List;

import com.sh.blog.payloads.PostDTO;
import com.sh.blog.payloads.PostResponse;

public interface PostService 
{
	PostDTO post(PostDTO postDTO , Integer userID , Integer categoryID);//
	PostDTO updatePost(PostDTO postDTO , Integer id);
	PostDTO getPostByID(Integer id);//
	PostResponse getAllPosts(Integer pageNumber , Integer pageSize , String sortBy , String sortDir);//
	void deletePost(Integer id);
	
	List<PostDTO> getPostsByCategory(Integer categoryID);//
	List<PostDTO> getPostsByUser(Integer id);///
	
	List<PostDTO> searchPosts(String keyword);
}
