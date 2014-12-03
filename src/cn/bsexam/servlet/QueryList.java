package cn.bsexam.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;

import cn.bsexam.vo.Department;
import cn.bsexam.vo.ClassView;
import cn.bsexam.vo.ShowStu;
import cn.bsexam.dao.action.ShowTool;
/**
 * Servlet implementation class QueryList
 */
public class QueryList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryList() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取当前数据库中系别信息
		List<Department> dlist  = ShowTool.getDList();
		response.setContentType("text/html;charset=GB18030");
		PrintWriter out = response.getWriter();
		//获取当前用户的原系别
		String depat = ((ShowStu)request.getSession().getAttribute("stu")).getCdepat() ;
		//开始向输出流输入系别信息
		out.println("<option value=\"NULL\">"+"---请选择系别---"+"</option>");
		for(int i = 0;i<dlist.size();i++){
			String cdepat = dlist.get(i).getCdepat();
			String dname = dlist.get(i).getDepatname();
			boolean flag = dname.equals(depat);
			if(flag)
				out.println("<option value=\""+cdepat+"\" selected>"+dname+"</option>");
			else
				out.println("<option value=\""+cdepat+"\" >"+dname+"</option>");
		}
		out.close();
	}
		
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cdepat = request.getParameter("dapartment");
//		cdepat = URLDecoder.decode(cdepat, "UTF-8");
		List<ClassView> clist = ShowTool.getClist(cdepat);
		response.setContentType("text/html;charset=GB18030");
		PrintWriter out = response.getWriter();
		String pre_cname = ((ShowStu)request.getSession().getAttribute("stu")).getCname() ;
		out.println("<option value=\"NULL\">"+"---请选择班级---"+"</option>");
		for(int i = 0;i<clist.size();i++){
			String depat = clist.get(i).getCdepat();
			String cname = clist.get(i).getCname();
			boolean flag = cname.equals(pre_cname);
			if(flag)
				out.println("<option value=\""+cname+"\" selected>"+cname+"</option>");
			else
				out.println("<option value=\""+cname+"\" >"+cname+"</option>");
		}
		out.close();
	}

}
