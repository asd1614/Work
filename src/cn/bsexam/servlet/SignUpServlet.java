package cn.bsexam.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bsexam.check.StudentCheck;
import cn.bsexam.dao.action.ExamAction;
import cn.bsexam.vo.ExamType;
import cn.bsexam.vo.SE;
import cn.bsexam.vo.Student;

/**
 * Servlet implementation class ExamServlet
 */
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
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
		//解决输出乱码
		response.setContentType("text/html;charset=GB18030");
		PrintWriter out = response.getWriter();
		//获取该用户要报名的类型
		String eno = request.getParameter("eno");
		//eno为空 ，报名失败
		if(eno.equals("")||eno==null){
			out.print("报名失败，请刷新重试");
			out.close();
			return ;
		}
		//取得Student ，获取该用户的个人资料
		HttpSession session = request.getSession();
		Student s = (Student)session.getAttribute("s");
		//判断该用户是否已经完善了个人信息，如果没有，中断返回错误信息
		if(!StudentCheck.isEmpty(s)){
			out.println("个人资料信息不完整");
			out.close();
			return ;
		}
		//获取考试类型的具体时间
		List<ExamType> list = (List<ExamType>)super.getServletContext().getAttribute("list_ex");
		String edate = list.get(0).getEdate().toString().substring(0,10);
		//查询该用户是否已经报名
		ExamAction dao_ex = new ExamAction();
		SE se = dao_ex.getSE(s.getSno(), edate);
		if(se.getSno()==null||se.getSno().equals("")){
			se.setSno(s.getSno());
			se.setEno(eno);
			se.setEdate(edate);
			if(dao_ex.insertSE(se)){
				out.println("报名成功");
			}
		}else{
			if(!se.getEno().equals(eno)){
				se.setEno(eno);
				if(dao_ex.updateSE(se))
					out.println("修改成功");
			}else{
				if(dao_ex.updateSE(se))
					out.println("修改成功");
			}
		}
		out.close();
	}

}
