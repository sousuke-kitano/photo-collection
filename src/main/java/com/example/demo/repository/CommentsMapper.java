package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.entity.Comments;

@Mapper
public interface CommentsMapper {

	public int insertOne(Comments comments);

}
