<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- Sidebar-->


	<div class="border-end bg-white" id="sidebar-wrapper">
		<div class="list-group list-group-flush">
			<c:choose>
				<c:when test="${sessionScope.customer_id eq null}">
					<a
						class="list-group-item list-group-item-action list-group-item-light p-3"
						href="/board?cmd=LOGINFORM&totalBasket=${ totalBasket }">로그인</a>
				</c:when>
				<c:otherwise>
					<a
						class="list-group-item list-group-item-action list-group-item-light p-3"
						href="../view/logout.jsp">로그아웃</a>
				</c:otherwise>
			</c:choose>

			<c:if test="${sessionScope.customer_id eq null}">
				<a
					class="list-group-item list-group-item-action list-group-item-light p-3"
					href="/board?cmd=JOINFORM&totalBasket=${ totalBasket }">회원가입</a>
			</c:if>
			<c:if test="${sessionScope.customer_id ne null}">
					<a
						class="list-group-item list-group-item-action list-group-item-light p-3"
						href="/board?cmd=INFO&totalBasket=${ totalBasket }">회원정보수정</a>
			</c:if>
			<a
				class="list-group-item list-group-item-action list-group-item-light p-3"
				href="/board?cmd=BASKETLIST&totalBasket=${ totalBasket }">장바구니</a> <a
				class="list-group-item list-group-item-action list-group-item-light p-3"
				href="/board?cmd=PURCHASEFORM&totalBasket=${ totalBasket }">결제내역</a>
			<a
				class="list-group-item list-group-item-action list-group-item-light p-3"
				href="#!">O&A게시판</a>
		</div>
	</div>
</body>
</html>