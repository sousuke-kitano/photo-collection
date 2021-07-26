package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.model.entity.PostPhoto;

@Mapper
public interface PostPhotoMapper {

	// 新規登録
	public int insertOne(PostPhoto photo);

	// 全件取得
	public List<PostPhoto> selectAll();

	// 一件取得
	public PostPhoto selectOne(Long photoId);

	// １件更新
	public void updateOne(@Param("photoId") Long photoId,
			@Param("title") String title,
			@Param("content") String content);

	// 一件削除
	public void deleteOne(@Param("photoId") Long photoId);

	// Commentsテーブル結合、一覧表示
	public PostPhoto findCommentList(@Param("photoId") Long photoId);

}
