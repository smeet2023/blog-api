package com.sh.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO 
{
	private int id;
	private String postTitle;
	private String postContent;
	
	private String imageName;
	private Date addedDate;
	private CategoryDTO category;
	private UserDTO user;
	
	private Set<CommentDTO> comments = new HashSet<>();
	
	
}
