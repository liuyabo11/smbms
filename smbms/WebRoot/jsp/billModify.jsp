<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML ">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>超市账单管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>

<!--头部-->
<header class="publicHeader">
    <h1>超市账单管理系统</h1>

    <div class="publicHeaderR">
        <p><span>下午好！</span><span style="color: #fff21b"> Admin</span> , 欢迎你！</p>
        <a href="login.html">退出</a>
    </div>
</header>
<!--时间-->
<section class="publicTime">
    <span id="time">2015年1月1日 11:11  星期一</span>
    <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a>
</section>
<!--主体内容-->
<section class="publicMian ">
    <div class="left">
        <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
        <nav>
            <ul class="list">
                <li ><a href="bill.do">账单管理</a></li>
                <li><a href="provider.do">供应商管理</a></li>
                <li><a href="user.do">用户管理</a></li>
                <li><a href="jsp/password.jsp">密码修改</a></li>
                <li><a href="logout.do">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>账单管理页面 >> 账单添加页面</span>
        </div>
        <div class="providerAdd">
        	<input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
            <form id="billModify" action="realModifyBill" method="post">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <!-- 隐藏域 用来传递参数proid -->
            	<input type="hidden" name="billid" value="${bill.id }"/>
                <div class="">
                    <label for="billCode">订单编码：</label>
                    <input type="text" name="billCode" id="billCode" value="${bill.billCode }"/>
                    <font color="red"></font>
                </div>
                <div>
                    <label for="productName">商品名称：</label>
                    <input type="text" name="productName" id="productName" value="${bill.productName }"/>
                    <font color="red"></font>
                </div>
                <div>
                    <label for="productUnit">商品单位：</label>
                    <input type="text" name="productUnit" id="productUnit" value="${bill.productUnit }"/>
                    <font color="red"></font>

                </div>
                <div>
                    <label for="productCount">商品数量：</label>
                    <input type="text" name="productCount" id="productCount" value="${bill.productCount }"/>
                    <font color="red"></font>
                </div>
                <div>
                    <label for="totalPrice">总金额：</label>
                    <input type="text" name="totalPrice" id="totalPrice" value="${bill.totalPrice }"/>
                    <font color="red"></font>
                </div>
                <div>
                    <label >供应商：</label>
                    <select name="providerId" id="providerId">

                    </select>
                    <font color="red"></font>
                </div>
                <div>
                    <label >是否付款：</label>
                    
                    <input type="radio" name="isPayment" value="1" <c:if test="${bill.isPayment==1 }">checked</c:if>/>未付款
                    <input type="radio" name="isPayment" value="2" <c:if test="${bill.isPayment==2 }">checked</c:if>/>已付款
                </div>
                
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="userList.html">返回</a>-->
                    <input type="button" id="modify" name="modify" value="保存" />
                    <input type="button" id="button" name="button" value="返回" onclick="history.back(-1)"/>
                </div>
            </form>
        </div>

    </div>
</section>
<footer class="footer">
    版权归北大青鸟
</footer>
<script src="js/time.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/billmodify.js"></script>
</body>
</html>