package com.sh.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDTO 
{
	private Integer categoryId;
	
	@NotBlank
	@Size(min = 6,max = 110)
	private String categoryTitle;

	@NotEmpty
	@Size(max = 100)  // if you add message attribute , it says "Must not be blank"
	private String categoryDescription;
}
