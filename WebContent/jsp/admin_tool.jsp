<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
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
		$('#content_right').load('');
	});
	$('a#exma_html').click(function(){
		$('#content_right').load('');
	});
	$('a#mm_alter').click(function(){
		$('#content_right').load('adminAlter.jsp');
	});
	
});
</script>
<div class="left_Tool">
	<div class="menu">
		<ul>
			<li><a href="#" id="link1"><span>信息管理</span></a>
				<ul id="uL1">
					<li><a href="#" id="system_open"><span>报名时间设定</span></a></li>						
					<li><a href="#" id="exam_alter"><span>考试信息管理</span></a></li>
					<li><a href="#" id="alter"><span>系部信息管理</span></a></li>
				</ul></li>
			<li><a href="#" id="link2"><span>报表导出</span></a>
				<ul id="uL2">
					<li><a href="#" id="excel"><span>报名Excel导出</span></a></li>					
				</ul></li>
			<li><a href="#" id="link3"><span>安全中心</span></a>
				<ul id="uL3">
					<li><a href="#" id="mm_alter"><span>密码修改</span></a></li>
				</ul></li>
			<li><a href="#" id="link4"><span>注销</span></a>
				<ul id="uL4">
					<li><a href="../LoginOutServlet" id="sign_off"><span>安全退出</span></a></li>
				</ul></li>
		</ul>
	</div>
</div>