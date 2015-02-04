<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ page import="java.util.*" %>
<%@ page import="cn.bsexam.dao.action.ExamAction" %>
<%@ page import="cn.bsexam.vo.ExamType" %>
<%@ page import="cn.bsexam.vo.SE" %>
<%@ taglib prefix="tag" uri="checkStatus" %><tag:checkStatus/>
<jsp:useBean id="s" scope="session" class="cn.bsexam.vo.Student"></jsp:useBean>
<jsp:setProperty property="*" name="s"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="../css/student_exam.css"/>
<%
	List<ExamType> list = (List<ExamType>) application.getAttribute("list_ex");
	ExamAction dao_ex = new ExamAction();
	SE se;
	if(list==null||list.isEmpty()){		
		list = dao_ex.getList();
		application.setAttribute("list_ex",list);
		se = dao_ex.getSE(s.getSno(), list.get(0).getEdate().toString().substring(0, 10));
		session.setAttribute("se", se);
	}else{
		se = dao_ex.getSE(s.getSno(), list.get(0).getEdate().toString().substring(0, 10));
		session.setAttribute("se", se);
	}
%>
<script>
	$(document).ready(function(){
		$('input[name="eno"]').change(function(){
			var no = $(this).val();		
			$(this).parent().siblings().css("border-color","#FFF");
			$.post(
				"../SignUpServlet",
				{eno:no},
				function(data){					
					$('.note').text(data);
					$('.note').show();
					$('input[value="'+no+'"]').parent().css("border-color","#699");
					
				}
			);
		});
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
				if(se.getEno()!=null&&se.getEno().equals(list.get(i).getEno())){
		%>
			<div class="div_input" style="border: 7px solid #669999;">
				<input type="radio" name="eno" value="<%=list.get(i).getEno() %>" checked /> 
				<label><%=list.get(i).getEname() %></label><br />
				<label><%=list.get(i).getEdate().toString().substring(0, 16) %></label><br />
			</div>
			<%	}else{
				%>
			<div class="div_input">
				<input type="radio" name="eno" value="<%=list.get(i).getEno() %>" /> 
				<label><%=list.get(i).getEname() %></label><br />
				<label><%=list.get(i).getEdate().toString().substring(0, 16) %></label><br />
			</div>
				<%} %>
		<%	} %>
	</form>
	<p class="note">修改成功</p>
</div>
