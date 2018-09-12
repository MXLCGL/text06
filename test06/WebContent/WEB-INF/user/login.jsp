<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
		$("#zhuce").click(function() {
			location.href = "user"
		})
		
	})
</script>




</head>
<body>

	<div style="width: 600px; margin: 20px auto; margin-top: 120px">
		<form id="form" action="user?type=doLogin" method="post"
			class="form-horizontal" role="form">

			<div class="form-group">
				<label class="col-xs-2 control-label">账号:</label>
				<div class="col-xs-6">
					<input type="text" name="username" class="form-control"
						placeholder="请输入账号" value="${name }" />
				</div>
			</div>



			<div class="form-group">
				<label class="col-xs-2 control-label">密码:</label>
				<div class="col-xs-6">
					<input type="password" name="password" class="form-control"
						placeholder="请输入密码" />

				</div>
			</div>


			<div class="form-group">
				<label class="col-xs-2 control-label">验证:</label>
				<div class="col-xs-6">
					<input type="text" name="random" class="form-control"
						placeholder="请输入验证码" />
				</div>
				<div class="col-xs-2">
					<img id="image" src="user?type=randomImage" />
				</div>
			</div>


			<div id="mes" style="height: 40px">${mes }</div>
			<div class="form-group" style="width: 200px; margin-left: 180px;">

				<input id="login" type="submit" class="btn btn-primary" value="登陆" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="zhuce" type="button" class="btn btn-primary" value="注册" />
			</div>


		</form>
	</div>

</body>
</html>