package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.entity.Messages;
import com.example.demo.model.entity.Users;
import com.example.demo.model.form.MessagesForm;
import com.example.demo.model.message.FromToUser;
import com.example.demo.service.MessagesService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/message")
public class MessagesController {

	@Autowired
	private UserService userService;

	@Autowired
	private MessagesService messagesService;

	@Autowired
	HttpSession session;

	@Autowired
	private ModelMapper mapper;

	/**
	 * @return
	 * メッセージをしているユーザーを一覧表示する
	 */
	@GetMapping("/list")
	public String getMessageList(Model model) {
		Long loginUserId = (Long)session.getAttribute("userId");
		List<FromToUser> fromToUserList = messagesService.messageDoUserList(loginUserId);
		List<List<Messages>> messageList = new ArrayList<List<Messages>>();
		for (FromToUser list : fromToUserList) {
			List<Messages> messageListSub = messagesService.messageList(loginUserId, list.getFromToUserId(), 1);
			messageList.add(messageListSub);
		}

		model.addAttribute("messageList", messageList);
		model.addAttribute("fromToUserList", fromToUserList);

		return "message/index";
	}

	/**
	 * @return
	 * 相手ユーザーとのメッセージの履歴を表示する
	 */
	@GetMapping("/detail/{fromToUserId}")
	public String getMessageDetail(@PathVariable("fromToUserId") Long fromToUserId,
			@ModelAttribute MessagesForm messagesForm, Model model) {
		Long loginUserId = (Long)session.getAttribute("userId");
		List<Messages> messageList = messagesService.messageList(loginUserId, fromToUserId, 100);
		Users user = userService.findUser(fromToUserId);
		model.addAttribute("toUserName", user.getUserName());
		model.addAttribute("messageList", messageList);
		model.addAttribute("toUserId", fromToUserId);
		return "message/detail";
	}

	/**
	 * @return
	 * 新規メッセージーを投稿する
	 */
	@PostMapping("/insert/{toUserId}")
	public String postMessage(@PathVariable("toUserId") Long toUserId,
			@Validated @ModelAttribute MessagesForm messagesForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return getMessageDetail(toUserId, messagesForm, model);
		}
		Users toUser = userService.findUser(toUserId);
		Long fromUserId = (Long)session.getAttribute("userId");
		String fromUserName = (String)session.getAttribute("userName");
		messagesForm.setPostDateTime(LocalDateTime.now());
		messagesForm.setToUserId(toUserId);
		messagesForm.setToUserName(toUser.getUserName());
		messagesForm.setFromUserId(fromUserId);
		messagesForm.setFromUserName(fromUserName);
		Messages messages = mapper.map(messagesForm, Messages.class);
		messagesService.postSave(messages);
		return "redirect:/message/detail/{toUserId}";
	}
}
