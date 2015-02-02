package cn.bsexam.servlet;

import cn.bsexam.util.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NewStudentSerlvet
 */
public class NewStudentSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String Wait = "/jsp/loginProgess.jsp";
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
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		request.setCharacterEncoding("GB2312");
		Map<String,String[]> parameter = new ConcurrentHashMap(request.getParameterMap());
		//获取教务管理系统的ASP-sessionId
		String sessionId = (String) request.getSession().getAttribute("Id");
		String sno  = parameter.get("txt_asmcdefsddsd")[0];
		DataDeal run_insert = new DataDeal(parameter,sessionId,sno);
		Thread thread = new Thread(run_insert);
		thread.start();
		request.getSession().setAttribute("deal", run_insert);
		request.getSession().setAttribute("sno", sno);
		request.getSession().setAttribute("sessionId", request.getSession().getId());		
		response.sendRedirect(request.getContextPath()+Wait);
	}

}
