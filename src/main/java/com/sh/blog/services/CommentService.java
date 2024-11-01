package com.sh.blog.services;

import com.sh.blog.payloads.CommentDTO;

public interface CommentService 
{
	CommentDTO createComment(CommentDTO commentDTO , Integer postId);
	void deleteComment(Integer comment_id);
	
}
