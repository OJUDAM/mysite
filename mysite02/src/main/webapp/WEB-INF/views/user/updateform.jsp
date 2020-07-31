<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@page import="com.bit2020.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />

		<div id="content">
			<div id="user">

				<form id="update-form" name="updateForm" method="post"
					action="${pageContext.request.contextPath }/user">
					<input type='hidden' name='a' value='update'> 
					<input type='hidden' name='no' value='${userVo.no}'>
					<input type='hidden' name='email' value='${userVo.email}'>
					<label
						class="block-label" for="name">이름</label> <input id="name"
						name="name" type="text" value="${userVo.name }"> <label
						class="block-label" for="email">이메일</label> 
						<h2>${userVo.email}</h2>
						<input type="button"
						value="id 중복체크"> <label class="block-label">패스워드</label> <input
						name="password" type="password" value="">

					<fieldset>
						<legend>성별</legend>
						<c:set var="gender" value="${userVo.gender}"/>
						<c:choose>
						<c:when test="${gender eq 'female' }">
						<label>여</label> <input type="radio" name="gender" value="female"
							checked="checked"> <label>남</label> <input type="radio"
							name="gender" value="male">
						</c:when>	
						<c:when test = "${gender eq 'male' }">
						<label>여</label> <input type="radio" name="gender" value="female"
							> <label>남</label> <input type="radio"
							name="gender" value="male" checked="checked">
						</c:when>
						</c:choose>
					</fieldset>

					<input type="submit" value="수정하기">

				</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />

	</div>
</body>
</html>