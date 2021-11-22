<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<c:set  var="startPageNum"       value="${ pageVo.startpagenum }" />
<c:set  var="endPageNum"         value="${ pageVo.endpagenum }" />
<c:set  var="nowpage"            value="${ pageVo.nowpage }" />
<c:set  var="pagecount"          value="${ pageVo.pagecount }" />
<c:set  var="totalpagecount"     value="${ pageVo.totalpagecount }" />

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

<title>paging</title>
</head>
<body>
	<nav aria-label="Page navigation example">
		<ul class="pagination"  style="justify-content: center;">
		<c:if test="${ startPageNum gt 1 }">
			<li class="page-item">
				<a class="page-link" href="/board?cmd=CATEGORYVIEW&category_code_id=${category_code_id}&category_code_name=${ category_code_name }&totalBasket=${ totalBasket }&nowpage=${startPageNum-1}&pagecount=${pagecount}" aria-label="Previous"> 
					<span aria-hidden="true">&laquo;</span>
				</a>
			</li>
		</c:if>  
		    <c:forEach var="pagenum"  begin="${startPageNum}" end="${endPageNum}" step="1"  >
			    <li class="page-item"><a class="page-link" href="/board?cmd=CATEGORYVIEW&category_code_id=${category_code_id}&category_code_name=${ category_code_name }&totalBasket=${ totalBasket }&nowpage=${pagenum}&pagecount=${pagecount}">${ pagenum }</a></li>
			</c:forEach>  
		<c:if test="${ endPageNum  lt totalpagecount }">
			<li class="page-item">
				<a class="page-link" href="/board?cmd=CATEGORYVIEW&category_code_id=${category_code_id}&category_code_name=${ category_code_name }&totalBasket=${ totalBasket }&nowpage=${endPageNum+1}&pagecount=${pagecount}" aria-label="Next"> 
					<span aria-hidden="true">&raquo;</span>
				</a>
			</li>
		</c:if>
		</ul>
	</nav>

	<!-- Bootstrap JS -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>

</body>
</html>