package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Messages;
import com.example.demo.model.message.FromToUser;
import com.example.demo.repository.MessagesMapper;
import com.example.demo.service.MessagesService;

@Service
public class MessagesServiceImpl implements MessagesService {

	@Autowired
	private MessagesMapper messagesMapper;

	@Override
	public void postSave(Messages messages) {
		messagesMapper.insertOne(messages);
	}

	@Override
	public List<FromToUser> messageDoUserList(Long loginUserId) {
		return messagesMapper.messageDoUserList(loginUserId);
	}

	@Override
	public List<Messages> messageList(Long loginUserId, Long fromToUserId, Integer limitNum) {
		return messagesMapper.messageList(loginUserId, fromToUserId, limitNum);
	}

}
