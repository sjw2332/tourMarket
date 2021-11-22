<%@page import="board.vo.BasketVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../css/styles.css" rel="stylesheet" />
<script src = "https://code.jquery.com/jquery-1.12.4.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<%
		//세션 ID값 가져오기
		String basket_id = session.getId();
		DecimalFormat dFormat = new DecimalFormat("###,###");		//숫자를 천단위 구분 
	%>


<style type="text/css">
    img{
    max-width: 30%;
    }
    .modal-header{
    border-bottom: 0px solid;
    }
    
    h1,p{
    padding: 20px;
    }
    p{
    text-align: center;
    }
    .fa{
    text-align: center;
    font-size: 40px;
    color: red;
    }
    .btn-primary{
    margin: 20px auto;
    display: block; 
    }
    .close{
    cursor: pointer;
    }
  </style>
 <script>
	$(function(){
		
		var totalBasket = ${ totalBasket };
		if(totalBasket == 0){
			$('.btnBuy').removeAttr('data-target');
		}
		
	});
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
					<th style="text-align:center; vertical-align: middle;">상품</th>
					<th style="text-align:center; vertical-align: middle;">가격</th>
					<th style="text-align:center; vertical-align: middle;">수량</th>
					<th style="text-align:center; vertical-align: middle;">소계</th>
					<th style="text-align:center; vertical-align: middle;">비고</th>
					<th></th>
				</tr>
				<c:forEach var="basket" items="${ basketList }">
				<tr>
					<td style="vertical-align: middle;">${ basket.item_name }</td> 
					<td style="text-align:center; vertical-align: middle;">${ basket.price }</td> 
					<td style="text-align:center; vertical-align: middle;">${ basket.cnt }</td> 
					<td style="text-align:center; vertical-align: middle;">${ basket.subtotal }</td> 
					<td style="text-align:center; vertical-align: middle;">${ basket.regdate }</td> 
					<td><a href="/board?cmd=DELETEBASKET&totalBasket=${ totalBasket }&basket_id=${basket.basket_id}" class="btn btn-danger">삭제</a></td>
				</tr>
				<c:set var="total" value="${total+basket.subtotal }"/>
				</c:forEach>			
				<tr>
					<th colspan="2" style="text-align:center; vertical-align: middle;">총액</th>
					
					<th></th>
					<th colspan="2" style="text-align:center; vertical-align: middle;"><c:out value="${ total }"/></th>
					
					<th>
						<!-- Button to Open the Modal -->
						    <button type="button" class="btn btn-primary mt-3 btnBuy" data-toggle="modal" data-target="#myModal">
						    구매하기
						    </button>
						    <!-- The Modal -->
						    <div class="modal" id="myModal">
						       <div class="modal-dialog">
						        <div class="modal-content"> 
						          <!-- Modal Header -->
						          <div class="modal-header">
						            <button type="button" id="close" class="close" data-dismiss="modal" >&times;</button>
						          </div>
						          <!-- Modal body -->
						          <div class="modal-body text-center mb-5">
						            <img src="/images/right.png" class="img-responsive">
						            <h1>결제를 진행하시겠습니까?</h1>
						            <p>확인 버튼을 누를 시 바로 결제가 완료됩니다.</p>
						            <div class="btn-group">
						              <button type="button" class="btn btn-danger btn-lg rounded-lg"  onclick="location.href='/board?cmd=PURCHASELIST&totalBasket=${ totalBasket }'" style="margin-right:10px; background-color:#0d6efd; "> 확인 </button>
						              <button type="button" class="btn btn-secondary btn-lg mr-2 rounded-lg" data-dismiss="modal"
						              	>취소</button>
						           </div>
						          </div> 
						        </div>
						      </div>
						    </div>
						
					</th>
				</tr>	
			</table>
			<a href="/board?cmd=MAINVIEW&totalBasket=${ totalBasket }" class="btn btn-secondary" onclick="">쇼핑 계속하기 &raquo;</a>
	
	</div>
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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>