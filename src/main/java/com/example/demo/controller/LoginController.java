package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.entity.Users;
import com.example.demo.model.form.UsersForm;
import com.example.demo.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * @return ログイン画面へ
	 */
	@GetMapping("/login")
	public String login() {
		return "login/login";
	}

	/**
	 * @param model
	 * @return 新規挿入画面へ
	 */
	@GetMapping("/signup")
	public String getSignup(Model model, @ModelAttribute UsersForm usersForm) {
		return "login/signup";
	}

	/**
	 * @param userForm
	 * @param model
	 * @return 新規登録処理成功後、ログイン画面へ遷移する。
	 */
	@PostMapping("/signup")
	public String postSignup(@Validated @ModelAttribute UsersForm usersForm,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return getSignup(model, usersForm);
		}
		Users user = modelMapper.map(usersForm, Users.class);

		// ユーザー登録
		userService.signup(user);

		return "redirect:/login";
	}
}
