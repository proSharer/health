<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/health/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">

	$().ready(function() {

		keyup(); // 아이디 중복 체크..
		
		ckeckEsentialVulues(); // Validation check
	});

	function ckeckEsentialVulues() {

		$("#signUpForm").find("input[type=button]").click(function() {

			// 필수 입력 값 확인
			if ($("#userId").val() == "") {
				alert("아이디를 입력하세요");
				$("#userId").focus();
				return; // 리턴 할 것이 없으므로, 종료.
			}

			if ($("#userName").val() == null) {
				alert("이름을 입력해주세요.");
				$("#userName").focus();
				return;
			}
			
			if ($("#userPass1").val().length < 8) {
				alert("최소 8글자 입니다.");
				$("#userPass1").focus();
				return;
			}

			if ($("#userPass1").val() != $("#userPass2").val()) {
				alert("비밀번호가 일치하지 않습니다.");
				$("#userPass2").focus();
				return;
			}
			
			if($("#userHeight").val() <= 100 || $("#userHeight").val() > 250){
				alert("올바른 값을 입력해 주세요.");
				$("#userHeight").focus();
				return;
			}
			
			if($("#userWeight").val() <= 30 || $("#userHeight").val() > 300){
				alert("올바른 값을 입력해 주세요.");
				$("#userWeight").focus();
				return;
			}

			$.post("/health/user/checkDuplicate",
			{
				"userId" : $("#userId").val()
			},
			function(response) {
				var jsonObj = JSON.parse(response);

				if (jsonObj.duplicated) {
					alert("입력한 아이디는 이미 사용중입니다.");
					return;
				} else {
					$("#signUpForm").attr({
						"method" : "post",
						"action" : "/health/user/signUp"
					});
					$("#signUpForm").submit();
				}
			});
		});
	}

	function keyup() {
		$("#userId").keyup(function() {
			$.post("/health/user/checkDuplicate", 
			{
				"userId" : $("#userId").val()
			},

			function(response) {
				var jsonObj = JSON.parse(response);
				console.log(jsonObj);

				if (jsonObj.duplicated) {
					$("#duplicateState").text('이미 사용중인 아이디입니다.')
				} else {
					$("#duplicateState").text('사용 가능한 아이디 입니다.')
				}
			});
		});
	}
	
</script>
</head>
<body>

	<c:if test="${not empty param.errorCode}">
		<div>
			<c:choose>
				<c:when test="${param.errorCode=='0'}">ID는 필수</c:when>
				<c:when test="${param.errorCode=='1'}">Password 필수</c:when>
				<c:when test="${param.errorCode=='2'}">이름은 필수</c:when>
				<c:when test="${param.errorCode=='3'}">이미 사용중인 아이디 입니다.</c:when>
				<c:otherwise>??</c:otherwise>
			</c:choose>
		</div>
	</c:if>
	
	<form id="signUpForm" enctype="multipart/form-data"> <!-- 파일 보낼때 필수 form -->
														 <!-- ajax는 파일을 보내기를 할 수 없다. -->
														 <!-- name은 서블릿사용, id는 jsp사용 -->
		<input type="text" name="userId" id="userId" placeHolder="아이디를 입력하세요"/>
		<span id=duplicateState></span>
		<br/>
		<input type="text" name="userName" id="userName" placeholder="이름을 입력하세요"/>
		<br/>
		<input type="password" name="userPassword1" id="userPass1" placeholder="비밀번호를 입력하세요." />
		<br/>
		<input type="password" name="userPassword2" id="userPass2" placeholder="비밀번호를 입력하세요." />
		<br/>
		<input type="text" name="userHeight" id="userHeight" placeholder="키를 입력해주세요" />
		<br/>
		<input type="text" name="userWeight" id="userWeight" placeholder="몸무게를 입력해주세요." />
		<br/>
		<input type="button" value="가입" />
		<br/>
	</form>

</body>
</html>