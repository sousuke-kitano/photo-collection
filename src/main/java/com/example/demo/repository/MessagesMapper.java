package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.model.entity.Messages;
import com.example.demo.model.message.FromToUser;

@Mapper
public interface MessagesMapper {

	// メッセージを新規投稿
	public int insertOne(Messages messages);

	// ログインユーザーとやりとりしているユーザーList
	public List<FromToUser> messageDoUserList(@Param("loginUserId") Long loginUserId);

	// ログインユーザーと特定のユーザーとのやりとり
	public List<Messages> messageList(@Param("loginUserId") Long loginUserId,
									@Param("fromToUserId") Long fromToUserId,
									@Param("limitNum") Integer limitNum);
}
