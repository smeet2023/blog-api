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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Category 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer categoryId;
	
	@Column(name = "title", nullable = false , length = 100)
	private String categoryTitle;
	
	@Column(name = "description" ,length = 100) 
	private String categoryDescription;  
	
	@OneToMany(mappedBy = "category" , cascade = CascadeType.ALL )
	@JsonManagedReference
	private List<Post> posts = new ArrayList<>();

}
