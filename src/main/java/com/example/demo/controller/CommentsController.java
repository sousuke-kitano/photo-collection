package com.example.demo.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.entity.Comments;
import com.example.demo.model.form.CommentsForm;
import com.example.demo.service.CommentsService;

@Controller
@RequestMapping("/comment")
public class CommentsController {

	@Autowired
	private CommentsService commentsService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	HttpSession session;

	@PostMapping("/post/{photoId}")
	public String postComment(@PathVariable("photoId") Long photoId, CommentsForm commentsForm) {
		Long userId = (Long)session.getAttribute("userId");
		String userName = (String)session.getAttribute("userName");
		commentsForm.setUserId(userId);
		commentsForm.setUserName(userName);
		commentsForm.setCreated(LocalDateTime.now());
		commentsForm.setPhotoId(photoId);

		Comments comments = modelMapper.map(commentsForm, Comments.class);
		commentsService.saveComment(comments);

		return "redirect:/album/detail/{photoId}";
	}
}
