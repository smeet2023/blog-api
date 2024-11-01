package com.sh.blog.entites;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "post")
public class Post 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "post_id") 
	private Integer id;
	
	@Column(name = "post_Title", nullable = false , length = 20)
	private String postTitle;
		
	@Column(name = "post_content", nullable = false , length = 100)
	private String postContent;
	
	@Column(name = "image_name")
	private String imageName;
	 
	@Column(name = "added_date" , nullable = false )
	private Date addedDate;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	@JsonBackReference
	private Category category;
	
	@ManyToOne 
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;
	
	@OneToMany(mappedBy = "post" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();
	
	
}
