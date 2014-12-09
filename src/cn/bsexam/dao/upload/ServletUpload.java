package cn.bsexam.dao.upload;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.bsexam.dao.image.ImageCheck;

//import com.jspsmart.upload.SmartUpload;

public class ServletUpload extends HttpServlet {

	private ServletConfig config;

	/**
	 * Init the servlet
	 */
	final public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

	final public ServletConfig getServletConfig() {
		return config;
	}

	/**
	 * Handles GET requests
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<BODY BGCOLOR='white'>");
		out.println("<H1>jspSmartUpload : Servlet Sample</H1>");
		out.println("<HR><BR>");
		out.println("The method of the HTML form must be POST.");
		out.println("</BODY>");
		out.println("</HTML>");
	}

	/**
	 * Handles POST requests
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<BODY BGCOLOR='white'>");
		out.println("<H1>jspSmartUpload : Servlet Sample</H1>");
		out.println("<HR>");

		// Variables
		int count = 0;
		SmartUpload mySmartUpload = new SmartUpload();

		try {
			// Initialization
			mySmartUpload.initialize(config.getServletContext(), request, response);

			// Upload
			mySmartUpload.upload();

			// Save the file with the original name
			// in a virtual path of the web server
			SmartFiles files = mySmartUpload.getFiles();
			SmartFile file = files.getFile(0);
			file.setFileName("123456.jpg");
			boolean flag[] = ImageCheck.imageCheck(file, file.getImage());
			count = mySmartUpload.save(null);
			for(int i=0;i<flag.length;i++){
				out.println("<p>");
				out.println(flag[i]);
				out.println("</p>");
			}
			// Display the result
			out.println(count + " file uploaded.");

		} catch (Exception e) {
			out.println("Unable to upload the file.<br>");
			out.println("Error : " + e.toString());
		}
		
		out.println("</BODY>");
		out.println("</HTML>");
	}

	/**
	 * Destroy the servlet
	 */
	public void destroy() {
	}

}
