package com.example.file_extension_blocker.entity;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class CustomFileExtensionTest {

	@Test
	@DisplayName("create 성공")
	void create_success() {
		assertDoesNotThrow(() -> {
			new CustomFileExtension("test");
		});
	}

	@Test
	@DisplayName("create 성공 - 대문자 입력")
	void create_lower_success() {
		assertDoesNotThrow(() -> {
			var fileExtension =  new CustomFileExtension("TEST");
			assertThat(fileExtension.getName()).isEqualTo("test");
		});
	}


	@DisplayName("create 실패 - 확장자 이름이 유효하지않을 경우")
	@ParameterizedTest
	@ValueSource(strings = {" ", "", "한글",".문자.","t e s t","aaaaaaaaaaaaaaaaaaaaaa"})
	@NullSource
	void createDefaultFileExtension_fail(String name) {
		assertThrows(IllegalArgumentException.class,
			() -> new CustomFileExtension(name)
		);
	}
}