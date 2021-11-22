<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE document>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../css/styles.css" rel="stylesheet" />
<script src = "https://code.jquery.com/jquery-1.12.4.min.js"></script>

<script>
    window.addEventListener('load', () => {
      const forms = document.getElementsByClassName('validation-form');

      Array.prototype.filter.call(forms, (form) => {
        form.addEventListener('submit', function (event) {
          if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
          }

          form.classList.add('was-validated');
        }, false);
      });
    }, false);
  </script>

  <script type="text/javascript">
  
   //비밀번호, 비밀번호 확인 function
  function test(){
  	var p1 = document.getElementById('passwd').value;
  	var p2 = document.getElementById('passwd2').value;
  	
  	if(p1.length < 6) {
        alert('입력한 글자가 6글자 이상이어야 합니다.');
        return false;
       
    }
    
    if( p1 != p2 ) {
      alert("비밀번호불일치");
      return false;
      
    } else{
    	alert("회원정보가 수정되었습니다.");
      return true;
    }
	  
  }
  
  
  </script>
  
  
  <style>
    body {
      min-height: 100vh;

      
    }

    .row>.input-form {
      max-width: 680px;

      margin-top: 80px;
      padding: 32px;

      background: #fff;
      -webkit-border-radius: 10px;
      -moz-border-radius: 10px;
      border-radius: 10px;
      -webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
      -moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
      box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
    }
    
    
  </style>

</head>
<body>
	<%@include file="/include/header.jsp" %>
	<div class="d-flex" id="wrapper">
		<%@ include file="/include/sidebar.jsp" %>
		<!-- Page content wrapper-->
		<div id="page-content-wrapper">

			<!-- Page content-->
			
			
			<div class="container">
			
		
    <div class="input-form-backgroud row">
      <div class="input-form col-md-12 mx-auto">  <!-- col-md-12:  mx-auto : 수평 센터 처리 -->
        <h4 class="mb-3">회원정보수정</h4>
        <form class="validation-form" onsubmit="return test()" action="/board?cmd=CUPDATE&totalBasket=${totalBasket }" method="POST">
          <div class="row">
            
             <%String id = (String)session.getAttribute("customer_id"); %>
             
            <input type="hidden" name="customer_id" value="${sessionScope.customer_id}" />
           
            <div class="col-md-6 mb-3">
              <label for="nickname">닉네임</label>
              <input type="text" class="form-control" name="customer_nickname" placeholder="" value="" required >
              <div class="invalid-feedback">
                닉네임을 입력해주세요.
              </div>
            </div>
          </div>
          
          <div class="mb-3">
            <label for="password">비밀번호</label>
            <input type="password" class="form-control" name="passwd"  id="passwd" placeholder="password" required >
            <div class="invalid-feedback">
              비밀번호를 입력해주세요.
            </div>
          </div>
          
          <div class="mb-3">
            <label for="password">비밀번호 확인</label>
            <input type="password" class="form-control" name="passwd2" id="passwd2" required >
            <div class="invalid-feedback">
              비밀번호를 다시 입력해주세요.
            </div>
          </div>
          

          <div class="mb-3">
            <label for="email">이메일</label>
            <input type="email" class="form-control" name="email" required >
            <div class="invalid-feedback">
              이메일을 입력해주세요.
            </div>
          </div>

          <div class="mb-3">
            <label for="address">주소</label>
            <input type="text" class="form-control" name="address" placeholder="부산광역시 사하구" required >
            <div class="invalid-feedback">
              주소를 입력해주세요.
            </div>
          </div>


          <div class="mb-3">
            <label for="address">연락처</label>
            <input type="tel" class="form-control" name="tel" placeholder="연락처"  required>
            <div class="invalid-feedback">
              연락처를 입력해주세요.
            </div>
          </div>
          
          
          <hr class="mb-4">
          <div class="custom-control custom-checkbox">
            <input type="checkbox" class="custom-control-input" id="aggrement" required>
            <label class="custom-control-label" for="aggrement">제대로 입력하셨나요?</label>
          </div>
          <div class="mb-4"></div>
          
         
          <button class="btn btn-primary btn-lg btn-block btn-sub"  id="join_sub"  type="submit" >수정 완료</button>
          
          
        </form>
      </div>
    </div>
    <footer class="my-3 text-center text-small">
      <p class="mb-1">&copy; 2021 YD</p>
    </footer>
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