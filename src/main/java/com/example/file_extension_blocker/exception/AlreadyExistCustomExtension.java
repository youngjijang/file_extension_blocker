package com.example.file_extension_blocker.exception;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AlreadyExistCustomExtension extends RuntimeException {

	private final String extensionName;

	public AlreadyExistCustomExtension(String name) {
		this.extensionName = name;
	}

	@Override
	public String getMessage() {
		return String.format("이미 존재하는 커스텀 장자입니다. %s",extensionName);
	}
}
