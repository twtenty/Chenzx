<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>商品搜索</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">

</head>
<body>


<jsp:include page="/admin/header.jsp">
	<jsp:param value="8" name="flag"/>
</jsp:include>

<span class="text-right">
		<form class="navbar-form" method="get" action="/admin/goods_search">
			<input type="text" class="form-control" name="keyword">
			<button type="submit" class="btn btn-default <c:if test="${param.flag==7 }">active</c:if>" aria-label="Left Align">搜索</button>
			<a class="btn btn-warning" href="/admin/goods_add.jsp">添加商品</a>
		</form>
</span>


<!--products-->
<div class="products">
	<div class="container">
		<h2> 搜索 ‘${param.keyword }’的结果 </h2>

		<table class="table table-bordered table-hover">

			<tr>
				<th width="5%">ID</th>
				<th width="10%">图片</th>
				<th width="10%">名称</th>
				<th width="20%">介绍</th>
				<th width="10%">价格</th>
				<th width="25%">操作</th>
			</tr>

			<c:forEach items="${p.list }" var="g">
				<tr>
					<td><p>${g.id }</p></td>
					<td><p><a href="/admin/goods_detail?id=${g.id}" target="_blank"><img src="${g.image}" width="100px" height="100px"></a></p></td>
					<td><p><a href="/admin/goods_detail?id=${g.id}" target="_blank">${g.name}</a></p></td>
					<td><p>${g.intro}</p></td>
					<td><p>${g.price}</p></td>
					<td>
						<a class="btn btn-success" href="/admin/goods_editshow?id=${g.id }& pageNumber=${p.pageNumber}">修改</a>
						<a class="btn btn-danger" href="/admin/goods_delete?id=${g.id }&pageNumber=${p.pageNumber}">删除</a>
					</td>
				</tr>
			</c:forEach>


		</table>
		<div>
			<jsp:include page="/page.jsp">
				<jsp:param name="url" value="/admin/goods_search"></jsp:param>
				<jsp:param name="param" value="&keyword=${keyword}"></jsp:param>
			</jsp:include>
		</div>
	</div>
</div>
<!--footer-->
<jsp:include page="/footer.jsp"></jsp:include>
<!--//footer-->

</body>
</html>