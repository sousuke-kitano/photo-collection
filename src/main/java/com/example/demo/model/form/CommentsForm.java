package com.example.demo.model.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class CommentsForm {

	private Long commentId;

	@NotBlank
	@Length(max = 300)
	private String comment;

	private LocalDateTime created;

	private Long userId;

	private String userName;

	private Long photoId;
}
