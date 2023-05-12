package com.example.file_extension_blocker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomFileExtension {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	public CustomFileExtension(String name) {
		validationName(name);
		this.name = changeToLowerCase(name);
	}

	private void validationName(String name) {
		// 공백있으면 안됨
		// only alphabat
		// 글자수
	}

	private String changeToLowerCase(String name) {
		return null;
	}
}
