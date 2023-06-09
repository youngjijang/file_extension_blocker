package com.example.file_extension_blocker.service;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.file_extension_blocker.controller.dto.AddCustomExtensionRequest;
import com.example.file_extension_blocker.controller.dto.CheckedExtensionsRequest;
import com.example.file_extension_blocker.entity.CustomFileExtension;
import com.example.file_extension_blocker.entity.DefaultFileExtension;
import com.example.file_extension_blocker.exception.AlreadyExistCustomExtension;
import com.example.file_extension_blocker.exception.ExceedMaximumCountException;
import com.example.file_extension_blocker.exception.ExistDefaultFileExtension;
import com.example.file_extension_blocker.exception.NotFoundCustomException;
import com.example.file_extension_blocker.repository.CustomExtensionRepository;
import com.example.file_extension_blocker.repository.DefaultExtensionRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FileExtensionServiceImpl implements FileExtensionService {

	private final DefaultExtensionRepository defaultExtensionRepository;

	private final CustomExtensionRepository customExtensionRepository;

	private final static Integer SAVE_MAX_COUNT = 200;

	@Override
	@Transactional(readOnly = true)
	public List<DefaultFileExtension> findAllDefaultExtension() {
		return defaultExtensionRepository.findAll(Sort.by("name"));
	}

	@Override
	@Transactional(readOnly = true)
	public List<CustomFileExtension> findAllCustomExtension() {
		return customExtensionRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	@Override
	public void saveCustomExtension(AddCustomExtensionRequest request) {
		if (customExtensionRepository.countBy() >= SAVE_MAX_COUNT){
			throw new ExceedMaximumCountException(SAVE_MAX_COUNT);
		}
		CustomFileExtension customFileExtension = new CustomFileExtension(request.name());
		if (defaultExtensionRepository.existsByName(customFileExtension.getName())){
			throw new ExistDefaultFileExtension(customFileExtension.getName());
		}
		if (customExtensionRepository.existsByName(customFileExtension.getName())){
			throw new AlreadyExistCustomExtension(customFileExtension.getName());
		}
		customExtensionRepository.save(customFileExtension);
	}

	@Override
	public void removeCustomExtension(Long extensionId) {
		CustomFileExtension customFileExtension = customExtensionRepository.findById(extensionId)
			.orElseThrow(NotFoundCustomException::new);
		customExtensionRepository.delete(customFileExtension);
	}

	@Override
	public void changeDefaultExtensionChecked(CheckedExtensionsRequest request) {
		List<DefaultFileExtension> defaultFileExtensions = defaultExtensionRepository.findAll();
		List<String> checkedExtension = request.name();
		defaultFileExtensions
			.forEach(extension ->{
				extension.changeChecked(checkedExtension.contains(extension.getName()));
			});
	}
}
