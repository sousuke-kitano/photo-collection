package com.example.demo.model.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Comments {

	private Long commentId;

	private String comment;

	private LocalDateTime created;

	private Long userId;

	private String userName;

	private Long photoId;

}
