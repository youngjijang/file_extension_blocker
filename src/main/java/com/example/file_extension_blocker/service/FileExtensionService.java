package com.example.file_extension_blocker.service;

import java.util.List;

import com.example.file_extension_blocker.controller.dto.AddCustomExtensionRequest;
import com.example.file_extension_blocker.controller.dto.ChangeCheckedExtensionsRequest;
import com.example.file_extension_blocker.entity.CustomFileExtension;
import com.example.file_extension_blocker.entity.DefaultFileExtension;

public interface FileExtensionService {

	List<DefaultFileExtension> findAllDefaultExtension();

	List<CustomFileExtension> findAllCustomExtension();

	void saveCustomExtension(AddCustomExtensionRequest request);

	void removeCustomExtension(Long customExtensionId);

	void changeDefaultExtensionChecked(ChangeCheckedExtensionsRequest request);
}
