package com.example.demo.model.entity;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class Users {

	private Long userId;

	private String email;

	private String userName;

	private String password;

	private Integer age;

	private Integer gender;

	private LocalDate birthday;

	private String role;

	private List<PostPhoto> photoList;
}
