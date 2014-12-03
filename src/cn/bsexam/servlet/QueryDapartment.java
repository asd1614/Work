package cn.bsexam.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import cn.bsexam.dbc.*;

public class QueryDapartment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnectionODBC dbc = null;
	Connection conn = null;
	public void init(ServletConfig config) throws ServletException {
		// TODO 自动生成的方法存根
		super.init(config);
		try {
			dbc = new DatabaseConnectionODBC();
			conn = dbc.getConnection();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sql = "SELECT cname FROM classmessage where cdepat=? order by cname ASC;";		
		PreparedStatement pstat = null;
		ResultSet rs = null;
		ArrayList<String> arrayClass = new ArrayList<String>();
		try {
			pstat = conn.prepareStatement(sql);			
	        String dapartment = request.getParameter("cdepat");
	        dapartment = URLDecoder.decode(dapartment, "UTF-8");
			if(dapartment!=null){
				pstat.setString(1,dapartment);
			}else{
				pstat.setString(1,dapartment);
			}
			rs = pstat.executeQuery();
			while (rs.next()) {
				arrayClass.add(rs.getString(1));
			}
			String path = "/jsp/showstuClass.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
			request.setAttribute("arrayClass", arrayClass);
			requestDispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				pstat.close();				
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sql = "SELECT DISTINCT depatname FROM department ORDER BY depatname ASC;";		
		PreparedStatement pstat = null;
		ResultSet rs = null;
		ArrayList<String> array = new ArrayList<String>();
		try {
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while (rs.next()) {
				array.add(rs.getString(1));
			}
			String path = "/jsp/showDapartment.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
			request.setAttribute("array", array);
			requestDispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				pstat.close();				
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}
		
	}
}
