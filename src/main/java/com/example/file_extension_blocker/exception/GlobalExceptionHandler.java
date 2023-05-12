package com.example.file_extension_blocker.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AlreadyExistCustomExtension.class)
	public ResponseEntity AlreadyExistCustomExtensionHandler(AlreadyExistCustomExtension error) {
		return ResponseEntity.badRequest().body(error.getMessage());
	}

	@ExceptionHandler(ExceedMaximumCountException.class)
	public ResponseEntity ExceedMaximumCountExceptionHandler(ExceedMaximumCountException error) {
		return ResponseEntity.badRequest().body(error.getMessage());
	}

	@ExceptionHandler(ExistDefaultFileExtension.class)
	public ResponseEntity ExistDefaultFileExtensionHandler(ExistDefaultFileExtension error) {
		return ResponseEntity.badRequest().body(error.getMessage());
	}

	@ExceptionHandler(NotFoundCustomException.class)
	public ResponseEntity NotFoundCustomExceptionHandler(NotFoundCustomException error) {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity RuntimeExceptionHandler(RuntimeException error) {
		return ResponseEntity.internalServerError().body(error.getMessage());
	}
}
