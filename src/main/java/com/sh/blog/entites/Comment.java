package com.sh.blog.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comment")
@NoArgsConstructor
@Getter
@Setter

public class Comment 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int comment_id;
	
	@Column(nullable = false)
	private String content;
	
	@ManyToOne
	private Post post; 

}
