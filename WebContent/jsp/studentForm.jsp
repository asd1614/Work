<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
    <jsp:useBean id="stu" scope="session" class="cn.bsexam.vo.ShowStu"></jsp:useBean>
    <jsp:setProperty property="*" name="stu"/>
    <jsp:useBean id="s" scope="session" class="cn.bsexam.vo.Student"></jsp:useBean>
    <jsp:setProperty property="*" name="s"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" type="text/css" href="../css/ui/jquery-ui.css" />

<script>
	$(document).ready(function(){
		$('select#xib').load('<%="http://"+request.getHeader("host")+"/Work//QueryList"%>',		
		function(){
			var dname = $('select#xib').val();
			if(danem=='NULL')
				$('tr#class').hide();
		});
		function refresh_class(dname){
			$.post(
					'<%="http://"+request.getHeader("host")+"/Work//QueryList"%>',
					{
						dapartment: dname
					},
					function(data){$('select#cname').html(data);}
					);
		}
		refresh_class("SJ");
		$('input#datepicker').datepicker();
	});
</script>
<div class="view">
	<p>��������</p>
	<form action="" class="imgform" name="img_form" method="post" >
		<div class="stu_img_right">
			<p>��ɫ����һ��֤����</p>
			<img src="../images/img.jpg"><br/>	
			<input type="file" name="view_file" value="���"/>
			<input type="button" name="upload_file" value="�ϴ�"/>
		</div>		
	</form>
	<form action="" class="client_form" id="check_form" name="client_form" method="post">
		<table class="form_table">	
			<tr>
				<th><label>ѧ��</label></th>
				<td><input type="text" name="studentId" value="<%=s.getSno()%>"/></td>		
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
					char id[]=idCard.toCharArray();
					String date ="";
					date+=String.copyValueOf(id, 6, 4)+"-";
					date+=String.copyValueOf(id, 10, 2)+"-";
					date+=String.copyValueOf(id, 12, 2);
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
			<input type="reset" value="����"/>
			<input type="submit" value="�ύ"/>
		</div>
	</form>
	
</div>
