package cn.bsexam.servlet;

import cn.bsexm.util.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NewStudentSerlvet
 */
public class NewStudentSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String HTTP_200 = "MAINFRM.aspx";  
	private static final String WardURL = "/jsp/studentMain.jsp";
    private static final String Redirect = "/index.html";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewStudentSerlvet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("GB2312");
		Map<String,String[]> parameter = request.getParameterMap();
		response.setCharacterEncoding("GB2312");
		String _form = "__VIEWSTATE=dDwtMTMwOTYyOTQ5Mjt0PDtsPGk8MD47aTwxPjtpPDI%2BOz47bDx0PHA8bDxUZXh0Oz47bDznmb7oibLlrabpmaI7Pj47Oz47dDxwPGw8VGV4dDs%2BO2w8XDxzY3JpcHQgdHlwZT0idGV4dC9qYXZhc2NyaXB0Ilw%2BClw8IS0tCmZ1bmN0aW9uIENoa1ZhbHVlKCl7CiB2YXIgdlU9JCgnVUlEJykuaW5uZXJIVE1MXDsKIHZVPXZVLnN1YnN0cmluZygwLDEpK3ZVLnN1YnN0cmluZygyLDMpXDsKIHZhciB2Y0ZsYWcgPSAiWUVTIlw7IGlmICgkKCd0eHRfYXNtY2RlZnNkZHNkJykudmFsdWU9PScnKXsKIGFsZXJ0KCfpobvlvZXlhaUnK3ZVKyfvvIEnKVw7JCgndHh0X2FzbWNkZWZzZGRzZCcpLmZvY3VzKClcO3JldHVybiBmYWxzZVw7Cn0KIGVsc2UgaWYgKCQoJ3R4dF9wZXdlcndlZHNkZnNkZmYnKS52YWx1ZT09JycpewogYWxlcnQoJ%2Bmhu%2BW9leWFpeWvhuegge%2B8gScpXDskKCd0eHRfcGV3ZXJ3ZWRzZGZzZGZmJykuZm9jdXMoKVw7cmV0dXJuIGZhbHNlXDsKfQogZWxzZSBpZiAoJCgndHh0X3NkZXJ0ZmdzYWRzY3hjYWRzYWRzJykudmFsdWU9PScnICYmIHZjRmxhZyA9PSAiWUVTIil7CiBhbGVydCgn6aG75b2V5YWl6aqM6K%2BB56CB77yBJylcOyQoJ3R4dF9zZGVydGZnc2Fkc2N4Y2Fkc2FkcycpLmZvY3VzKClcO3JldHVybiBmYWxzZVw7Cn0KIGVsc2UgeyAkKCdkaXZMb2dOb3RlJykuaW5uZXJIVE1MPSdcPGZvbnQgY29sb3I9InJlZCJcPuato%2BWcqOmAmui%2Fh%2Bi6q%2BS7vemqjOivgS4uLuivt%2BeojeWAmSFcPC9mb250XD4nXDsKIHJldHVybiB0cnVlXDt9Cn0KZnVuY3Rpb24gU2VsVHlwZShvYmopewogdmFyIHM9b2JqLm9wdGlvbnNbb2JqLnNlbGVjdGVkSW5kZXhdLmdldEF0dHJpYnV0ZSgndXNySUQnKVw7CiB2YXIgdz1vYmoub3B0aW9uc1tvYmouc2VsZWN0ZWRJbmRleF0uZ2V0QXR0cmlidXRlKCdQd2RJRCcpXDsKICQoJ1VJRCcpLmlubmVySFRNTD1zXDsKIHNlbFR5ZU5hbWUoKVw7Cn0KZnVuY3Rpb24gb3BlbldpbkxvZyh0aGVVUkwsdyxoKXsKdmFyIFRmb3JtLHJldFN0clw7CmV2YWwoIlRmb3JtPSd3aWR0aD0iK3crIixoZWlnaHQ9IitoKyIsc2Nyb2xsYmFycz1ubyxyZXNpemFibGU9bm8nIilcOwpwb3A9d2luZG93Lm9wZW4odGhlVVJMLCd3aW5LUFQnLFRmb3JtKVw7IC8vcG9wLm1vdmVUbygwLDc1KVw7CmV2YWwoIlRmb3JtPSdkaWFsb2dXaWR0aDoiK3crInB4XDtkaWFsb2dIZWlnaHQ6IitoKyJweFw7c3RhdHVzOm5vXDtzY3JvbGxiYXJzPW5vXDtoZWxwOm5vJyIpXDsKcG9wLm1vdmVUbygoc2NyZWVuLndpZHRoLXcpLzIsKHNjcmVlbi5oZWlnaHQtaCkvMilcO2lmKHR5cGVvZihyZXRTdHIpIT0ndW5kZWZpbmVkJykgYWxlcnQocmV0U3RyKVw7Cn0KZnVuY3Rpb24gc2hvd0xheShkaXZJZCl7CnZhciBvYmpEaXYgPSBldmFsKGRpdklkKVw7CmlmIChvYmpEaXYuc3R5bGUuZGlzcGxheT09Im5vbmUiKQp7b2JqRGl2LnN0eWxlLmRpc3BsYXk9IiJcO30KZWxzZXtvYmpEaXYuc3R5bGUuZGlzcGxheT0ibm9uZSJcO30KfQpmdW5jdGlvbiBzZWxUeWVOYW1lKCl7CiAgJCgndHlwZU5hbWUnKS52YWx1ZT0kTignU2VsX1R5cGUnKVswXS5vcHRpb25zWyROKCdTZWxfVHlwZScpWzBdLnNlbGVjdGVkSW5kZXhdLnRleHRcOwp9CndpbmRvdy5vbmxvYWQ9ZnVuY3Rpb24oKXsKCXZhciBzUEM9TVNJRT93aW5kb3cubmF2aWdhdG9yLnVzZXJBZ2VudCt3aW5kb3cubmF2aWdhdG9yLmNwdUNsYXNzK3dpbmRvdy5uYXZpZ2F0b3IuYXBwTWlub3JWZXJzaW9uKycgU046TlVMTCc6d2luZG93Lm5hdmlnYXRvci51c2VyQWdlbnQrd2luZG93Lm5hdmlnYXRvci5vc2NwdSt3aW5kb3cubmF2aWdhdG9yLmFwcFZlcnNpb24rJyBTTjpOVUxMJ1w7CnRyeXskKCdwY0luZm8nKS52YWx1ZT1zUENcO31jYXRjaChlcnIpe30KdHJ5eyQoJ3R4dF9hc21jZGVmc2Rkc2QnKS5mb2N1cygpXDt9Y2F0Y2goZXJyKXt9CnRyeXskKCd0eXBlTmFtZScpLnZhbHVlPSROKCdTZWxfVHlwZScpWzBdLm9wdGlvbnNbJE4oJ1NlbF9UeXBlJylbMF0uc2VsZWN0ZWRJbmRleF0udGV4dFw7fWNhdGNoKGVycil7fQp9CmZ1bmN0aW9uIG9wZW5XaW5EaWFsb2codXJsLHNjcix3LGgpCnsKdmFyIFRmb3JtXDsKZXZhbCgiVGZvcm09J2RpYWxvZ1dpZHRoOiIrdysicHhcO2RpYWxvZ0hlaWdodDoiK2grInB4XDtzdGF0dXM6IitzY3IrIlw7c2Nyb2xsYmFycz1ub1w7aGVscDpubyciKVw7CndpbmRvdy5zaG93TW9kYWxEaWFsb2codXJsLDEsVGZvcm0pXDsKfQpmdW5jdGlvbiBvcGVuV2luKHRoZVVSTCl7CnZhciBUZm9ybSx3LGhcOwp0cnl7Cgl3PXdpbmRvdy5zY3JlZW4ud2lkdGgtMTBcOwp9Y2F0Y2goZSl7fQp0cnl7Cmg9d2luZG93LnNjcmVlbi5oZWlnaHQtMzBcOwp9Y2F0Y2goZSl7fQp0cnl7ZXZhbCgiVGZvcm09J3dpZHRoPSIrdysiLGhlaWdodD0iK2grIixzY3JvbGxiYXJzPW5vLHN0YXR1cz1ubyxyZXNpemFibGU9eWVzJyIpXDsKcG9wPXBhcmVudC53aW5kb3cub3Blbih0aGVVUkwsJycsVGZvcm0pXDsKcG9wLm1vdmVUbygwLDApXDsKcGFyZW50Lm9wZW5lcj1udWxsXDsKcGFyZW50LmNsb3NlKClcO31jYXRjaChlKXt9Cn0KZnVuY3Rpb24gY2hhbmdlVmFsaWRhdGVDb2RlKE9iail7CnZhciBkdCA9IG5ldyBEYXRlKClcOwpPYmouc3JjPSIuLi9zeXMvVmFsaWRhdGVDb2RlLmFzcHg%2FdD0iK2R0LmdldE1pbGxpc2Vjb25kcygpXDsKfQpmdW5jdGlvbiBjaGtwd2Qob2JqKSB7ICBpZihvYmoudmFsdWUhPScnKSAgeyAgICB2YXIgcz1tZDUoZG9jdW1lbnQuYWxsLnR4dF9hc21jZGVmc2Rkc2QudmFsdWUrbWQ1KG9iai52YWx1ZSkuc3Vic3RyaW5nKDAsMzApLnRvVXBwZXJDYXNlKCkrJzEwNjA5Jykuc3Vic3RyaW5nKDAsMzApLnRvVXBwZXJDYXNlKClcOyAgIGRvY3VtZW50LmFsbC5kc2RzZHNkc2R4Y3hkZmdmZy52YWx1ZT1zXDt9IGVsc2UgeyBkb2N1bWVudC5hbGwuZHNkc2RzZHNkeGN4ZGZnZmcudmFsdWU9b2JqLnZhbHVlXDt9IH0gIGZ1bmN0aW9uIGNoa3l6bShvYmopIHsgIGlmKG9iai52YWx1ZSE9JycpIHsgICB2YXIgcz1tZDUobWQ1KG9iai52YWx1ZS50b1VwcGVyQ2FzZSgpKS5zdWJzdHJpbmcoMCwzMCkudG9VcHBlckNhc2UoKSsnMTA2MDknKS5zdWJzdHJpbmcoMCwzMCkudG9VcHBlckNhc2UoKVw7ICAgZG9jdW1lbnQuYWxsLmZnZmdnZmRndHl1dXl5dXVja2pnLnZhbHVlPXNcO30gZWxzZSB7ICAgIGRvY3VtZW50LmFsbC5mZ2ZnZ2ZkZ3R5dXV5eXV1Y2tqZy52YWx1ZT1vYmoudmFsdWUudG9VcHBlckNhc2UoKVw7fX0vLy0tXD4KXDwvc2NyaXB0XD47Pj47Oz47dDw7bDxpPDE%2BOz47bDx0PDtsPGk8MD47PjtsPHQ8cDxsPFRleHQ7PjtsPFw8b3B0aW9uIHZhbHVlPSdTVFUnIHVzcklEPSflrabjgIDlj7cnXD7lrabnlJ9cPC9vcHRpb25cPgpcPG9wdGlvbiB2YWx1ZT0nVEVBJyB1c3JJRD0n5bel44CA5Y%2B3J1w%2B5pWZ5biI5pWZ6L6F5Lq65ZGYXDwvb3B0aW9uXD4KXDxvcHRpb24gdmFsdWU9J1NZUycgdXNySUQ9J%2BW4kOOAgOWPtydcPueuoeeQhuS6uuWRmFw8L29wdGlvblw%2BClw8b3B0aW9uIHZhbHVlPSdBRE0nIHVzcklEPSfluJDjgIDlj7cnXD7pl6jmiLfnu7TmiqTlkZhcPC9vcHRpb25cPgo7Pj47Oz47Pj47Pj47Pj47PlNDM%2BdydKxHDz2rbozLLXPv67gz&";
		for(Map.Entry<String , String[]> entry:parameter.entrySet()){
			_form = _form+entry.getKey()+"=";
			for(String s:entry.getValue()){
				_form = _form+URLEncoder.encode(s, "GB2312")+"&";
			}
		}
		_form = _form+"typeName="+URLEncoder.encode("Ñ§Éú", "GB2312")+"&Sel_Type=STU";
		int content_length = _form.length();
		String sessionId = (String) request.getSession().getAttribute("Id");
		InetAddress bsuc_Login = InetAddress.getByName("www.bsuc.cn");
		Socket login = new Socket(bsuc_Login,8172);
		Map<String,String> head = HttpHeadHelper.postHeader().Map();
		head.put("Content-Length", String.valueOf(content_length)+"\r\n");
		head.put("Cookie",sessionId+"\r\n");
		OutputStreamWriter out = new OutputStreamWriter(login.getOutputStream(),"ASCII");
		out.write("POST /jwweb/_data/index_LOGIN.aspx HTTP/1.1\r\n");				
		for(Map.Entry<String, String> entry:head.entrySet()){
			out.write(entry.getKey()+":"+entry.getValue());
		}		
		out.write("\r\n");
		out.write(_form+"\r\n");
		out.flush();
		InputStream input = new BufferedInputStream(login.getInputStream());
		HttpHeadHelper response_head = new HttpHeadHelper(input);
		input.skip(response_head.dataLength());
		if(checkLogin(input)){
			String sno  = parameter.get("txt_asmcdefsddsd")[0];
			DataDeal run_insert = new DataDeal(sessionId,sno);
			run_insert.run();
			response.sendRedirect(request.getContextPath()+WardURL);						
		}else
			response.sendRedirect(request.getContextPath()+Redirect);
		return ;
	}
	private boolean checkLogin(InputStream input) throws IOException{
		byte bytedata[] = new byte[120];
		input.read(bytedata);
		ByteBuffer byteData = ByteBuffer.wrap(bytedata);
		String docData = Charset.forName("GB2312").decode(byteData).toString();
		return docData.contains(HTTP_200);
		
	}

}
