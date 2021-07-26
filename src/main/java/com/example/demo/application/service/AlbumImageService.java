package com.example.demo.application.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.model.entity.PostPhoto;


@Service
public class AlbumImageService {


	public List<String> imageList(List<PostPhoto> photoList) {
		List<String> pictureList = new ArrayList<>();
		for (PostPhoto photo : photoList) {
			String image = Base64.getEncoder().encodeToString(photo.getPicture());
			pictureList.add(image);
		}
		return pictureList;
	}

	public Map<Long, String> imageMap(List<PostPhoto> photoList) {
		Map<Long, String> pictureMap = new HashMap<Long, String>();
		for (PostPhoto postPhoto : photoList) {
			String image = Base64.getEncoder().encodeToString(postPhoto.getPicture());
			pictureMap.put(postPhoto.getPhotoId(), image);
		}
		return pictureMap;
	}

}
