package com.example.file_extension_blocker.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CustomExtensionRepositoryTest {

	@Autowired
	private CustomExtensionRepository customExtensionRepository;

	@Test
	@DisplayName("countAll 쿼리 확인")
	void countAll() {
		customExtensionRepository.countBy();
	}

	@Test
	@DisplayName("existsByName 쿼리 확인")
	void existsByName() {
		customExtensionRepository.existsByName("test");
	}

}