package cn.bsexam.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bsexam.dao.image.AuthCode;
import cn.bsexam.vo.ShowStu;
import cn.bsexam.vo.Student;

/**
 * Servlet implementation class ImageSendServlet
 */
public class ImageSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageSendServlet() {
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
		HttpSession session = request.getSession();
		Student s = (Student)session.getAttribute("s");
		ShowStu stu = (ShowStu)session.getAttribute("stu");
		String RealPath = session.getServletContext().getRealPath("/");
		if(s.getImage_f()){
			String path = RealPath+"images"+File.separator+stu.getCdepat()+File.separator+stu.getSno();
			File file = new File(path+".jpg");
			if(!file.exists()){
				file = new File(path+"jpeg");
			}
			if(!file.exists()){
				file = new File(RealPath+"images"+File.separator+"img.jpg");
			}
			BufferedImage image = ImageIO.read(file);
			try {
				//·¢ËÍÍ¼Æ¬
				ImageIO.write(image, "JPEG", response.getOutputStream());
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
