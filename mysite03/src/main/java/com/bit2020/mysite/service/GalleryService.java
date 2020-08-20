package com.bit2020.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2020.mysite.repository.GalleryRepository;
import com.bit2020.mysite.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryRepository galleryRepository;
	
	public boolean deleteImageInformation(Long no) {
		return 1 == galleryRepository.delete(no);
	}
	
	public boolean saveImageInformation(GalleryVo galleryVo) {
		return 1 == galleryRepository.insert(galleryVo);
	}
	
	public List<GalleryVo> getGalleryList(){
		return galleryRepository.getList();
	}
}
