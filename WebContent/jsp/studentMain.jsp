<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
   <%@ page import="cn.bsexam.dao.action.*" %>
   <%@ page import="cn.bsexam.vo.Student" %>
   <%@ page import="cn.bsexam.vo.ShowStu" %>
   <%@ taglib prefix="tag" uri="checkStatus" %>
   <tag:checkStatus/>
<%
String sno = (String)session.getAttribute("sno");
Student s = LoginAction.getStudent(sno);
ShowStu stu = LoginAction.getStu(sno);
session.setAttribute("s", s);
session.setAttribute("stu", stu);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>百色学院报名系统</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/student_form.css" rel="stylesheet" type="text/css" />
<link href="../css/student_tool.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-1.11.1.js"></script>
<script src="../js/datepickter.js"></script>
<script src="../js/ajaxfileupload.js"></script>

<script>
$(document).ready(function(){
	$('div#tool_left').load("studentTools.jsp");
	$('div#content_right').load("../html/content_init.html");	
});
</script>
</head>
<body>
	<div id="main">
		<div id="head_left"></div>
		<div id="head_right"></div>
		<div id="tool_left"></div>
		<div id="content_right"></div>
		<div id="bottom"></div>
	</div>
</body>
</html>