package com.sh.blog.entites;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false , length = 100)
	private String name;
	
	@Column(name = "email" , nullable = false , length = 30)
	private String email;
	
	@Column(name = "password" , nullable = false , length = 12)
	private String password;
	
	@Column(name = "About" , nullable = false , length = 50)
	private String about;
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Post> posts = new ArrayList<>();
	
	
}
