<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div id="navigation">
		<ul>
			<li class="selected"><a href="${pageContext.request.contextPath }/admin">메인페이지</a></li>
			<li><a href="${pageContext.request.contextPath }/admin/guestbook">방명록관리</a></li>
			<li><a href="${pageContext.request.contextPath }/admin/board">게시판 관리</a></li>
			<li><a href="${pageContext.request.contextPath }/admin/user">사용자 관리</a></li>
		</ul>
	</div>
	<div id="footer">&nbsp;</div>