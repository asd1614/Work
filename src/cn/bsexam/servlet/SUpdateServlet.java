package cn.bsexam.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bsexam.vo.ShowStu;
import cn.bsexam.vo.Student;
import cn.bsexam.dao.action.*;
import cn.bsexam.check.StudentCheck;

/**
 * Servlet implementation class SInsertServlet
 */
public class SUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SUpdateServlet() {
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
		Student s = StudentCheck.getStudent(request);
		response.setContentType("text/html;charset=GB18030");
		if(SUpdateAction.update(s)){
			ShowStu stu = SUpdateAction.refreshStu(s.getSno());
			HttpSession session = request.getSession();
			session.setAttribute("stu", stu);
			PrintWriter out = response.getWriter();
			out.println("保存成功");
			out.close();
		}else{
			PrintWriter out = response.getWriter();
			out.println("保存失败，请重新刷新");
			out.close();
		}
		
	}

}
