package com.sh.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO 
{
	private int id;
	
	@NotEmpty
	@Size(min = 4 , message = "MIN 4 MAX ANYTHING")
	private String name;
	
	@Email(message = "not valid")
	
	private String email;

	@NotEmpty
	@Size(min = 6 , max = 20 , message = "min 6 max 20")
//	@Pattern(regexp = )
	private String password;
	
	@NotNull
	@Size(max = 40)
	private String about;
}
