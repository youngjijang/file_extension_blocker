package com.example.file_extension_blocker.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.example.file_extension_blocker.controller.dto.AddCustomExtensionRequest;
import com.example.file_extension_blocker.controller.dto.CheckedExtensionsRequest;
import com.example.file_extension_blocker.entity.CustomFileExtension;
import com.example.file_extension_blocker.exception.AlreadyExistCustomExtension;
import com.example.file_extension_blocker.exception.ExistDefaultFileExtension;
import com.example.file_extension_blocker.exception.NotFoundCustomException;
import com.example.file_extension_blocker.repository.CustomExtensionRepository;
import com.example.file_extension_blocker.repository.DefaultExtensionRepository;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class FileExtensionServiceImplTest {

	@Autowired
	private FileExtensionServiceImpl fileExtensionService;

	@Autowired
	private CustomExtensionRepository customExtensionRepository;

	@Autowired
	private DefaultExtensionRepository defaultExtensionRepository;

	@Test
	@DisplayName("findAllDefaultExtension 성공")
	void findAllDefaultExtension() {
		// given // when
		var res = fileExtensionService.findAllDefaultExtension();

		// then
		assertThat(res.size()).isEqualTo(7);
	}

	@Test
	@DisplayName("findAllCustomExtension 성공")
	void findAllCustomExtension() {
		// given
		customExtensionRepository.save(new CustomFileExtension("abc"));
		customExtensionRepository.save(new CustomFileExtension("def"));

		// when
		var res = fileExtensionService.findAllCustomExtension();

		// then
		assertThat(res.size()).isEqualTo(2);
		assertThat(res.get(0).getName()).isEqualTo("def");
		assertThat(res.get(1).getName()).isEqualTo("abc");
	}

	@Test
	@DisplayName("saveCustomExtension 성공")
	void saveCustomExtension() {
		// given
		var req = new AddCustomExtensionRequest("sh");

		// when
		fileExtensionService.saveCustomExtension(req);

		// then
		var res = customExtensionRepository.findAll();
		assertThat(res.isEmpty()).isFalse();
		assertThat(res.get(0).getName()).isEqualTo("sh");
	}

	@Test
	@DisplayName("saveCustomExtension 실패 - custom extension에 존재하는 경우")
	void saveCustomExtension_existsCustom_fail() {
		// given
		customExtensionRepository.save(new CustomFileExtension("abc"));
		var req = new AddCustomExtensionRequest("abc");

		// when // then
		assertThrows(AlreadyExistCustomExtension.class,()->
				fileExtensionService.saveCustomExtension(req)
		);
	}

	@Test
	@DisplayName("saveCustomExtension 실패 - default extension에 존재하는 경우")
	void saveCustomExtension_existsDefault_fail() {
		// given
		var req = new AddCustomExtensionRequest("bat");

		// when // then
		assertThrows(ExistDefaultFileExtension.class,()->
			fileExtensionService.saveCustomExtension(req)
		);
	}

	@Test
	@DisplayName("removeCustomExtension 성공")
	void removeCustomExtension() {
		// given
		var extension = customExtensionRepository.save(new CustomFileExtension("abc"));
		customExtensionRepository.save(new CustomFileExtension("def"));

		// when
		fileExtensionService.removeCustomExtension(extension.getId());

		// then
		var res = customExtensionRepository.findAll();
		assertThat(res.size()).isEqualTo(1);
		assertThat(res.get(0).getName()).isEqualTo("def");
	}

	@Test
	@DisplayName("removeCustomExtension 실패 - 존재하지 않는 id")
	void removeCustomExtension_fail() {
		// given
		customExtensionRepository.save(new CustomFileExtension("abc"));

		// when // then
		assertThrows(NotFoundCustomException.class,() ->
			fileExtensionService.removeCustomExtension(44L)
			);
	}

	@Test
	@DisplayName("changeDefaultExtensionChecked 성공")
	void changeDefaultExtensionChecked() {
		//given
		var req = new CheckedExtensionsRequest(List.of("bat","com","js"));

		// when
		fileExtensionService.changeDefaultExtensionChecked(req);

		// then
		var res = defaultExtensionRepository.findAll();
		assertThat(res.size()).isEqualTo(7);
		assertThat(res.get(0).getName()).isEqualTo("bat");
		assertThat(res.get(0).getChecked()).isTrue();
	}
}