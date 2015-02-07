package cn.bsexam.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bsexam.dao.action.PWAction;
import cn.bsexam.vo.SUser;
import cn.bsexam.vo.ShowStu;
import cn.bsexam.vo.SysUser;

/**
 * Servlet implementation class AlterPWServlet
 */
public class AlterPWServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterPWServlet() {
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
		String pre = request.getParameter("pass");
		String new_pass = request.getParameter("new_pass");
		String confirm = request.getParameter("confirm");
		if(new_pass.equals(confirm)){
			HttpSession session = request.getSession();
			String suser = (String)session.getAttribute("admin");
			SysUser u = new SysUser();
			u.setSuser(suser);
			u.setPassword(pre);
			if(PWAction.check(u)){
				u.setPassword(new_pass);
				if(PWAction.alter(u)){
					response.setContentType("text/html;charset=GB18030");
					PrintWriter out = response.getWriter();
					out.print("ÐÞ¸Ä³É¹¦");
					out.close();
					return ;
				}

			}
		}
		response.setContentType("text/html;charset=GB18030");
		PrintWriter out = response.getWriter();
		out.print("false");
		out.close();
		return ;	
		
	}

}
