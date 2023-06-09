package com.example.file_extension_blocker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.file_extension_blocker.entity.DefaultFileExtension;

public interface DefaultExtensionRepository extends JpaRepository<DefaultFileExtension, Long> {

	Boolean existsByName(String name);
}
