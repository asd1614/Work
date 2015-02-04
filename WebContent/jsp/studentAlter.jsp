<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
    <%@ taglib prefix="tag" uri="checkStatus" %><tag:checkStatus/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="../css/student_alter.css"/>
<script type="text/javascript">
	$(document).ready(function(){
		$('#confirm').blur(function(){
			if($(this).val()!=$('#new').val()){
				$('#con_note').text('两次密码不一致');
				$('#con_note').show();
			}else{
				$('#con_note').hide();
			}
		});
		$('#mm').submit(function(){
			if($('#new').val()!=$('#new').val()){
				$('#con_note').text('两次密码不一致');
				$('#con_note').show();
				return false;
			}else{
				$.post(
						'../AlterPWServlet',
						{
							pass:$('#pass').val(),
							new_pass:$('#new').val(),
							confirm:$('#confirm').val()
						},
						function(data){
							if(data=='false'){
								$('#pass_note').text('密码错误');
								$('#pass_note').show();
							}else{
								$('#done_note').text(data);
								$('#done_note').show();
								$('#pass_note').hide();
							}
							
						}			
				);
				return false;
			}
		});
	});
</script>
<div class="alter">
	<p>密码修改</p>
	<form action="" id="mm" name="mm" method="post">
		<input type="password" style="display:none ;"/>
		<label for="pass" >旧密码</label>
			<input type="password" name="pass" id="pass" />
			<label id="pass_note" class="note"></label><br/>
		<label for="new" >新密码</label>
			<input type="password" name="new_pass" id="new" /><br/>
		<label for="confirm" >确认密码</label>
			<input type="password" name="confirm" id="confirm"/>
			<label id="con_note" class="note"></label><br/>	
		<input type="reset" id="re" value="重置"/>
		<input type="submit" id="su" value="提交"/><label id="done_note" class="note"></label>
	</form>
</div>