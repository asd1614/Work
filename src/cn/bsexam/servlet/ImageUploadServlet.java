package cn.bsexam.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bsexam.dao.image.ImageCheck;
import cn.bsexam.dao.upload.*;
import cn.bsexam.vo.ShowStu;
import cn.bsexam.vo.Student;
import cn.bsexam.dao.action.StudentAction;

/**
 * Servlet implementation class ImageUploadServlet
 */
public class ImageUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageUploadServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		ShowStu stu = (ShowStu) session.getAttribute("stu");
		response.setContentType("text/html;charset=GB18030");
		PrintWriter out = response.getWriter();
		SmartUpload dao_upload = new SmartUpload();
		try{
			dao_upload.initialize(session.getServletContext(), request, response);
			dao_upload.upload();
			SmartFiles files = dao_upload.getFiles();
			SmartFile file = files.getFile(0);
			//���ͼƬ�Ƿ�����ϴ�Ҫ��
			//�жϽ����¼����:0-type,1-size,2-width,3-height,4-H,5-S,6-B
			boolean flag[] = ImageCheck.imageCheck(file, file.getImage());
			if(!flag[0]){
				out.print("����JPG/JPEG");
				out.close();
				return ;
			}
			if(!flag[1]){
				out.print("�ļ�����100KB");
				out.close();
				return ;
			}
			if(!flag[2]||!flag[3]){
				out.print("�ߴ糬��614*819");
				out.close();
				return ;
			}
			if(!flag[4]||!flag[5]||!flag[6]){
				out.print("����ɫ��Ϊ��ɫ");
				out.close();
				return ;
			}
			//��ѧ������ͼƬ���������ϵ��������/images/�ļ�����
			String RealPath = request.getSession().getServletContext().getRealPath("/");
			File directory = new File(RealPath+"images"+File.separator+stu.getCdepat());
			if(!directory.exists())
				directory.mkdir();
			String filename = File.separator+"images"+File.separator+
					stu.getCdepat()+File.separator+stu.getSno()+"."+file.getFileExt();
			file.setFileName(filename);
			dao_upload.save(null);
			Student s = (Student) session.getAttribute("s");
			s = StudentAction.updateImage_f(s);	
			session.setAttribute("s", s);
			out.print("�ϴ��ɹ�");
		}catch(Exception e){
			out.println("�ϴ�ʧ��");
		}
		out.close();
	}

}
