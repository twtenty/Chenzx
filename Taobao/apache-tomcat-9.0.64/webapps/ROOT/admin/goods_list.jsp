<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>商品列表</title>
	<meta charset="utf-8"/>
	<link rel="stylesheet" href="css/bootstrap.css"/>
</head>
<body>
<div class="container-fluid">

	<jsp:include page="/admin/header.jsp"></jsp:include>

	<span class="text-right">
		<form class="navbar-form" method="get" action="/admin/goods_search">
			<input type="text" class="form-control" name="keyword">
			<button type="submit" class="btn btn-default <c:if test="${param.flag==7 }">active</c:if>" aria-label="Left Align">搜索</button>
			<a class="btn btn-warning" href="/admin/goods_add.jsp">添加商品</a>
		</form>
	</span>

	<br>

	<ul role="tablist" class="nav nav-tabs">
		<li role="presentation"><a href="/admin/goods_list">全部商品</a></li>
	</ul>

	<br>

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

	<br>
	<jsp:include page="/page.jsp">
		<jsp:param value="/admin/goods_list" name="url"/>
	</jsp:include>
	<br>
</div>
<!--footer-->
<jsp:include page="/footer.jsp"></jsp:include>
<!--//footer-->
</body>
</html>