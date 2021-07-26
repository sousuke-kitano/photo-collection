package com.example.demo.model.form;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UsersForm {

	private Long userId;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Length(min = 2, max = 20)
	private String userName;

	@NotBlank
	@Length(min = 4, max = 100)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String password;

	@NotNull
	@Min(value = 18)
	@Max(value = 100)
	private Integer age;

	@NotNull
	private Integer gender;

	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate birthday;

}
