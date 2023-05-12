package com.example.file_extension_blocker.entity;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class DefaultFileExtensionTest {

	@Test
	@DisplayName("create 성공")
	void create_success() {
		assertDoesNotThrow(() -> {
			new DefaultFileExtension("test", true);
		});
	}

	@Test
	@DisplayName("create 성공 - 이름만으로 생성하는 경우")
	void create_onlyName_success() {
		assertDoesNotThrow(() -> {
			var defaultFileExtension = new DefaultFileExtension("test");
			assertThat(defaultFileExtension.getChecked()).isFalse();
		});
	}

	@DisplayName("create 실패 - 확장자 이름이 유효하지않을 경우")
	@ParameterizedTest
	@NullSource
	void create_fail(String name) {
		assertThrows(IllegalArgumentException.class,
			() -> new DefaultFileExtension(name)
		);
	}

	@Test
	@DisplayName("changeChecked 성공")
	void changeChecked() {
		var defaultFileExtension = new DefaultFileExtension("test");
		assertDoesNotThrow(() -> {
			defaultFileExtension.changeChecked(true);
			assertThat(defaultFileExtension.getChecked()).isTrue();
		});
	}

	@DisplayName("changeChecked 실패 - checked 값이 null 일 경우")
	@ParameterizedTest
	@NullSource
	void changeChecked_fail(Boolean checked) {
		var defaultFileExtension = new DefaultFileExtension("test");
		assertThrows(IllegalArgumentException.class,
			() -> defaultFileExtension.changeChecked(checked)
		);
	}
}