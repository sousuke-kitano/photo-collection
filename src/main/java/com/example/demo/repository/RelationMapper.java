package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.model.entity.Relation;

@Mapper
public interface RelationMapper {

	// ユーザーをフォローする
	public int insertOne(Relation relation);

	// フォロー解除
	public int deleteOne(@Param("loginUserId") Long loginUserId,
						@Param("followerUserId") Long followerUserId);

	// フォローしているUser
	public Relation findFollowList(@Param("loginUserId") Long loginUserId);

	// フォローされているUser
	public Relation findFollowerList(Long loginUserId);

}
