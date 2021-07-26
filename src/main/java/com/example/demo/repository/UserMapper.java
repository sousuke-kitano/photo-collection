package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.entity.Users;


@Mapper
public interface UserMapper {

	// ユーザー登録
	public int insertOne(Users user);

	// ユーザー全件取得
	public List<Users> findMany();

	// ログインユーザー取得
	public Users findLoginUser(String email);

	// PostPhotoテーブル結合後、一覧表示
	public Users findAlbumList(Long userId);

	// ユーザー１件取得
	public Users findOne(Long userId);
}
