<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
    <jsp:useBean id="stu" scope="session" class="cn.bsexam.vo.ShowStu"></jsp:useBean>
    <jsp:setProperty property="*" name="stu"/>
    <jsp:useBean id="s" scope="session" class="cn.bsexam.vo.Student"></jsp:useBean>
    <jsp:setProperty property="*" name="s"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
$(document).ready(function(){
	$('a#link1').click(function(){
		$('ul#uL1').slideToggle();
	});
	$('a#link2').click(function(){
		$('ul#uL2').slideToggle();
	});
	$('a#link3').click(function(){
		$('ul#uL3').slideToggle();
	});
	$('a#link4').click(function(){
		$('ul#uL4').slideToggle();
	});
	$('a#messli').click(function(){
		$('#content_right').load('studentForm.jsp');
	});
	$('a#exma_html').click(function(){
		$('#content_right').load('studentExam.jsp');
	});	
});
</script>
<div class="left_Tool">
	<div class="stu_img_left">
		<img src="../ImageSendServlet" onclick="this.src=this.src+'?'+ Math.random()" width="100" height="150" />
	</div>
	<div class="user_message">
		<table>
			<tr>
				<th>�û�����</th>
				<td id="td_name"><%=stu.getSname() %></td>
			</tr>
			<tr>
				<th>ѧ&emsp;�ţ�</th>
				<td id="td_sno"><%=stu.getSno() %></td>
			</tr>
			<tr>
				<th>��&emsp;��</th>
				<td id="td_cname"><%=stu.getCname() %></td>
			</tr>
			<tr>
				<th>ϵ&emsp;��</th>
				<td id="td_xib"><%=stu.getCdepat() %></td>
			</tr>
		</table>
	</div>
	<div class="menu">
		<ul>
			<li><a href="#" id="link1"><span>�ҵ�����</span></a>
				<ul id="uL1">
					<li><a href="#" id="messli"><span>��������</span></a></li>
				</ul></li>
			<li><a href="#" id="link2"><span>���Ա���</span></a>
				<ul id="uL2">
					<li><a href="#" id="exma_html"><span>Ӣ��ȼ�����</span></a></li>					
				</ul></li>
			<li><a href="#" id="link4"><span>ע��</span></a>
				<ul id="uL4">
					<li><a href="../LoginOutServlet" id="sign_off"><span>��ȫ�˳�</span></a></li>
				</ul></li>
		</ul>
	</div>
</div>