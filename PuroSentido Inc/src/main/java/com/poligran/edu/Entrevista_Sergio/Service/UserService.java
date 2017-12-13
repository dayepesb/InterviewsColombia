package com.poligran.edu.Entrevista_Sergio.Service;

import java.util.List;

import com.poligran.edu.Entrevista_Sergio.Entity.UserEntity;

public interface UserService {
	public UserEntity saveUser(String address, String color, String name, String lastname, String phone,
			String zipcode);
	public void removeUser(Integer id);
	public List<UserEntity> allUsers();
}
