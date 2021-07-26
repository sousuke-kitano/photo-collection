package com.example.demo.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Users;
import com.example.demo.repository.UserMapper;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	HttpSession session;

	@Override
	public void signup(Users user) {
		user.setRole("ROLE_GENERAL");
		user.setPassword(encoder.encode(user.getPassword()));
		userMapper.insertOne(user);
	}

	@Override
	public List<Users> findAll() {
		return userMapper.findMany();
	}

	@Override
	public Users findLoginUser(String email) {
		return userMapper.findLoginUser(email);
	}

	@Override
	public Users getLoginAlbumList(Long userId) {
		return userMapper.findAlbumList(userId);
	}

	@Override
	public Users findUser(Long userId) {
		return userMapper.findOne(userId);
	}


}
