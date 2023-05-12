package com.example.file_extension_blocker.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.file_extension_blocker.controller.dto.AddCustomExtensionRequest;
import com.example.file_extension_blocker.controller.dto.ChangeCheckedExtensionsRequest;
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
		List<DefaultFileExtension> defaultExtension = fileExtensionService.findAllDefaultExtension();
		List<CustomFileExtension> customExtension = fileExtensionService.findAllCustomExtension();
		model.addAttribute("defaultExtensions",defaultExtension);
		model.addAttribute("customExtensions",customExtension);
		model.addAttribute("customExtensionsCount",customExtension.size());
		return "file-extension-blocker";
	}

	@PutMapping ("/default")
	public String changeDefaultExtensionChecked(@ModelAttribute ChangeCheckedExtensionsRequest requests) {
		fileExtensionService.changeDefaultExtensionChecked(requests);
		return "file-extension-blocker";
	}

	@PostMapping("/custom")
	public String addCustomExtension(@ModelAttribute @Valid AddCustomExtensionRequest request) {
		fileExtensionService.saveCustomExtension(request);
		return "file-extension-blocker";
	}

	@DeleteMapping("/custom/{customExtensionId}")
	public String removeCustomExtension(@PathVariable Long customExtensionId) {
		fileExtensionService.removeCustomExtension(customExtensionId);
		return "file-extension-blocker";
	}
}
