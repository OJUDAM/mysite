<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${list}" var="list">
						<tr>
							<td>${list.no }</td>
							<td><a
								href="${pageContext.servletContext.contextPath}/board/view/${list.no}">${list.title }</a></td>
							<td>${list.name }</td>
							<td>${list.hit }</td>
							<td>${list.regDate }</td>
							<td><c:choose>
									<c:when test="${authUser.no eq list.userNo}">
										<a href="${pageContext.request.contextPath }/board/delete/${list.no}" class="del">삭제</a>
									</c:when>
									<c:otherwise>
									&nbsp;
									</c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>

				</table>
				<div class="bottom">
					<c:if test="${not empty authUser }">
						<a href="${pageContext.request.contextPath }/board/write"
							id="new-book">글쓰기</a>
					</c:if>

				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>