package com.example.file_extension_blocker.entity;

import org.springframework.util.Assert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "default_file_extensions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DefaultFileExtension extends BaseTimeColumn{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(unique = true)
	private String name;

	@Column(nullable = false)
	private Boolean checked;

	public DefaultFileExtension(String name) {
		this(name, null);
	}

	public DefaultFileExtension(String name, Boolean checked) {
		Assert.hasText(name,"고정 확장자명이 입력되지않았습니다.");
		this.name = name;
		this.checked = checked != null && checked;
	}

	public void changeChecked(Boolean checked){
		Assert.notNull(checked,"checked 상태가 입력되지않았습니다.");
		this.checked = checked;
	}
}
