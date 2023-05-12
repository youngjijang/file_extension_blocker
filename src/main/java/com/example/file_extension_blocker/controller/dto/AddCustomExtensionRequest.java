package com.example.file_extension_blocker.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddCustomExtensionRequest(

	@NotBlank
	@Size(min = 1, max = 20)
	String name
) {
}
