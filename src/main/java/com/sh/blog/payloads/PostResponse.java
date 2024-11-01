package com.sh.blog.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostResponse 
{
	private List<PostDTO> content;
	private Integer pageNumber;
	private Integer pageSize;
	private long totalElements;
	private Integer totalPages;
	
	private boolean lastPage;
	
	
}
