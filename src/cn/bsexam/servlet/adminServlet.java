package cn.bsexam.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bsexam.dao.action.PWAction;
import cn.bsexam.vo.SysUser;

/**
 * Servlet implementation class adminServlet
 */
public class adminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminServlet() {
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
		// TODO Auto-generated method stub
		String authCode = (String)request.getSession().getAttribute("authCode"); 
		String auth = request.getParameter("txt_sdertfgsadscxcadsads");
		if(authCode.equals(auth)){
			String suser = request.getParameter("txt_asmcdefsddsd");
			String password = request.getParameter("txt_pewerwedsdfsdff");
			SysUser s = new SysUser();
			s.setSuser(suser);
			s.setPassword(password);
			boolean flag = PWAction.check(s);
			if(flag){
				response.sendRedirect(request.getContextPath()+"/jsp/admin.jsp");
				String sessionId = request.getSession().getId();
				request.getSession().setAttribute("sessionId", sessionId);
				request.getSession().setAttribute("admin", suser);
			}
		}else{
			response.sendRedirect(request.getContextPath()+"/index.html");
		}
		
	}

}
