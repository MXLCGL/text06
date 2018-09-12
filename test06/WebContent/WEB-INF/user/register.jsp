<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登陆</title>


<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<style type="text/css">
</style>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$().ready(function() {

		$("#image").click(function() {
			$(this).attr("src", "user?type=randomImage&" + Math.random())

		})

		$("#user").click(function() {
			var username = $("#registername").val();
			var password = $("#registerpsw").val();
			var password2 = $("#registerpsw2").val();
			if (password == password2) {
				if (username == "") {
					alert("请输入要注册的账号");
				} else {
					alert("可以注册");
				}
			} else {
				alert("两次输入密码不一致！");
			}

		})

	})
</script>




</head>
<body>

	<div style="width: 600px; margin: 20px auto; margin-top: 120px">
		<form id="form" action="user?type=doRegister" method="post"
			class="form-horizontal" role="form">

			<div class="form-group">
				<label class="col-xs-2 control-label">账号:</label>
				<div class="col-xs-6">
					<input type="text" name="username" class="form-control"
						id="registername" placeholder="请输入账号" value="${name }" />
				</div>
			</div>



			<div class="form-group">
				<label class="col-xs-2 control-label">密码:</label>
				<div class="col-xs-6">
					<input type="password" name="password" class="form-control"
						id="registerpsw" placeholder="请输入密码" />

				</div>
			</div>



			<div class="form-group">
				<label class="col-xs-2 control-label">确认密码:</label>
				<div class="col-xs-6">
					<input type="password" name="password" class="form-control"
						id="registerpsw2" placeholder="请再次输入密码" />

				</div>
			</div>











			<div id="mes" style="height: 40px">${mes }</div>
			<div class="form-group" style="width: 400px; margin-left: 100px;">
				<input id="user" type="button" class="btn btn-primary"
					value="查看是否可以注册" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="reigiser" type="submit" class="btn btn-primary"
					value="注册" />

			</div>



		</form>
	</div>

</body>
</html>