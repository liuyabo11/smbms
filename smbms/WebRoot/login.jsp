<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    <title>系统登录 - 超市账单管理系统</title>
    <link rel="stylesheet" href="css/style.css"/>
</head>
	<body class="login_bg">
    	<section class="loginBox">
        	<header class="loginHeader">
            	<h1>超市账单管理系统</h1>
        	</header>
        	<section class="loginCont">
	            <form class="loginForm" action="login.do">
	                <div class="inputbox">
	                    <label for="user">用户名：</label>
	                    <input id="user" type="text" id="userCode" name="userCode" placeholder="请输入用户名" required/>
	                </div>
	                <div class="inputbox">
	                    <label for="mima">密码：</label>
	                    <input id="mima" type="password" id="userPassword" name="userPassword" placeholder="请输入密码" required/>
	                </div>
	                <div class="subBtn">
	                	${erro }
	                    <input type="submit" value="登录" />
	                    <input type="reset" value="重置"/>
	                </div>
	            </form>
        	</section>
    	</section>
    
	</body>
</html>
  