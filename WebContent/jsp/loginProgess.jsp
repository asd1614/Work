<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>
<%@ page import="cn.bsexam.util.DataDeal" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	color: #FFFFFF;
	font-family: "微软雅黑";
}
body{
	background-color:#1ab9fb;
	}
table{
margin: 20% auto 0;
width: 50%;
height: 10%;
}
.progress{
	background-color:#CCCC33;
}
h3{
margin-top:2%;
text-align:center
}
</style>
 <% 
	DataDeal deal = (DataDeal)session.getAttribute("deal");
	int progress = deal.getProgress(); 
	if(progress==-1){
		session.removeAttribute("sessionId");%>
	<script type="" LANGUAGE="JavaScript">
      setTimeout("location='../index.html'", 500);
    </script>
    <%} %>
  	<%if (progress>=0&& progress<10) { %>
    <script type="" LANGUAGE="JavaScript">
      setTimeout("location='loginProgess.jsp'", 1000);
    </script>
	<% } %>
	<%if(progress==10) {%>
	<script type="" LANGUAGE="JavaScript">
      setTimeout("location='studentMain.jsp'", 500);
    </script>
    <%} %>
<title>百色学院报名系统</title>
</head>
<body>
	<table >
		<tr>

			<% for(int i =1 ;i<=progress;i++){	%>
				<td class="progress" >&nbsp;</td>
			<% } %>
			<% for(int j =4 ;j>progress;j--){	%>
				<td  >&nbsp;</td>
			<% } %>
		</tr>
	</table>
	<h3>系统正在从教务管理系统导入资料。。。。。。</h3>
</body>
</html>