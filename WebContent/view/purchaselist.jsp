<%@page import="board.vo.BasketVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제내역</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../css/styles.css" rel="stylesheet" />
<%
		//세션 ID값 가져오기
		String basket_id = session.getId();
		DecimalFormat dFormat = new DecimalFormat("###,###");		//숫자를 천단위 구분 
	%>

<script>
</script>
</head>
<body>
	<%@include file="/include/header.jsp" %>
	<div class="d-flex" id="wrapper">
	<%@ include file="/include/sidebar.jsp" %>
	<!-- Page content wrapper-->
	<div id="page-content-wrapper">
	<div class="container" style="margin-top: 30px;">		
		<div style="padding-top: 30px;">
			<table class="table table-hover">
				<tr>
					<th>상품</th>
					<th>가격</th>
					<th>수량</th>
					<th>주문금액</th>
					<th>주문일</th>
		
				</tr>
				<c:forEach var="purchaseList" items="${ purchaseList }">
				<tr>
					<td>${ purchaseList.item_name }</td>
					<td>${ purchaseList.price }</td>
					<td>${ purchaseList.cnt }</td>
					<td>${ purchaseList.subtotal }</td>
					<td>${ purchaseList.regdate }</td>
				</tr>
	<!-- 		<c:set var="total" value="${total+purchaseList.subtotal }"/>    -->	
				</c:forEach>	
			<%-- 	<tr>
					<th></th>
					<th></th>
					<th>총액</th> 
					<th><c:out value="${ total }"/></th> 
					<th></th> 				
				</tr> --%>
			</table>
			<a href="/board?cmd=MAINVIEW&totalBasket=${ totalBasket }" class="btn btn-secondary">쇼핑 계속하기 &raquo;</a>
		</div>
		<hr/>
	</div>

 <footer class="my-3 text-center text-small">
      <p class="mb-1">&copy; 2021 YD</p>
</footer>
</div>
</div>	
	<%@include file="/include/footer.jsp" %>
	<%@include file="/include/notice.jsp" %>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="../js/scripts.js"></script>
</body>
</html>