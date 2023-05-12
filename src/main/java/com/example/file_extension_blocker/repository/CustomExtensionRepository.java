package com.example.file_extension_blocker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.file_extension_blocker.entity.CustomFileExtension;

public interface CustomExtensionRepository extends JpaRepository<CustomFileExtension, Long> {

	Integer countBy();

	Boolean existsByName(String name);
}
