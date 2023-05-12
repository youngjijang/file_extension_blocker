package com.example.file_extension_blocker.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class DefaultExtensionRepositoryTest {

	@Autowired
	private DefaultExtensionRepository defaultExtensionRepository;

	@Test
	@DisplayName("existsByName 쿼리 화인")
	void existsByName() {
		var res = defaultExtensionRepository.existsByName("test");
		assertThat(res).isFalse();
	}
}