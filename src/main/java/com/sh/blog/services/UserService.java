package com.sh.blog.services;

import java.util.List;

import com.sh.blog.payloads.UserDTO;

public interface UserService 
{
//	@Cacheable()
	UserDTO createUser(UserDTO user);
//	@Transactional
	UserDTO updateUser(UserDTO user , Integer id);
	UserDTO getUserById(Integer id);
	List<UserDTO> getAllUsers();
	void deleteUser(Integer id);
}
