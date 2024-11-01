package com.sh.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.blog.payloads.ApiResponse;
import com.sh.blog.payloads.CommentDTO;
import com.sh.blog.services.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController 
{
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/create/{postId}")
	public ResponseEntity<CommentDTO> create(
												@RequestBody CommentDTO commentDTO , 
												@PathVariable Integer postId)
	{
		CommentDTO created = this.commentService.createComment(commentDTO, postId);

		return new ResponseEntity<CommentDTO>(created , HttpStatus.CREATED);
	}

	
	@DeleteMapping("/delete/{commentId}")
	public ResponseEntity<?> create(@PathVariable Integer commentId)
	{
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted" , true), HttpStatus.OK);
		
	}
} 
