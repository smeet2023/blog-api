package com.sh.blog.services.implmentation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.blog.entites.User;
import com.sh.blog.exceptions.ResourceNotFoundException;
import com.sh.blog.payloads.UserDTO;
import com.sh.blog.repositories.UserRepository;
import com.sh.blog.services.UserService;

@Service
public class UserServiceImplementation implements UserService 
{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	// this is a interface without implementation class, hence spring boot container bootstrap repo interfaces implementation classes automatically made dynamic
	@Override
	public UserDTO createUser(UserDTO userDto) 
	{
		User user2 = this.dtoToUser(userDto); 
		User savedUser = this.userRepository.save(user2);
		return this.userToDTO(savedUser); 
	}

	@Override
	public UserDTO updateUser(UserDTO userDto, Integer id) 
	{
		User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User" , "Id" , id)); //             supplier there is nothing 
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		User updatedUser = this.userRepository.save(user);
		UserDTO userDto1 = this.userToDTO(updatedUser);
		
		return userDto1;
	}

	@Override
	public UserDTO getUserById(Integer id) 
	{
		User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User" , "Id" , id));
		
		return this.userToDTO(user);
	}

	@Override
	public List<UserDTO> getAllUsers() 
	{
		List<User> users = this.userRepository.findAll();
		List<UserDTO> userdtos = users.stream().map(e->this.userToDTO(e)).collect(Collectors.toList());
		return userdtos;
	}

	@Override
	public void deleteUser(Integer id) 
	{
		User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User" , "Id" , id));
		
		this.userRepository.delete(user);
	}
	
	
	
	
	
	private User dtoToUser(UserDTO userDto)
	{
		User user = this.modelMapper.map(userDto, User.class); 
//				new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		
		return user;
	}
	public UserDTO userToDTO(User user)
	{
		UserDTO userDto = this.modelMapper.map(user, UserDTO.class); 
//				new UserDTO();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		
		return userDto;
	}

}
