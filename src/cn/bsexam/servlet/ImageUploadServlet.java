package cn.bsexam.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bsexam.dao.upload.*;
import cn.bsexam.vo.ShowStu;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
//		ShowStu stu = (ShowStu) session.getAttribute("stu");
		response.setContentType("text/html;charset=GB18030");
		PrintWriter out = response.getWriter();
		SmartUpload dao_upload = new SmartUpload();
		try{
			dao_upload.initialize(session.getServletContext(), request, response);
			dao_upload.upload();
			SmartFiles files = dao_upload.getFiles();
			SmartFile file = files.getFile(0);
//			String filename = "images/"+stu.getCdepat()+stu.getSno()+file.getFileExt();
//			file.setFileName(filename);
			dao_upload.save(null);
			out.print("上传成功");
		}catch(Exception e){
			out.println("上传失败");
		}
		out.close();
	}

}
