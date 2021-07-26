package com.example.demo.model.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Messages {

	private Long messageId;

	private String message;

	private LocalDateTime postDateTime;

	private Long toUserId;

	private String toUserName;

	private Long fromUserId;

	private String fromUserName;
}
