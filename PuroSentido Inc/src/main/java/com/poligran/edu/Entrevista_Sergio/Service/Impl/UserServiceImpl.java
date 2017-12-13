package com.poligran.edu.Entrevista_Sergio.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poligran.edu.Entrevista_Sergio.Entity.UserEntity;
import com.poligran.edu.Entrevista_Sergio.Repository.UserRepository;
import com.poligran.edu.Entrevista_Sergio.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository ur;

	@Override
	public UserEntity saveUser(String address, String color, String name, String lastname, String phone,
			String zipcode) {
		UserEntity ue = new UserEntity();
		ue.setAddress(address);
		ue.setColor(color);
		ue.setName(name);
		ue.setLastname(lastname);
		ue.setPhone(phone);
		ue.setZipcode(zipcode);
		return ur.save(ue);
	}

	@Override
	public void removeUser(Integer id) {
		ur.delete(id);
	}

	@Override
	public List<UserEntity> allUsers() {
		List<UserEntity> list = ur.findAll();
		return list;
	}
}
