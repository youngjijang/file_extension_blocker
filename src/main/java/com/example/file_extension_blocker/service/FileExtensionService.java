package com.example.file_extension_blocker.service;

import java.util.List;

import com.example.file_extension_blocker.entity.CustomFileExtension;
import com.example.file_extension_blocker.entity.DefaultFileExtension;

public interface FileExtensionService {

	List<DefaultFileExtension> findAllDefaultExtension();

	List<CustomFileExtension> findAllCustomExtension();

	void saveCustomExtension(String fileExtension);

	void removeCustomExtension(String fileExtension);
}
