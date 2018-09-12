<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

<style type="text/css">
#main {
	width: 400px;
	margin: 20px auto;
}
</style>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>

	<div id="main">
		<form action="emp" class="form-horizontal" role="form" method="post">
			<input type="hidden" name="type" value="update" /> <input
				type="hidden" name="id" value="${emp.id }" />

			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">姓名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name"
						value="${emp.name }">
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">性别</label>
				<div class="col-sm-10">
					<input type="radio" name="sex" value="男"
						<c:if test="${emp.sex=='男' }">
						checked </c:if>>男 <input
						type="radio" name="sex" value="女"
						<c:if test="${emp.sex=='女' }"> checked </c:if>>女

				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">年龄</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="age"
						value="${emp.age}">
				</div>
			</div>
			
			<div class="form-group">
    <label for="lastname" class="col-sm-2 control-label">部门</label>
    <div class="col-sm-10">
        <select name="depId" class="form-control">
      <option value="">部门</option>
     <c:forEach items="${ depList}" var="dep">
     
      <option value="${dep.id }"  <c:if test="${dep.id==emp.dep.id}">selected</c:if>>${dep.name}</option>
     </c:forEach>
     
     </select> 
    </div>
  </div>
			

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">保存</button>
				</div>
			</div>
		</form>

	</div>
</body>
</html>