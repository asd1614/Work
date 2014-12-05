package cn.bsexam.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.bsexam.dao.interf.IExcelFile;
import cn.bsexam.vo.ExcelFile;
import cn.bsexam.vo.ShowStu;

public class ExcelFileManage implements IExcelFile{
	private Connection conn =null;
	private String SQL_VIEW="SELECT * FROM bsexam.excelfile WHERE edate BETWEEN ? AND ?;";	
	private List<ExcelFile> list = null;
	public void setConnection(Connection conn){
		this.conn = conn;
	}
	public List<ExcelFile> exportList(String start,String end) {
		// TODO 自动生成的方法存根
		PreparedStatement pstat ;
		ResultSet rs;
		this.list = new ArrayList<ExcelFile>();
		try{
			pstat = conn.prepareStatement(SQL_VIEW);
			pstat.setString(1, start);
			pstat.setString(2, end);
			rs = pstat.executeQuery();
			while(rs.next()){
				ExcelFile e = new ExcelFile();
				e.setSname(rs.getString(1));
				e.setSsex(rs.getString(2));
				e.setSno(rs.getString(3));
				e.setIdno(rs.getInt(4));
				e.setSid(rs.getString(5));
				e.setDno(rs.getInt(6));
				e.setClength(rs.getInt(7));
				e.setXuezhi(rs.getInt(8));
				e.setSyear(rs.getInt(9));
				e.setDepatname(rs.getString(10));
				e.setCspecial(rs.getString(11));
				e.setCname(rs.getString(12));
				e.setEno(rs.getInt(13));
				e.setEdate(rs.getTimestamp(14));
				this.list.add(e);
			}
		}catch(SQLException e1){
			e1.printStackTrace();
		}
		return list;
	}
	
}
