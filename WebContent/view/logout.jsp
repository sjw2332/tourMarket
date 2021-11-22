<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src = "https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	$(function(){
	
	});
</script>
</head>
<body>
	<%
		response.sendRedirect("../board?cmd=LOGOUT");
	%>
</body>
</html>