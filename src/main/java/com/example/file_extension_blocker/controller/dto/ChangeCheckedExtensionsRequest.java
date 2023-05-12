package com.example.file_extension_blocker.controller.dto;

import java.util.List;

public record ChangeCheckedExtensionsRequest(
	List<DefaultExtensionRequest> defaultExtensions
) {
}
