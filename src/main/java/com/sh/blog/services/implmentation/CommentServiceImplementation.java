package com.sh.blog.services.implmentation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.blog.entites.Comment;
import com.sh.blog.entites.Post;
import com.sh.blog.exceptions.ResourceNotFoundException;
import com.sh.blog.payloads.CommentDTO;
import com.sh.blog.repositories.CommentRepository;
import com.sh.blog.repositories.PostRepository;
import com.sh.blog.services.CommentService;

@Service
public class CommentServiceImplementation implements CommentService 
{
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Integer postId) 
	{
		Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post ", "id ", postId));
		
		Comment comment = this.modelMapper.map(commentDTO, Comment.class);
		comment.setPost(post);
		Comment created = this.commentRepository.save(comment);
		
		return this.modelMapper.map(created, CommentDTO.class);
	}

	@Override
	public void deleteComment(Integer comment_id) 
	{
		Comment comment = this.commentRepository.findById(comment_id).orElseThrow(() -> new ResourceNotFoundException("comment ", "id ", comment_id));;
		this.commentRepository.delete(comment);

	}

}
