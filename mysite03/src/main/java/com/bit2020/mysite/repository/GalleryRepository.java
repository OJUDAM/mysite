package com.bit2020.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2020.mysite.vo.GalleryVo;

@Repository
public class GalleryRepository {

	@Autowired
	SqlSession sqlSession;
	
	public int delete(Long no) {
		return sqlSession.delete("gallery.delete",no);
	}

	public int insert(GalleryVo galleryVo) {
		return sqlSession.insert("gallery.insert",galleryVo);
	}

	public List<GalleryVo> getList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("gallery.getList");
	}
	

}
