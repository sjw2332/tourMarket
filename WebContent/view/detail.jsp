<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- <%
	String item_id = request.getParameter("item_id");
	
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Bootstrap icons-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../css/styles.css" rel="stylesheet" />
<style>
	#related .card-img-top {
		height: 170px;
		object-fit: cover;
	}
	
	#related .card {
		overflow: hidden;
		transition-duration: 1s;
	}
	
	#related .card:hover {
		background-color: rgba(0, 0, 0, 0.2);
	}
	
	#related .card:hover img {
		transform: scale(1.1);
		transition: transform 1s;
	}
	
	
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
$(function(){
	$('.card').on('click',function(){
		var item_id = $(this).find('img').attr('item_id');
		var category_code_id = '${ category_code_id }';
		var category_code_name = '${ category_code_name }';
		var totalBasket = ${ totalBasket };
		var nowpage = ${ nowpage };
		var pagecount = ${ pagecount };
		//http://localhost:9090/board?cmd=DETAILVIEW&item_id=101&category_code_id=100k&nowpage=3&pagecount=2
		var params =  '&item_id=' + item_id
					+ '&category_code_id=' + category_code_id
					+ '&category_code_name=' + category_code_name
					+ '&totalBasket=' + totalBasket
					+ '&nowpage=' + nowpage
					+ '&pagecount=' + pagecount;
		location.href='/board?cmd=DETAILVIEW' + params;
	});
});

</script>
</head>
<body>
	<%@include file="/include/header.jsp" %>
	<!-- Product section-->
	<section class="py-5">
		<div class="container px-4 px-lg-5 my-5">
			<div class="row gx-4 gx-lg-5 align-items-center">
		<%-- 	${ item_id }
			<%= item_id %> --%>
				<div class="col-md-6">
					<img class="card-img-top mb-5 mb-md-0"
						src="../images/${fn:trim(itemVo.item_id)}.jpg" alt="상품이미지" />     <!-- ITEM_ID -->
				</div>
				<div class="col-md-6">
					<h1 class="display-5 fw-bolder">${ itemVo.item_name }</h1> <!-- ITEM_NAME -->
					<div class="fs-5 mb-5">
						<span>₩${ itemVo.price}</span> <!-- PRICE -->
					</div>
					
					<p class="lead">${ fn:replace(itemVo.item_cont, newLineChar, "</br>") }</p><!-- ITEM_CONT -->
					<div class="d-flex">
					<table>							
					<c:choose>
					<c:when test="${ sessionScope.loginId  eq null }">
						<a class="btn btn-outline-dark mt-auto" href="/board?cmd=LOGINFORM&totalBasket=${ totalBasket }">장바구니에 담기</a> 
					</c:when>
					<c:otherwise>
					<form  action="/board?cmd=DETAILBASKET" method="POST">
					    <input type="hidden" name="category_code_id" value="${ category_code_id }" />
					    <input type="hidden" name="category_code_name" value="${ category_code_name }" />
					    <input type="hidden" name="item_id" value="${ item_id }" />
					    <input type="hidden" name="nowpage" value="${ nowpage }" />
					    <input type="hidden" name="pagecount" value="${ pagecount }" />
					    <input type="hidden" name="totalBasket" value="${ totalBasket }" />
						<input class="form-control text-center me-3" id="inputQuantity"
							type="number" name="cnt" value="1" style="max-width: 3rem" />
						<input class="btn btn-outline-dark flex-shrink-0" style="margin-left:10px" type="submit" value="장바구니담기">
					</form>
					</c:otherwise>
					</c:choose>
					</table>
						<%-- <a class="btn btn-outline-dark flex-shrink-0" href="/board?cmd=PURCHASE&category_code_id=${ category_code_id }&category_code_name=${ category_code_name }&item_id=${ item_id }&nowpage=${ nowpage }&pagecount=${ pagecount }" style="margin-left:10px">
							<i class="bi-cart-fill me-1"></i>결제하기</a> --%>
						<a class="btn btn-outline-dark flex-shrink-0" href="/board?cmd=CATEGORYVIEW&category_code_id=${ category_code_id }&category_code_name=${ category_code_name }&totalBasket=${ totalBasket }&nowpage=${ nowpage }&pagecount=${ pagecount }" style="margin-left:10px">
							목록으로</a>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Related items section-->
	<section class="py-5 bg-light">
		<div class="container px-4 px-lg-5 mt-5">
			<h2 class="fw-bolder mb-4">Related products</h2>
			<h4 class="fw-bolder mb-3">${category_code_name }</h4>
			<div id="related" class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-left">
				<c:forEach var="item" items="${ itemList }" end="3">
					<div class="col mb-5">
						<div class="card h-100">
							<!-- Product image-->
							<img class="card-img-top"
								src="../images/${item.item_id}.jpg" alt="상품이미지" item_id="${item.item_id}"/>
							<!-- Product details-->
							<div class="card-body p-4">
								<div class="text-center">
									<!-- Product name-->
									<h5 class="fw-bolder">${ item.item_name }</h5>
									<!-- Product price-->
									₩${ item.price } 
								</div>
							</div>
							<!-- Product actions-->
							<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
								<div class="text-center">
								<c:choose>
								<c:when test="${ sessionScope.loginId  eq null }">
									<a class="btn btn-outline-dark mt-auto" href="/board?cmd=LOGINFORM&totalBasket=${ totalBasket }">장바구니에 담기</a>
								</c:when>
								<c:otherwise>
									<a class="btn btn-outline-dark mt-auto" href="/board?cmd=CATEGORYBASKET&category_code_id=${ category_code_id }&category_code_name=${ category_code_name }&item_id=${fn:trim(item.item_id)}&totalBasket=${ totalBasket }&nowpage=${ nowpage }&pagecount=${ pagecount }&cnt=1">장바구니에 담기</a>
								</c:otherwise>
								</c:choose>
								</div>
								<!-- text-center end -->
							</div>
							<!-- car footer end -->
						</div>
						<!-- card end -->
					</div>
					<!-- col end -->
				</c:forEach>
			</div>
			<!-- row end -->
		</div>
	</section>
	<%@include file="/include/footer.jsp" %>
	<%@include file="/include/notice.jsp" %>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="../js/scripts.js"></script>
</body>
</html>