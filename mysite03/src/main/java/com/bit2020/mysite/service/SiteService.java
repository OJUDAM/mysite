package com.bit2020.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2020.mysite.repository.SiteRepository;
import com.bit2020.mysite.vo.SiteVo;

@Service
public class SiteService {
	@Autowired
	private SiteRepository siteRepository;
	
	public SiteVo getSiteInformation() {
		return siteRepository.get();
	}
	
	public boolean updateSiteInformation(SiteVo siteVo) {
		return siteRepository.update(siteVo)==1;
	}
}
