package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Relation;
import com.example.demo.repository.RelationMapper;
import com.example.demo.service.RelationService;

@Service
public class RelationServiceImpl implements RelationService {

	@Autowired
	private RelationMapper relationMapper;

	@Override
	public void followerUs(Relation relation) {
		relationMapper.insertOne(relation);
	}

	@Override
	public Relation followUsers(Long loginUserId) {
		return relationMapper.findFollowList(loginUserId);
	}

	@Override
	public Relation followerUsers(Long loginUserId) {
		return relationMapper.findFollowerList(loginUserId);
	}

	@Override
	public void unfollow(Long loginUserId, Long followerUserId) {
		relationMapper.deleteOne(loginUserId, followerUserId);
	}

}
