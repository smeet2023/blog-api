package com.sh.blog.services.implmentation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sh.blog.entites.Category;
import com.sh.blog.entites.Post;
import com.sh.blog.entites.User;
import com.sh.blog.exceptions.ResourceNotFoundException;
import com.sh.blog.payloads.PostDTO;
import com.sh.blog.payloads.PostResponse;
import com.sh.blog.repositories.CategoryRepository;
import com.sh.blog.repositories.PostRepository;
import com.sh.blog.repositories.UserRepository;
import com.sh.blog.services.PostService;


@Service
public class PostServiceImplementation implements PostService 
{
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PostDTO post(PostDTO postDTO , Integer userID , Integer categoryID) 
	{
		User user = this.userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("user ", "id", userID));
		
		Category ctg = this.categoryRepository.findById(categoryID).orElseThrow(() -> new ResourceNotFoundException("category ", "id", categoryID));
		
		Post post = this.modelMapper.map(postDTO, Post.class);
		post.setImageName(postDTO.getImageName());
		post.setAddedDate(new Date());
		post.setPostTitle(postDTO.getPostTitle());
		post.setPostContent(postDTO.getPostContent());

		post.setUser(user);
		post.setCategory(ctg);

		Post addedPost = this.postRepository.save(post);
		return this.modelMapper.map(addedPost, PostDTO.class);
		
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO, Integer id) 
	{
		 Post existingPost = this.postRepository.findById(id)
		            .orElseThrow(() -> new ResourceNotFoundException("post", "id", id));

		 if(postDTO.getPostTitle() != null)
		 {
			 existingPost.setPostTitle(postDTO.getPostTitle());
		 }
		 if (postDTO.getPostContent() != null)
		 {
			 existingPost.setPostContent(postDTO.getPostContent());
		 }
		 if(postDTO.getImageName() != null)
		 {
			 existingPost.setImageName(postDTO.getImageName());
		 }
		this.postRepository.save(existingPost);
		return this.modelMapper.map(existingPost, PostDTO.class);
	}

	@Override
	public PostDTO getPostByID(Integer id) 
	{
		Post post = this.postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
		return this.modelMapper.map(post, PostDTO.class);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber , Integer pageSize , 
									String sortBy , String sortDir) 
	{
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc"))
		{
			sort = Sort.by(sortBy).ascending();
		}
		else
		{
			sort = Sort.by(sortBy).descending();
		}
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize , sort);// Sort.by(sortBy));
		Page<Post> posts = this.postRepository.findAll(pageable);
		
		List<Post> allPosts = posts.getContent();
		
		List<PostDTO> allpostsDTO = allPosts.stream().map(e->this.modelMapper.map(e, PostDTO.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(allpostsDTO);
		postResponse.setPageNumber(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLastPage(posts.isLast());
		
		
		return postResponse;
	}

	@Override
	public void deletePost(Integer id) 
	{
		Post post = this.postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id ", id));
		this.postRepository.delete(post);
	}

	@Override
	public List<PostDTO> getPostsByCategory(Integer categoryID) 
	{
		Category ctg = this.categoryRepository.findById(categoryID).orElseThrow(() -> new ResourceNotFoundException("catgeory", "id ", categoryID));
		
		List<Post> postsByCategory = this.postRepository.findByCategory(ctg);
		List<PostDTO> postDTOByCategory = postsByCategory.stream().map(e->this.modelMapper.map(e, PostDTO.class)).collect(Collectors.toList());
		
		return postDTOByCategory;
	}

	@Override
	public List<PostDTO> getPostsByUser(Integer id) 
	{
		User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "user id", id));
		
		List<Post> postByUser = this.postRepository.findByUser(user);
		List<PostDTO> postDTOBYUser = postByUser.stream().map(e->this.modelMapper.map(e, PostDTO.class)).collect(Collectors.toList());
		
		return postDTOBYUser;
	}

	@Override
	public List<PostDTO> searchPosts(String keyword) 
	{
		List<Post> post = this.postRepository.findBypostTitleContaining(keyword);
		if(post == null)
		{
			throw new ResourceNotFoundException("post ","keyword ", 1);
		}
		
		List<PostDTO> postDTO = post.stream().map(e->this.modelMapper.map(e, PostDTO.class)).collect(Collectors.toList());
		
		return postDTO;
	}

//	@Override
//	public List<Post> searchPosts(String keyword) 
//	{
//		return null;
//	}

}
