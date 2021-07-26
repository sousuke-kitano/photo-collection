package com.example.demo.model.entity;

import java.util.List;

import lombok.Data;

@Data
public class Relation {

	private Long userId;

	private Long followerId;

	private List<Users> followerList;
}
