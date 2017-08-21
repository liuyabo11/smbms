<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML >
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
            <p><span>下午好！</span><span style="color: #fff21b"> ${userSession.userName }</span> , 欢迎你！</p>
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
    <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
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
                <span>账单管理页面</span>
            </div>
            <div class="search">
            	<form action="bill.do" method="post" id="search">
            		<span>商品名称：</span>
	               <input type="text" placeholder="请输入商品名称" name="searchName" value="${searchName }"/>
	                
	                <span>供应商：</span>
	                <select name="providerID" >
	                    <option value="0">--请选择--</option>
	                    <c:if test="${providerList != null }">
		                    <c:forEach items="${providerList }" var="provider">
		                    	<option value="${provider.id }" <c:if test="${providerID==provider.id }">selected="selected"</c:if>>
		                    	
		                    	${provider.proName }
		                    	</option>
		                    </c:forEach>
		                 </c:if>
	                </select>
	
	                <span>是否付款：</span>
	                <select name="isPayment">
	                    <option value="0">--请选择--</option>
	                    
	                    <option value="1" <c:if test="${isPayment==1 }">selected="selected"</c:if>>未付款</option>
	                    <option value="2" <c:if test="${isPayment==2 }">selected="selected"</c:if>>已付款</option>
	                </select>
	                <input type="button" value="查询" onclick="search();"/>
                </form>
                <a href="jsp/billAdd.jsp">添加账单</a>
            </div>
            <!--用户-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="10%">账单编号</th>
                    <th width="20%">商品名称</th>
                    <th width="10%">供应商</th>
                    <th width="10%">账单金额</th>
                    <th width="10%">是否付款</th>
                    <th width="10%">创建时间</th>
                    <th width="30%">操作</th>
                </tr>
                <c:forEach items="${billList }" var="bill">
                
                <tr>
                    <td>${bill.billCode }</td>
                    <td>${bill.productName }</td>
                  
                    <td>${bill.providerName }</td>
                    <td>${bill.totalPrice }</td>
                   	<td>
	                   	<c:if test="${bill.isPayment==1 }">未付款</c:if>
	                   	<c:if test="${bill.isPayment==2 }">已付款</c:if>
                   	</td>
                    <td>
                    <fmt:formatDate value="${bill.creationDate }" pattern="yyyy-MM-dd HH:mm"/>
                    
                    </td>
                    <td>
                        <a href="javascript:;" class="viewBill" billid=${bill.id } ><img src="img/read.png" alt="查看" title="查看"/></a>
                        <a href="javascript:;" class="modifyBill" billid=${bill.id } ><img src="img/xiugai.png" alt="修改" title="修改"/></a>
                        <a href="javascript:;" class="deleteBill" billid=${bill.id } ><img src="img/schu.png" alt="删除" title="删除"/></a>
                    </td>
                </tr>
                </c:forEach>
            </table>

        </div>
    </section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

    <footer class="footer">
        版权归北大青鸟
    </footer>

<script src="js/jquery.js"></script>
<script src="js/js.js"></script>
<script src="js/time.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/billlist.js"></script>
<script type="text/javascript">


function search(){
	var form=document.getElementById("search");
	form.submit();
}
</script>
</script>
</body>
</html>