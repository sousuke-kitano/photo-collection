package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.entity.Relation;
import com.example.demo.service.RelationService;

@Controller
@RequestMapping("/relation")
public class RelationController {

	@Autowired
	private RelationService relationService;

	@Autowired
	HttpSession session;

	// フォロー
	@PostMapping(value = "/doFollower/{userId}", params = "follower")
	public String postFollower(@PathVariable("userId") Long userId, Relation relation, Model model) {
		Long loginUserId = (Long)session.getAttribute("userId");
		relation.setUserId(loginUserId);
		relation.setFollowerId(userId);
		relationService.followerUs(relation);
		return "redirect:/user/detail/" + userId;
	}

	// フォロー解除
	@PostMapping(value = "/deleteFollower/{userId}", params = "unfollow")
	public String unFollower(@PathVariable("userId") Long userId, Model model) {
		Long loginUserId = (Long)session.getAttribute("userId");
		relationService.unfollow(loginUserId, userId);
		return "redirect:/user/detail/" + userId;
	}
}
