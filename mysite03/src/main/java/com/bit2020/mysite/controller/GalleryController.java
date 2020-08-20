package com.bit2020.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bit2020.mysite.security.Auth;
import com.bit2020.mysite.service.FileUploadService;
import com.bit2020.mysite.service.GalleryService;
import com.bit2020.mysite.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	@Autowired
	private GalleryService galleryService;
	
	@Autowired
	private FileUploadService fileuploadService;
	
	@RequestMapping("")
	public String index(Model model) {
		model.addAttribute("list",galleryService.getGalleryList());
		return "gallery/index";
	}
	
	@Auth(value=Auth.Role.ADMIN)
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no")Long no) {
		galleryService.deleteImageInformation(no);
		return "redirect:/gallery";
	}
	
	@Auth(value=Auth.Role.ADMIN)
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String upload(
			@ModelAttribute GalleryVo galleryVo,
			@RequestParam("file") MultipartFile multipartFile) {
			
		String imageUrl = fileuploadService.restore(multipartFile);
		galleryVo.setImageUrl(imageUrl);
		
		galleryService.saveImageInformation(galleryVo);
		
		return "redirect:/gallery";
	}
}
