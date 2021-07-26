package com.example.demo.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.application.service.AlbumImageService;
import com.example.demo.model.entity.PostPhoto;
import com.example.demo.model.form.CommentsForm;
import com.example.demo.model.form.PostPhotoForm;
import com.example.demo.service.PostPhotoService;


@Controller
@RequestMapping("/album")
public class PhotoController {

	@Autowired
	private PostPhotoService photoService;

	@Autowired
	private AlbumImageService imageServece;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	HttpSession session;

	/**
	 * 一覧表示画面
	 */
	@GetMapping("/index")
	public String indexPhoto(Model model) {
		// 全データの取得
		List<PostPhoto> photoList = photoService.findAll();
		// pictureをString型にしてMapに格納
		Map<Long, String> pictureMap = imageServece.imageMap(photoList);
		model.addAttribute("photoList", photoList);
		model.addAttribute("pictureMap", pictureMap);
		return "album/albumList";
	}

	/**
	 * 新規挿入画面
	 */
	@GetMapping("/create")
	public String getCreate(Model model, @ModelAttribute PostPhotoForm postPhotoForm) {
		return "album/albumForm";
	}

	/**
	 * 新規挿入処理
	 * @throws IOException
	 */
	@PostMapping("/insert")
	public String postCreate(@Validated @ModelAttribute PostPhotoForm postPhotoForm,
			BindingResult result, Model model) throws IOException {
		if (result.hasErrors()) {
			return getCreate(model, postPhotoForm);
		}
		PostPhoto photo = postPhotoForm.toEntity();
		photoService.postSave(photo);
		return "redirect:/album/index";
	}

	@GetMapping("/detail/{photoId}")
	public String getDetails(@PathVariable("photoId") Long photoId,
			@ModelAttribute CommentsForm commentsForm, Model model) {
		PostPhoto photo = photoService.findByCommentJoin(photoId);
		String image = Base64.getEncoder().encodeToString(photo.getPicture());
		model.addAttribute("photo", photo);
		model.addAttribute("image", image);
		return "album/albumDetails";
	}

	@GetMapping("/edit/{photoId}")
	public String getEdit(@PathVariable("photoId") Long photoId, PostPhotoForm postPhotoForm, Model model) {
		PostPhoto photo = photoService.findById(photoId);
		String image = Base64.getEncoder().encodeToString(photo.getPicture());
		postPhotoForm = mapper.map(photo, PostPhotoForm.class);

		model.addAttribute("postPhotoForm", postPhotoForm);
		model.addAttribute("image", image);
		model.addAttribute("photo", photo);
		return "album/albumEdit";
	}

	@PostMapping(value = "/edit", params = "update")
	public String postEdit(@Validated PostPhotoForm postPhotoForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			PostPhoto photo = photoService.findById(postPhotoForm.getPhotoId());
			String image = Base64.getEncoder().encodeToString(photo.getPicture());
			model.addAttribute("postPhotoForm", postPhotoForm);
			model.addAttribute("image", image);
			model.addAttribute("photo", photo);

			return "album/albumEdit";
		}
		photoService.updateById(postPhotoForm.getPhotoId(), postPhotoForm.getTitle(), postPhotoForm.getContent());
		return "redirect:/user/myPage";
	}

	/**
	 * idを１件取得して、削除する
	 * @param id
	 * @param model
	 * @return Topページへリダイレクト
	 */
	@PostMapping(value = "/edit", params = "delete")
	public String delete(PostPhotoForm postPhotoForm, Model model) {
		photoService.deleteById(postPhotoForm.getPhotoId());
		return "redirect:/user/myPage";
	}

}
