package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.application.service.AlbumImageService;
import com.example.demo.model.entity.Relation;
import com.example.demo.model.entity.Users;
import com.example.demo.service.RelationService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RelationService relationService;

	@Autowired
	HttpSession session;

	@Autowired
	private AlbumImageService imageService;


	@GetMapping("/list")
	public String getUserList(Model model) {
		List<Users> userList = userService.findAll();
		model.addAttribute("userList", userList);
		return "user/userList";
	}

	@GetMapping("/myPage")
	public String getMyPage(Model model) {
		Long userId = (Long)session.getAttribute("userId");
		Users user = userService.getLoginAlbumList(userId);

		if (!user.getPhotoList().isEmpty()) {
			List<String> image = imageService.imageList(user.getPhotoList());
			model.addAttribute("pictureList", image);
		}
		model.addAttribute("selfUser", user);
		// フォローしているUserList
		Relation followUsers = relationService.followUsers(userId);
		model.addAttribute("followList", followUsers);
		// フォローされているUserList
		Relation followerUsers = relationService.followerUsers(userId);
		model.addAttribute("followerList", followerUsers);
		return "user/myPage";
	}

	@GetMapping("/detail/{userId}")
	public String getUserPage(@PathVariable("userId") Long userId, Model model) {
		Users user = userService.getLoginAlbumList(userId);
		List<String> image = imageService.imageList(user.getPhotoList());
		model.addAttribute("selfUser", user);
		model.addAttribute("pictureList", image);
		// フォローしているUserList
		Relation followUsers = relationService.followUsers(userId);
		model.addAttribute("followList", followUsers);
		// フォローされているUserList
		Relation followerUsers = relationService.followerUsers(userId);
		model.addAttribute("followerList", followerUsers);
		// フォロワーリストをArrayListに格納
		List<Long> userIdList = new ArrayList<>();
		if (followerUsers != null) {
			for (Users users: followerUsers.getFollowerList()) {
				userIdList.add(users.getUserId());
			}
		}
		model.addAttribute("userIdList", userIdList);

		return "user/userDetail";
	}

}
