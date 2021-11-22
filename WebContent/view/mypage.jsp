<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<style>
@import url('https://fonts.googleapis.com/css2?family=Sunflower:wght@300&display=swap');
</style>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@300&display=swap" rel="stylesheet">

<!-- Core theme CSS (includes Bootstrap)-->
<link href="../css/styles.css" rel="stylesheet" />

<style>
	table {
		
		padding : 20px;
		width: 800px;
		background: #fff;
		-webkit-border-radius: 10px;
		-moz-border-radius: 10px;
		border-radius: 10px;
		-webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
		-moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
		box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
	}
	
	table.tr {
		padding-left : 30px;
	}

	#info {	
		font-family : 'Sunflower', sans-serif;
		font-size : 160%;
	}
</style>

</head>
<body>
	<%@include file="/include/header.jsp" %>
	<div class="d-flex" id="wrapper">
		<!-- Sidebar-->
		<%@ include file="/include/sidebar.jsp"%>
		<!-- Page content wrapper-->
		<div id="page-content-wrapper">
			<!-- Top navigation-->
			<div class="container-fluid" style="margin-top: 10px;">
				
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
				</div>
			</div>

			<!-- Page content-->
			<div class="container-fluid">
				<h1 style="margin-top:100px;"> ${customer_id}  님 환영합니다!</h1>
				<table>
					<tr>
						<td style="width:120px; padding:20px; padding-top:100px; padding-bottom:100px;"><img src="../images/profile.png" width=100 height=100>
						</td>
						<td> 
							<ul style="vertical-align:top;">
								<li id=info>닉네임 :  ${cVo.customer_nickname}
								<li id=info>이메일 :  ${cVo.email}
								<li id=info>연락처 :  ${cVo.tel}
							</ul>
						</td>
					</tr>
				</table>
				
			</div>
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