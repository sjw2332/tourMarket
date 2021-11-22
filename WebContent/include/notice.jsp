<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../css/styles.css" rel="stylesheet" />
<!-- xeicon : 벨 아이콘 라이브러리 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
/* 공지사항   */
.notice {
	-webkit-box-align: center;
	-webkit-box-pack: center;
	align-items: center;
	animation-delay: 0.35s;
	animation-direction: normal;
	animation-duration: 0.2s;
	animation-fill-mode: backwards;
	animation-iteration-count: 1;
	animation-name: fcEaNs;
	animation-play-state: running;
	animation-timing-function: cubic-bezier(0.1, 0, 0.6, 1);
	background-attachment: scroll;
	background-clip: border-box;
	background-color: rgba(0, 0, 0, 0);
	background-image: linear-gradient(rgb(243, 245, 252) 0%,
		rgb(243, 245, 252) 50%, rgb(243, 243, 252) 100%);
	background-origin: padding-box;
	background-position-x: 0%;
	background-position-y: 0%;
	background-size: auto;
	border-bottom-color: rgb(229, 231, 235);
	border-bottom-left-radius: 31px;
	border-bottom-right-radius: 31px;
	border-bottom-style: solid;
	border-bottom-width: 0px;
	border-image-outset: 0px;
	border-image-repeat: stretch;
	border-image-slice: 100%;
	border-image-source: none;
	border-image-width: 1;
	border-left-color: rgb(229, 231, 235);
	border-left-style: solid;
	border-left-width: 0px;
	border-right-color: rgb(229, 231, 235);
	border-right-style: solid;
	border-right-width: 0px;
	border-top-color: rgb(229, 231, 235);
	border-top-left-radius: 31px;
	border-top-right-radius: 31px;
	border-top-style: solid;
	border-top-width: 0px;
	box-shadow: rgba(0, 0, 0, 0.2) 0px 5px 10px 0px;
	box-sizing: border-box;
	color: rgb(0, 0, 0);
	cursor: pointer;
	display: flex;
	flex-wrap: nowrap;
	font-family: NotoSansKR, NotoSansJP, -apple-system, BlinkMacSystemFont,
		"Helvetica Neue", "맑은 고딕", "Yu Gothic", "Segoe UI", Roboto, system-ui,
		sans-serif;
	height: 62px;
	justify-content: center;
	line-height: 24px;
	overflow-x: visible;
	overflow-y: visible;
	position: relative;
	tab-size: 4;
	transition-delay: 0s;
	transition-duration: 0.2s;
	transition-property: box-shadow;
	transition-timing-function: ease-out;
	width: 62px;
	position: fixed;
	right: 30px;
	bottom: 30px;
}

#notice {
	width: 250px;
	background-color: #fbfcff;
	box-shadow: 3px 3px 7px rgba(0, 0, 0, 0.1);
	color: #595959;
	border-radius: 10px;
	height: 210px;
	position: fixed;
	bottom: 50px;
	right: 130px;
	text-align: left;
	padding-top: 10px;
	display:none;
}

.txt {
	padding-left: 20px;
}

#notice h3 {
	color: #0f0f0f;
}

#notice p {
	font-size: 11px;
	color: #6d6d6d;
}

#notice .not {
	margin-right: auto;
	margin-left: auto;
	padding-top: 32px;
	width: 230px;
	height: 150px;
	background-color: #ffffff;
	border-radius: 10px;
	bottom: 2;
	text-align: left;
	color: #111111;
	font-size: 15px;
}

#notice .not p {
	color: #111111;
	font-size: 15px;
	padding-left: 30px;
}
</style>
</head>

<body>
	<div class="notice">
		<i class="xi-bell xi-2x"></i>
	</div>

	<div id="notice">
		<div class="txt">
			<h3>&nbsp;&nbsp;&nbsp;&nbsp;NOTICE</h3>
		</div>
		<!--txt-->

		<div class="not">
			<p>
				저희 여행상품은 랜선투어로 <br /> 이루어져 있는 점을 <br /> 이용하기전 참고 해주시길 바랍니다.
			</p>
		</div>
		<!--not-->
	</div>


	<script>
        $(document).ready(function () {
            // 처음에는 #notice 태그 display : none
   //         $("#notice").hide();

            // .notice 태그 클릭시 실행
            $(".notice").click(function () {

           // #notice 태그 slide down() up()  메소드 사이를 토글 합니다.
                $("#notice").slideToggle();
            });
        });
    </script>
</body>

</html>