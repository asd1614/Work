<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ page import="java.util.*" %>
<%@ page import="cn.bsexam.dao.action.ExamAction" %>
<%@ page import="cn.bsexam.vo.ExamType" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="../css/student_exam.css"/>
<%
	List<ExamType> list = (List<ExamType>) application.getAttribute("list_ex");
	if(list==null||list.isEmpty()){
		ExamAction dao_ex = new ExamAction();
		list = dao_ex.getList();
		application.setAttribute("list_ex",list);
	}
%>
<script>
	$(document).ready(function(){
		
	});
</script>
<div class="exam">
	<p>考试报名</p>
	<pre>
		在下面选择你要报名的考试，并只能选择一个考试进行报名。
	</pre>
	<form>
		<%
			for(int i = 0;i<list.size();i++	){
		%>
			<div class="div_input">
				<input type="radio" name="eno" value="<%=list.get(i).getEno() %>" /> 
				<label><%=list.get(i).getEname() %></label><br />
				<label><%=list.get(i).getEdate().toString().substring(0, 16) %></label><br />
			</div>
		<%	} %>
	</form>
</div>
