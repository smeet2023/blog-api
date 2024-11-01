package com.sh.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.blog.payloads.ApiResponse;
import com.sh.blog.payloads.UserDTO;
import com.sh.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController 
{
	@Autowired
	private UserService userServices;
	
	
	@PostMapping("/create/")   // the fact in using USERDTO is to not expose User Entity altogether , suppose we dont want to expose password , we can do that in DTO 
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO)
	{
		UserDTO create = this.userServices.createUser(userDTO);
		return new ResponseEntity<>(create , HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}") // path uri variable
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO , @PathVariable Integer id)
	{
		UserDTO update = this.userServices.updateUser(userDTO, id);
//		return ResponseEntity.ok("uploaded");
		return new ResponseEntity<>(update , HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id)
	{
		this.userServices.deleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted" , true), HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<UserDTO>> getAllUsers()
	{
		return ResponseEntity.ok(this.userServices.getAllUsers());
	}
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id)
	{
		return ResponseEntity.ok(this.userServices.getUserById(id));
	}
	
	
}
