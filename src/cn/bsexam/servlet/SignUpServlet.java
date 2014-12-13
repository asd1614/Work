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
		//����������
		response.setContentType("text/html;charset=GB18030");
		PrintWriter out = response.getWriter();
		//��ȡ���û�Ҫ����������
		String eno = request.getParameter("eno");
		//enoΪ�� ������ʧ��
		if(eno.equals("")||eno==null){
			out.print("����ʧ�ܣ���ˢ������");
			out.close();
			return ;
		}
		//ȡ��Student ����ȡ���û��ĸ�������
		HttpSession session = request.getSession();
		Student s = (Student)session.getAttribute("s");
		//�жϸ��û��Ƿ��Ѿ������˸�����Ϣ�����û�У��жϷ��ش�����Ϣ
		if(!StudentCheck.isEmpty(s)){
			out.println("����������Ϣ������");
			out.close();
			return ;
		}
		//��ȡ�������͵ľ���ʱ��
		List<ExamType> list = (List<ExamType>)super.getServletContext().getAttribute("list_ex");
		String edate = list.get(0).getEdate().toString().substring(0,10);
		//��ѯ���û��Ƿ��Ѿ�����
		ExamAction dao_ex = new ExamAction();
		SE se = dao_ex.getSE(s.getSno(), edate);
		if(se.getSno()==null||se.getSno().equals("")){
			se.setSno(s.getSno());
			se.setEno(eno);
			se.setEdate(edate);
			if(dao_ex.insertSE(se)){
				out.println("�����ɹ�");
			}
		}else{
			if(!se.getEno().equals(eno)){
				se.setEno(eno);
				if(dao_ex.updateSE(se))
					out.println("�޸ĳɹ�");
			}else{
				if(dao_ex.updateSE(se))
					out.println("�޸ĳɹ�");
			}
		}
		out.close();
	}

}
