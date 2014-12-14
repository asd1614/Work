package cn.bsexam.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bsexam.dao.image.AuthCode;

/**
 * Servlet implementation class AuthCodeServlet
 */
public class AuthCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String authCode = AuthCode.getAuthCode();		
		request.getSession().setAttribute("authCode", authCode);	//����֤�뱣�浽session�У������Ժ���֤
	    
		try {
			//����ͼƬ
			ImageIO.write(AuthCode.getAuthImg(authCode), "JPEG", response.getOutputStream());
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=GB18030");
		PrintWriter out = response.getWriter();
		String authCode = (String) request.getSession().getAttribute("authCode");
		String str = request.getParameter("authCode");
		if(authCode.equals(str)){
			out.print("��ȷ");
			out.close();
		}else{
			out.print("����");
			out.close();
		}
	}

}
