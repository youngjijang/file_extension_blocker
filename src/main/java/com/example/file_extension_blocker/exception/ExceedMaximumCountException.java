package com.example.file_extension_blocker.exception;

public class ExceedMaximumCountException extends RuntimeException{

	private final Integer maximumCount;

	public ExceedMaximumCountException(Integer maximumCount) {
		this.maximumCount = maximumCount;
	}

	@Override
	public String getMessage() {
		return String.format("저장 가능한 최대량을 초과하였습니다. maximum count : %d",maximumCount);
	}
}
