package cn.bsexam.servlet;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bsexam.util.*;
/**
 * Servlet implementation class AuthCodeServlet
 */
public class ValidateCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//    	http","www.bsuc.cn",8172,"/jwweb/sys/ValidateCode.aspx
    	String sessionId = (String)request.getSession().getAttribute("Id");
    	try {
    		InetAddress jwweb = InetAddress.getByName("www.bsuc.cn");
    		Socket vali = new Socket(jwweb,8172);
    		Map<String,String> head = HttpHeadHelper.getHeader().Map();
    		if(sessionId!=null&&!sessionId.equals(""))
    			head.put("Cookie",sessionId+"\r\n");
    		OutputStreamWriter out = new OutputStreamWriter(vali.getOutputStream(),"ASCII");
    		out.write("GET /jwweb/sys/ValidateCode.aspx HTTP/1.1\r\n");				
    		for(Map.Entry<String, String> entry:head.entrySet()){
    			out.write(entry.getKey()+":"+entry.getValue());
    		}    		
    		out.write("\r\n");
    		out.flush();
    		InputStream input = new BufferedInputStream(vali.getInputStream());
    		HttpHeadHelper headhelper = new HttpHeadHelper(input);
    		long length = headhelper.dataLength();
    		input.skip(length);
    		BufferedImage image = ImageIO.read(input);
    		OutputStream image_out = response.getOutputStream();
    		ImageIO.write(image, "JPEG",image_out );
    		image_out.flush();
    		image_out.close();
    		input.close();
    		vali.close();
    		if(sessionId==null||sessionId.equals("")){
    			sessionId = headhelper.get("Set-Cookie");
        		sessionId = sessionId.substring(0,sessionId.indexOf(";"));
        		request.getSession().setAttribute("Id", sessionId);
    		}
    		return ;
    	} catch (FileNotFoundException e) {
    		// TODO 自动生成的 catch 块
    		e.printStackTrace();
    	}  catch (MalformedURLException e) {
    		// TODO 自动生成的 catch 块
    		e.printStackTrace();
    	} catch (IOException e) {
    		// TODO 自动生成的 catch 块
    		e.printStackTrace();
    	}
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}