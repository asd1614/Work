<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
    <%@ taglib prefix="tag" uri="checkStatus" %><tag:checkStatus/>
    <jsp:useBean id="s" scope="session" class="cn.bsexam.vo.Student"></jsp:useBean>
    <jsp:setProperty property="*" name="s"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" type="text/css" href="../css/ui/jquery-ui.css" />

<script>
	$(document).ready(function(){
		$('select#xib').load('../QueryList',		
		function(){
			var dname = $('select#xib').val();
			if(danem=='NULL')
				$('tr#class').hide();
		});
		function refresh_class(dname){
			$.post(
					'../QueryList',
					{
						dapartment: dname
					},
					function(data){$('select#cname').html(data);}
					);
		}
		$('div.view').one('mouseenter',function(){
			var d = $('#xib').val();
			refresh_class(d);
		});
		$('select#xib').change(
			function(){
				var d = $('#xib').val();
				refresh_class(d);
			}
		);
		$('input#datepicker').datepicker();
		function formSubmit(){
			$.post(
					'../SUpdateServlet',
					{
						name: $('[name="name"]').val(),
						idCard:$('[name="idCard"]').val(),
						sex:$('[name="sex"]').val(),
						stuClass:$('[name="stuClass"]').val(),
					},
					function(data){
						$('#message').text(data);
						$('#formsubimt').val('提交');
						}
					);
		}
		function refresh_Tool(){
			$('#td_name').text($('[name="name"]').val());
			$('#td_sno').text($('[name="sno"]').val());
			$('#td_cname').text($('[name="stuClass"]').val());
		}
		$('#check_form').submit(function(){
			$('#formsubimt').attr("disabled","disabled");
			$('#formsubimt').val('保存中');
			formSubmit();
			$('#message').show();
			refresh_Tool();
			$('#formsubimt').removeAttr('disabled');
			return false;	
		});
		$('#upload_button').click(function(){
			$.ajaxFileUpload({
				url:'../ImageUploadServlet',
				type:'POST',
				fileElementId:'image_file',
				dataType: 'text',
				success:function(data){
					$('#img_message').text(data);
					$('#img_message').show();
				}
			});
		});
	});
</script>
<div class="view">
	<p>个人资料</p>
	<form action="../ImageUploadServlet" class="imgform" name="img_form" method="post" >
		<div class="stu_img_right">
			<p>蓝色背景一寸证件照</p>
			<img src="../ImageSendServlet" onclick="this.src=this.src+'?'+ Math.random()"><br/>	
			<input type="file" name="view_file" value="浏览" id="image_file"/>
			<input type="button" name="upload_file" value="上传" id="upload_button"/>
			<p id="img_message"></p>
		</div>		
	</form>
	<form action="" class="client_form" id="check_form" name="client_form" method="post">
		<table class="form_table">	
			<tr>
				<th><label>学号</label></th>
				<td><input type="text" name="studentId" value="<%=s.getSno()%>" disabled="disabled"/></td>		
			</tr>
			<tr>
				<th><label>姓名</label></th>
				<td><input type="text" name="name" value="<%=s.getSname()%>" /></td>
			</tr>			
			<tr>
				<th><label>身份证号码</label></th>
				<td><input type="text" name="idCard" value="<%=s.getSid()%>"/></td>		
			<tr>
				<th><label>性别</label></th>
				<td><input type="radio" name="sex" value="男"
					<%=s.getSsex().equals("男")?"checked":"" %>><label>男</label>
					<input type="radio" name="sex" value="女"
					<%=s.getSsex().equals("女")?"checked":"" %>><label>女</label>
				</td>
			</tr>
			<tr>
				<th><label>出生日期</label></th>
				<%
					String idCard = s.getSid();
					String date ="";
					if(!idCard.equals(date)){
						char id[]=idCard.toCharArray();						
						date+=String.copyValueOf(id, 6, 4)+"-";
						date+=String.copyValueOf(id, 10, 2)+"-";
						date+=String.copyValueOf(id, 12, 2);
					}
											
				%>
				<td><input type="text" name="birthday" id="datepicker"
					value="<%=date==null?"":date%>"></td>
			</tr>
			<tr>
				<th><label>系别</label></th>
				<td><select name="dapartment" id="xib"></select>
				</td>
			</tr>
			<tr id="class">
				<th><label>班级</label></th>
				<td><select name="stuClass" id="cname"></select>
				</td>
			</tr>	
		</table>
		<div id="submitName">
			<p id="message"></p>
			<input type="reset" value="重置"/>
			<input type="submit" id="formsubimt" value="提交"/>
		</div>
	</form>
	
</div>
