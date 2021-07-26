package com.example.demo.model.form;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.entity.PostPhoto;

import lombok.Data;

@Data
public class PostPhotoForm {

	private Long photoId;

	@NotBlank
	@Length(max = 100)
	private String title;

	private MultipartFile picture;

	@NotBlank
	@Length(max = 700)
	private String content;

	private LocalDateTime created;



	public PostPhoto toEntity() throws IOException {
		PostPhoto photo = new PostPhoto();
		photo.setPhotoId(photoId);
		photo.setTitle(title);
		photo.setPicture(picture.getBytes());
		photo.setContent(content);
		photo.setCreated(LocalDateTime.now());

		return photo;
	}
}
