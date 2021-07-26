package com.example.demo.service;

import com.example.demo.model.entity.Relation;

public interface RelationService {

	public void followerUs(Relation relation);

	public void unfollow(Long loginUserId, Long followerUserId);

	public Relation followUsers(Long loginUserId);

	public Relation followerUsers(Long loginUserId);
}
