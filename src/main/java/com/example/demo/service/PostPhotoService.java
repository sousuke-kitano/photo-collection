package com.example.demo.service;

import java.util.List;

import com.example.demo.model.entity.PostPhoto;

public interface PostPhotoService {

	public void postSave(PostPhoto photo);

	public List<PostPhoto> findAll();

	public PostPhoto findById(Long photoId);

	public void updateById(Long photoId, String title, String content);

	public void deleteById(Long photoId);

	public PostPhoto findByCommentJoin(Long photoId);
}
