package com.example.demo.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Users;
import com.example.demo.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Autowired
	HttpSession session;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user = userService.findLoginUser(username);

		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		session.setAttribute("email", username);
		session.setAttribute("userId", user.getUserId());
		session.setAttribute("userName", user.getUserName());

		// 権限List作成
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
		List<GrantedAuthority> authoritys = new ArrayList<>();
		authoritys.add(authority);

		// UserDetails生成
		UserDetails userDetails = (UserDetails)new User(user.getEmail(), user.getPassword(), authoritys);
		return userDetails;
	}

}
