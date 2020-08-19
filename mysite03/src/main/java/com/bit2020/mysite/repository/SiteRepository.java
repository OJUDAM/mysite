package com.bit2020.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2020.mysite.vo.SiteVo;

@Repository
public class SiteRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public SiteVo get() {
		return sqlSession.selectOne("site.get");
	}

	public int update(SiteVo siteVo) {
		return sqlSession.update("site.update",siteVo);
	}

}
