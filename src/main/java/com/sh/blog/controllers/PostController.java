package com.sh.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sh.blog.config.AppConstants;
import com.sh.blog.payloads.ApiResponse;
import com.sh.blog.payloads.PostDTO;
import com.sh.blog.payloads.PostResponse;
import com.sh.blog.services.FileService;
import com.sh.blog.services.PostService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class PostController 
{
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	/////////////////////////////////////////////////////////////////////////////////////
	@PutMapping("/update/{id}/posts")
	public ResponseEntity<PostDTO> update(@RequestBody PostDTO postDTO , @PathVariable Integer id)
	{
		PostDTO post = this.postService.updatePost(postDTO, id);
		return new ResponseEntity<PostDTO>(post , HttpStatus.OK);
	}
	/////////////////////////////////////////////////////////////////////////////////////
	
	@PostMapping("/user/{userid}/category/{categoryid}/posts")   
	public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO , @PathVariable Integer userid , @PathVariable Integer categoryid)
	{
		PostDTO post = this.postService.post(postDTO , userid , categoryid);
		return new ResponseEntity<PostDTO>(post , HttpStatus.CREATED);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/user/{userid}/posts")
	public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable Integer userid)
	{
		List<PostDTO> postByUser = this.postService.getPostsByUser(userid);
		return new ResponseEntity<List<PostDTO>>(postByUser , HttpStatus.OK);
//		hold on	
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/category/{categoryid}/posts")
	public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable Integer categoryid)
	{
		List<PostDTO> postByCategory = this.postService.getPostsByCategory(categoryid);
		return new ResponseEntity<List<PostDTO>>(postByCategory , HttpStatus.OK);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/{postid}/posts")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postid)
	{
		PostDTO post = this.postService.getPostByID(postid);
		return new ResponseEntity<PostDTO>(post , HttpStatus.OK);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("posts/")
	public ResponseEntity<PostResponse> allPost(
			@RequestParam (
			value = "pageNumber" , defaultValue = AppConstants.PAGE_NUMBER ,required = false) 			Integer pageNumber, 
			
			@RequestParam (
			value = "pageSize" , defaultValue = AppConstants.PAGE_SIZE , required = false) 			Integer pageSize , 
			
			@RequestParam(
			value = "sortBy" , defaultValue = AppConstants.SORT_ID , required = false)
			String sortBy , 
			
			@RequestParam(
			value = "sortDir" , defaultValue = AppConstants.SORT_DIRECTION , required = false)
			String sortDir)
	{ 
		PostResponse post = this.postService.getAllPosts(pageNumber , pageSize , sortBy , sortDir);
		return new ResponseEntity<PostResponse>(post , HttpStatus.OK);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	@DeleteMapping("/delete/{id}/posts")
	public ApiResponse delete(@PathVariable Integer id)
	{
		this.postService.deletePost(id);
		return new ApiResponse("deleted" , true); 
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/posts/searchTitle/{keyword}") 
	public ResponseEntity<List<PostDTO>> searchByTitle(@PathVariable String keyword)
	{
		List<PostDTO> posts = this.postService.searchPosts(keyword);
//		Optional<List<PostDTO>> optionalPost = Optional.ofNullable(posts);
		if(posts.isEmpty())
			return new ResponseEntity<List<PostDTO>>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<List<PostDTO>>(posts , HttpStatus.OK);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	@PostMapping("/post/image/upload/{id}")
	public ResponseEntity<PostDTO> uploadPostImage(
			@PathVariable Integer id,
			@RequestParam("image") MultipartFile image) throws IOException
	{
		PostDTO postDto = this.postService.getPostByID(id);
		
		String fileName = this.fileService.uploadImage(path, image);
		postDto.setImageName(fileName);
		PostDTO updated = this.postService.updatePost(postDto, id);
		
		return new ResponseEntity<PostDTO>(updated , HttpStatus.OK);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping(value = "post/image/{imageName}" , produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable String imageName , 
			HttpServletResponse response) throws IOException
	{
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
	
	
	
	
	
	
	
}
