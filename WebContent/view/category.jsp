<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script> 
$(function(){
	$('.card').on('click',function(){
//		alert(${ nowpage });
//		alert(${ pagecount }); 
//		alert(${ totalBasket })
//		alert(${ sessionScope.loginId } );
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
	
	/* $('.card').on('click', function(e){
        
        var obj =  $('img', this);
        console.dir(obj);
        var item_id =  $(obj).attr('item-id');
        alert(item_id);
        var params =  '&item_id=' +  item_id  + '&nowpage=${nowpage}&pagecount=${pagecount}';
      //  location.href='/board?cmd=DETAILVIEW' + params;
        
     });
	 */
});
</script>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Bootstrap icons-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../css/styles.css" rel="stylesheet" />
</head>
<style>
	.card-img-top {
		height: 170px;
		object-fit: cover;
	}
	
	.card {
		overflow: hidden;
		transition-duration: 1s;
	}
	
	.card:hover{
		background-color:rgba(0,0,0,0.2);
	}
	
	.card:hover img {
		transform: scale(1.1);
		transition: transform 1s;
	}
	
	
</style>
<body>
	<%@include file="/include/header.jsp" %>
	<!-- Related items section-->
	<section class="py-5 bg-light">
		<div class="container px-4 px-lg-5 mt-5">
			<h2 class="fw-bolder mb-4">${ category_code_name}</h2>
			<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-left">
				<c:forEach var="item" items="${ itemList }">
					<div class="col mb-5">
						<div class="card h-100">
							<!-- Product image-->
							<img class="card-img-top"
								src="../images/${fn:trim(item.item_id)}.jpg" alt="상품이미지" item_id="${fn:trim(item.item_id)}"/>
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
		<!-- container end -->

		<%@include file="/include/paging.jsp"%>
	</section>
	<%@include file="/include/footer.jsp"%>
	<%@include file="/include/notice.jsp"%>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="../js/scripts.js"></script>
</body>
</html>