package com.example.file_extension_blocker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.file_extension_blocker.entity.CustomFileExtension;

public interface CustomExtensionRepository extends JpaRepository<CustomFileExtension, Long> {

	Optional<CustomFileExtension> findByIdAndName(Long id, String name);
}
