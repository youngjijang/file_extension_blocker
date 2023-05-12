package com.example.file_extension_blocker.service;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.file_extension_blocker.controller.dto.CheckedDefaultExtensionsRequest;
import com.example.file_extension_blocker.entity.CustomFileExtension;
import com.example.file_extension_blocker.entity.DefaultFileExtension;
import com.example.file_extension_blocker.repository.CustomExtensionRepository;
import com.example.file_extension_blocker.repository.DefaultExtensionRepository;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@Transactional
@RequiredArgsConstructor
public class FileExtensionServiceImpl implements FileExtensionService {

	private final DefaultExtensionRepository defaultExtensionRepository;

	private final CustomExtensionRepository customExtensionRepository;

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
	public void saveCustomExtension(String fileExtensionName) {
		// default에 동일한 이름이 있는지
		// custom에 동일한 이름이 있는지

		// 없으면 저장
		CustomFileExtension customFileExtension = new CustomFileExtension(fileExtensionName);
		customExtensionRepository.save(customFileExtension);
	}

	@Override
	public void removeCustomExtension(String fileExtensionName) {
		// 있는지
		CustomFileExtension customFileExtension = customExtensionRepository.findByIdAndName(1L, fileExtensionName)
			.orElseThrow();
		// 삭제
		customExtensionRepository.delete(customFileExtension);
	}

	@Override
	public void changeDefaultExtensionChecked(CheckedDefaultExtensionsRequest request) {
		List<DefaultFileExtension> defaultFileExtensions = defaultExtensionRepository.findAll();
		for (int i=0;i<defaultFileExtensions.size();i++){
			int finalI = i;
			var r = request.defaultExtensions().stream().filter(extension -> Objects.equals(extension.name(),defaultFileExtensions.get(
				finalI).getName())).findFirst();
			r.ifPresent(defaultExtensionRequest -> defaultFileExtensions.get(finalI)
				.changeChecked(defaultExtensionRequest.checked()));
		}
	}
}
