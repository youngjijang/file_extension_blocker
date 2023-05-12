package com.example.file_extension_blocker.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.example.file_extension_blocker.controller.dto.AddCustomExtensionRequest;
import com.example.file_extension_blocker.controller.dto.CheckedExtensionsRequest;
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

	@PostMapping ("/default")
	public RedirectView changeDefaultExtensionChecked(@ModelAttribute CheckedExtensionsRequest requests) {
		fileExtensionService.changeDefaultExtensionChecked(requests);
		return new RedirectView("/file-extensions");
	}

	@PostMapping("/custom")
	public RedirectView addCustomExtension(@ModelAttribute @Valid AddCustomExtensionRequest request) {
		fileExtensionService.saveCustomExtension(request);
		return new RedirectView("/file-extensions");
	}

	@PostMapping("/custom/{extensionId}")
	public RedirectView removeCustomExtension(@PathVariable Long extensionId) {
		fileExtensionService.removeCustomExtension(extensionId);
		return new RedirectView("/file-extensions");
	}
}
