package com.bit2020.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bit2020.mysite.security.Auth;
import com.bit2020.mysite.security.Auth.Role;
import com.bit2020.mysite.service.FileUploadService;
import com.bit2020.mysite.service.SiteService;
import com.bit2020.mysite.vo.SiteVo;

@Auth(Role.ADMIN)
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private FileUploadService fileuploadService;
	
	@Autowired
	private SiteService siteService;
	
	@RequestMapping({"","/main"})
	public String main(Model model) {
		SiteVo siteVo = siteService.getSiteInformation();
		model.addAttribute("siteVo",siteVo);
		return "admin/main";
	}
	
	@RequestMapping("/main/update")
	public String updateSite(
			@ModelAttribute SiteVo siteVo,
			@RequestParam(value="upload-profile")MultipartFile multipartFile) {
		
		String profile = fileuploadService.restore(multipartFile);
		siteVo.setProfile(profile);
		siteService.updateSiteInformation(siteVo);
	
		return "redirect:/admin/main";
	}
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
}
