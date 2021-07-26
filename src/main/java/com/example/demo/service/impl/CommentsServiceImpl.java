package com.example.demo.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Comments;
import com.example.demo.repository.CommentsMapper;
import com.example.demo.service.CommentsService;

@Service
public class CommentsServiceImpl implements CommentsService {

	@Autowired
	private CommentsMapper commentsMapper;

	@Autowired
	HttpSession session;

	@Override
	public void saveComment(Comments comments) {
		commentsMapper.insertOne(comments);
	}

}
