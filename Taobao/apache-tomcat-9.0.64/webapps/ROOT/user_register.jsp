<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>

<head>
	<title>用户注册</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="css/style.css">

	<style>
		.error{
			color: red;
		}
	</style>

	<script type="text/javascript">
		window.onload = function(){
			//1.给表单绑定onsubmit事件
			document.getElementById("form").onsubmit = function (){
				//调用用户校验方法 checkUsername();
				//调用密码校验方法 checkPassword();
				//return checkUsername() && checkPassword();
				return checkUsername() && checkPassword() && checkEmail();
			}

			//给邮箱绑定离焦事件
			document.getElementById("email").onblur = checkEmail;
		}

		//校验用户名
		function checkUsername(){
			//1.获取用户名的值
			var username = document.getElementById("username").value;
			//2.正则表达式,以单字符开头，以单字符结尾，6到12位
			var reg_username = /^\w{4,12}$/;
			//3.判断是否符合正则表达式的规则
			var flag = reg_username.test(username);
			//4.提示信息
			var s_username = document.getElementById("s_username");
			if (flag){
				//提示绿色对勾
				// s_username.innerHTML = "<img width='35' height='25' src='/picture/gou.png' />";
				s_email.innerHTML = "";
			} else {
				//提示红色用户名有误
				s_username.innerHTML = "用户名格式有误";
			}
			return flag;
		}

		//校验密码
		function checkPassword(){
			//1.获取密码的值
			var password = document.getElementById("password").value;
			//2.正则表达式,以单字符开头，以单字符结尾，6到12位
			var reg_password = /^\w{6,12}$/;
			//3.判断是否符合正则表达式的规则
			var flag = reg_password.test(password);
			//4.提示信息
			var s_password = document.getElementById("s_password");
			if (flag){
				//提示绿色对勾
				// s_password.innerHTML = "<img width='35' height='25' src='/picture/gou.png' />";
				s_email.innerHTML = "";
			} else {
				//提示红色密码有误
				s_password.innerHTML = "密码格式有误";
			}
			return flag;
		}

		//校验邮箱
		function checkEmail(){
			//1.获取邮箱的值
			var email = document.getElementById("email").value;
			//2.正则表达式,以单字符开头，以单字符结尾，6到12位
			var reg_email = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/ ;
			// var reg_email = /^\w{6,12}$/;
			//3.判断是否符合正则表达式的规则
			var flag = reg_email.test(email);
			//4.提示信息
			var s_email = document.getElementById("s_email");
			if (flag){
				//提示绿色对勾
				// s_email.innerHTML = "<img width='35' height='25' src='/picture/gou.png' />";
				s_email.innerHTML = "";
			} else {
				//提示红色邮箱有误
				s_email.innerHTML = "邮箱格式有误";
			}
			return flag;
		}
	</script>
</head>
<body>

	<!--header-->
	<jsp:include page="header.jsp">
		<jsp:param name="flag" value="10"></jsp:param>
	</jsp:include>
	<!--//header-->


	<!--account-->
	<div class="account">
		<div class="container">
			<div class="register">
				<c:if test="${!empty msg }">
					<div class="alert alert-danger">${msg }</div>
				</c:if>
				<form action="/user_rigister" id="form" method="post">
					<div class="register-top-grid">
						<h3>注册新用户</h3>
						<div class="input">
							<span>用户名 <label style="color:red;">*</label></span>
							<input type="text" name="username" id="username" placeholder="请输入用户名(4-12字符)" required="required">
							<span id="s_username" style="color: red"></span>
						</div>
						<div class="input">
							<span>邮箱 <label style="color:red;">*</label></span>
							<input type="text" name="email" id="email" placeholder="请输入邮箱" required="required">
							<span id="s_email" style="color: red"></span>
						</div>
						<div class="input">
							<span>密码 <label style="color:red;">*</label></span>
							<span>
								<input type="password" name="password" id="password" placeholder="请输入密码(6-12字符)" required="required">
								<span id="s_password" style="color: red"></span>
							</span>
						</div>
						<div class="input">
							<span>收货人<label></label></span>
							<input type="text" name="name" placeholder="请输入收货">
						</div>
						<div class="input">
							<span>收货电话<label></label></span>
							<input type="text" name="phone" placeholder="请输入收货电话">
						</div>
						<div class="input">
							<span>收货地址<label></label></span>
							<input type="text" name="address" placeholder="请输入收货地址">
						</div>
						<div class="clearfix"> </div>
					</div>
					<div class="register-but text-center">
					   <input type="submit" value="提交">
					   <div class="clearfix"> </div>
					</div>
				</form>
				<div class="clearfix"> </div>
			</div>
	    </div>
	</div>
	<!--//account-->

	




	<!--footer-->
	<jsp:include page="/footer.jsp"></jsp:include>
	<!--//footer-->

	
</body>
</html>