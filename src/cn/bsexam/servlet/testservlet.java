package cn.bsexam.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;

/**
 * Servlet implementation class testservlet
 */
public class testservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//RealPath = 
		//%Project Path%\Work\
		response.setContentType("text/html;charset=GB18030");
		PrintWriter out = response.getWriter();
		String path  = request.getParameter("PATH");
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		String contextpath = context.getRealPath("/");
		out.println("<p>"+path+"</p>");
		out.println("<p>"+contextpath+"</p>");
		out.println("<p>"+request.getServletPath()+"</p>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
