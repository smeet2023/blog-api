package com.sh.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO 
{
	private int comment_id;
	private String content;
//	private Post post; 
}
