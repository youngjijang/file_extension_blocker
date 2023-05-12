package com.example.file_extension_blocker.exception;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ExistDefaultFileExtension extends RuntimeException {

	private final String extensionName;

	public ExistDefaultFileExtension(String name) {
		this.extensionName = name;
	}

	@Override
	public String getMessage() {
		return String.format("고정 파일 확장자에 존재하는 확장자입니다. %s",extensionName);
	}
}
