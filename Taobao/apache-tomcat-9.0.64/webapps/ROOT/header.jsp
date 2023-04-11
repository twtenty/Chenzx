<%--
  Created by IntelliJ IDEA.
  User: 86158
  Date: 2022/11/8
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
<link type="text/css" rel="stylesheet" href="css/style.css">

<div class="header">
    <div class="container">
        <nav class="navbar navbar-default">
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/index.jsp">首页</a> </li>
                    <li class="dropdown">
                        <a href=“#” class="dropdown-toggle" data-toggle="dropdown">商品分类
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu multi-column columns-2">
                            <li>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <h4>商品分类</h4>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li><a href="https://www.taobao.com">热销</a> </li>
                    <li><a href="#">新品</a> </li>
                    <li><a href="user_register.jsp" >注册</a> </li>
                    <li><a href="user_login.jsp" class="active">登录</a> </li>
                </ul>
            </div>
        </nav>
    </div>
</div>