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
						$('#formsubimt').val('�ύ');
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
			$('#formsubimt').val('������');
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
	<p>��������</p>
	<form action="../ImageUploadServlet" class="imgform" name="img_form" method="post" >
		<div class="stu_img_right">
			<p>��ɫ����һ��֤����</p>
			<img src="../ImageSendServlet" onclick="this.src=this.src+'?'+ Math.random()"><br/>	
			<input type="file" name="view_file" value="���" id="image_file"/>
			<input type="button" name="upload_file" value="�ϴ�" id="upload_button"/>
			<p id="img_message"></p>
		</div>		
	</form>
	<form action="" class="client_form" id="check_form" name="client_form" method="post">
		<table class="form_table">	
			<tr>
				<th><label>ѧ��</label></th>
				<td><input type="text" name="studentId" value="<%=s.getSno()%>" disabled="disabled"/></td>		
			</tr>
			<tr>
				<th><label>����</label></th>
				<td><input type="text" name="name" value="<%=s.getSname()%>" /></td>
			</tr>			
			<tr>
				<th><label>���֤����</label></th>
				<td><input type="text" name="idCard" value="<%=s.getSid()%>"/></td>		
			<tr>
				<th><label>�Ա�</label></th>
				<td><input type="radio" name="sex" value="��"
					<%=s.getSsex().equals("��")?"checked":"" %>><label>��</label>
					<input type="radio" name="sex" value="Ů"
					<%=s.getSsex().equals("Ů")?"checked":"" %>><label>Ů</label>
				</td>
			</tr>
			<tr>
				<th><label>��������</label></th>
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
				<th><label>ϵ��</label></th>
				<td><select name="dapartment" id="xib"></select>
				</td>
			</tr>
			<tr id="class">
				<th><label>�༶</label></th>
				<td><select name="stuClass" id="cname"></select>
				</td>
			</tr>	
		</table>
		<div id="submitName">
			<p id="message"></p>
			<input type="reset" value="����"/>
			<input type="submit" id="formsubimt" value="�ύ"/>
		</div>
	</form>
	
</div>
