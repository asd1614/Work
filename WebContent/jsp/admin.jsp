<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>报名管理系统管理</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/student_form.css" rel="stylesheet" type="text/css" />
<link href="../css/student_tool.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-1.11.1.js"></script>
<script src="../js/datepickter.js"></script>
<script src="../js/ajaxfileupload.js"></script>
<link href="../css/admin.css" rel="stylesheet" type="text/css" />
<script>
$(document).ready(function(){
	$('div#tool_left').load("admin_tool.jsp");
	$('div#content_right').load("../html/content_init.html");	
});
</script>
</head>
<body>
	<div id="main">
		<div id="head"></div>
		<div id="tool_left"></div>
		<div id="content_right"></div>
		<div id="bottom"></div>
	</div>
</body>
</html>