package com.example.demo.service;

import java.util.List;

import com.example.demo.model.entity.Messages;
import com.example.demo.model.message.FromToUser;

public interface MessagesService {

	public void postSave(Messages messages);

	public List<FromToUser> messageDoUserList(Long loginUserId);

	public List<Messages> messageList(Long loginUserId, Long fromToUserId, Integer limitNum);
}
