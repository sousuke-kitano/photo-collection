package com.example.demo.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class PostPhoto {

	private Long photoId;

	private String title;

	private byte[] picture;

	private String content;

	private LocalDateTime created;

	private Long userId;

	private String userName;

	private List<Comments> commentList;
}
