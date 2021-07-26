package com.example.demo.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.PostPhoto;
import com.example.demo.model.entity.Users;
import com.example.demo.repository.PostPhotoMapper;
import com.example.demo.service.PostPhotoService;
import com.example.demo.service.UserService;

@Service
public class PostPhotoServiceImpl implements PostPhotoService {

	@Autowired
	private PostPhotoMapper photoMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private HttpSession session;

	@Override
	public void postSave(PostPhoto photo) {
		String email = (String)session.getAttribute("email");
		Long userId = (Long)session.getAttribute("userId");
		Users user = userService.findLoginUser(email);
		String userName = user.getUserName();
		photo.setUserId(userId);
		photo.setUserName(userName);
		photoMapper.insertOne(photo);
	}

	@Override
	public List<PostPhoto> findAll() {
		return photoMapper.selectAll();
	}

	@Override
	public void deleteById(Long photoId) {
		photoMapper.deleteOne(photoId);
	}

	@Override
	public PostPhoto findById(Long photoId) {
		return photoMapper.selectOne(photoId);
	}

	@Override
	public void updateById(Long photoId, String title, String content) {
		photoMapper.updateOne(photoId, title, content);
	}

	@Override
	public PostPhoto findByCommentJoin(Long photoId) {
		return photoMapper.findCommentList(photoId);
	}

}
