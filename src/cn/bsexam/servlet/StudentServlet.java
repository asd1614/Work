package cn.bsexam.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.bsexam.dao.action.LoginAction;
import cn.bsexam.vo.*;
/**
 * Servlet implementation class StudentServlet
 */
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String WardURL = "/jsp/studentMain.jsp";
    private static String Redirect = "/index.html";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
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
		boolean flag =false;
		SUser suser = new SUser();
		String sno = request.getParameter("name");
		suser.setSno(sno);
		suser.setPassword(request.getParameter("pwd"));
		ShowStu stu = LoginAction.getShowStu(suser);
		//如果成功返回一个ShowStu对象，则认为用户验证正确
		if(stu!=null){
			Student s = LoginAction.getStudent(sno);	//提取学生简略信息	
			HttpSession session = request.getSession();
			session.setAttribute("s", s);
			session.setAttribute("stu", stu);
			session.setAttribute("sessionId",session.getId());
			flag = true;
		}
		if(flag==true){
			response.sendRedirect(request.getContextPath()+WardURL);
		}else
			;//response.sendRedirect(request.getContextPath()+Redirect);		
	}

}
