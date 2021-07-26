package com.example.demo.model.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class MessagesForm {

	private Long messageId;

	@NotBlank
	@Length(max = 1000)
	private String message;

	private LocalDateTime postDateTime;

	private Long toUserId;

	private String toUserName;

	private Long fromUserId;

	private String fromUserName;
}
