package com.example.file_extension_blocker.entity;

import org.springframework.util.Assert;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "custom_file_extensions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CustomFileExtension extends BaseTimeColumn{

	private final static String ONLY_ALPHA = "^[a-zA-Z]*$";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	public CustomFileExtension(String name) {
		validationName(name);
		this.name = changeToLowerCase(name);
	}

	private void validationName(String name) {
		Assert.hasText(name,"파일 확장자명이 입력되지않았습니다.");
		Assert.isTrue(name.matches(ONLY_ALPHA), "파일 확장자명는 영문만 입력 가능합니다.");
		Assert.isTrue(name.length()>=1 && name.length() <= 20, "파일 확장자명은 최소 1자, 최대 20자까지 입력 가능합니다.");
	}

	private String changeToLowerCase(String name) {
		return name.toLowerCase();
	}
}
