package com.example.file_extension_blocker.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.file_extension_blocker.controller.dto.AddCustomExtensionRequest;
import com.example.file_extension_blocker.controller.dto.DefaultExtensionRequest;
import com.example.file_extension_blocker.entity.CustomFileExtension;
import com.example.file_extension_blocker.entity.DefaultFileExtension;
import com.example.file_extension_blocker.service.FileExtensionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/file-extensions")
@RequiredArgsConstructor
@Slf4j
public class FileExtensionController {

	private final FileExtensionService fileExtensionService;

	@GetMapping()
	public String fileExtensionPage(Model model){
		List<DefaultFileExtension> checkedDefaultExtension = fileExtensionService.findAllDefaultExtension();
		List<CustomFileExtension> customExtension = fileExtensionService.findAllCustomExtension();
		model.addAttribute("checkedDefaultExtension",checkedDefaultExtension);
		model.addAttribute("customExtension",customExtension);
		return "file-extension-blocker";
	}

	@PutMapping("/default")
	public String changeDefaultExtension(@ModelAttribute DefaultExtensionRequest request) {
		return null;
	}

	@PostMapping("/custom")
	public String addCustomExtension(@ModelAttribute @Valid AddCustomExtensionRequest request) {
		fileExtensionService.saveCustomExtension(request.name());
		return "file-extension-blocker";
	}

	@DeleteMapping("/custom")
	public String removeCustomExtension(@ModelAttribute AddCustomExtensionRequest request) {
		fileExtensionService.removeCustomExtension(request.name());
		return "file-extension-blocker";
	}
}
