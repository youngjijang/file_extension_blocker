package com.example.file_extension_blocker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DefaultFileExtension {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private Boolean checked;

	public DefaultFileExtension(String name, Boolean checked) {
		this.name = name;
		this.checked = checked;
	}

	public void changeChecked(Boolean checked){
		this.checked = checked;
	}
}
