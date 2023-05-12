package com.example.file_extension_blocker.controller.dto;

import java.util.List;

import lombok.Getter;

public record CheckedDefaultExtensionsRequest(
	List<DefaultExtensionRequest> defaultExtensions
) {
}
