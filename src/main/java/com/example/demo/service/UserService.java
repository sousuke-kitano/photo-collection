package com.example.demo.service;

import java.util.List;

import com.example.demo.model.entity.Users;


public interface UserService {

	// ユーザー登録
	public void signup(Users user);

	// ユーザー全件取得
	public List<Users> findAll();

	// ログインユーザー取得
	public Users findLoginUser(String email);

	// ログインユーザー情報をPostPhotoテーブルと結合し、一覧表示する
	public Users getLoginAlbumList(Long userId);

	// ユーザー１件取得
	public Users findUser(Long userId);
}
