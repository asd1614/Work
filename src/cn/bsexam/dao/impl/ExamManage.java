package cn.bsexam.dao.impl;
import cn.bsexam.vo.ExamType;
import cn.bsexam.dao.interf.IExamType;
import cn.bsexam.dbc.*;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ExamManage implements IExamType{
	private String SQL_VIEW = 
			"SELECT eno, ename,edate FROM examtype ORDER BY eno ASC;";
	private String SQL_INSERT =
			"INSERT INTO examtype (eno,ename,edate) VALUES (?,?,?);";
	private String SQL_INSERT_L =
			"INSERT INTO examtype (eno,ename,edate) VALUES (?,?,?) ";
	private String SQL_UPDATE = 
			"UPDATE examtype set eno = ?, ename = ?, edate = ?  WHERE eno = ? ;";
	private String SQL_DELETE = 
			"DELETE FROM examtype WHERE eno = ? ;";
	private Connection conn = null;
	public void setConnection(Connection conn){
		this.conn = conn;
	}
	public List<ExamType> viewList(){
		
		PreparedStatement pstat;
		ResultSet re;
		List<ExamType> list = new ArrayList<ExamType>();
		try {
			pstat = conn.prepareStatement(SQL_VIEW);
			re = pstat.executeQuery();
			while(re.next()){
				ExamType e = new ExamType();
				e.setEno(re.getString(1));
				e.setEname(re.getString(2));
				e.setEdate(re.getTimestamp(3));
				list.add(e);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return list;		
	}
	
	public boolean insertOne(ExamType e) {
		// TODO 自动生成的方法存根
		
		PreparedStatement pstat;
		ResultSet re;
		boolean flag = false;
		if(e==null)
			return flag;
		try {
			pstat = conn.prepareStatement(SQL_INSERT);
			pstat.setString(1, e.getEno());
			pstat.setString(2, e.getEname());
			pstat.setTimestamp(3,e.getEdate());
			if(pstat.executeUpdate()==1)
				flag = true;
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		return flag;
	}

	public boolean insertList(List<ExamType> list) {
		// TODO 自动生成的方法存根
		String SQL_INSERT_L = this.SQL_INSERT_L;
		String aft =",(?,?,?)";
		int size = list.size();	
		for(int i=1;i<size;i++){
			SQL_INSERT_L = SQL_INSERT_L + aft;
		}
		SQL_INSERT_L = SQL_INSERT_L+ ";";
		PreparedStatement pstat ;
		boolean flag = false;
		try {
			pstat = conn.prepareStatement(SQL_INSERT_L);
			if(!list.isEmpty()){
				for(int i=0,j=1;i<size;i++){				
					pstat.setString(j++, list.get(i).getEno());
					pstat.setString(j++, list.get(i).getEname());
					pstat.setTimestamp(j++, list.get(i).getEdate());
					}
				if(pstat.executeUpdate()==size){
					flag = true;
				}
			} 
		}catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			}		
		return flag;
	}

	public boolean updateOne(ExamType e,int eno) {
		// TODO 自动生成的方法存根
		
		PreparedStatement pstat;
		ResultSet re;
		boolean flag = false;
		if(e==null)
			return flag;
		try {
			pstat = conn.prepareStatement(SQL_UPDATE);
			pstat.setString(1, e.getEno());
			pstat.setString(2, e.getEname());
			pstat.setTimestamp(3, e.getEdate());
			pstat.setInt(4, eno);
			if(pstat.executeUpdate()==1)
				flag = true;
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		return flag;
	}
	
	public boolean deleteOne(int eno) {
		// TODO 自动生成的方法存根
		
		PreparedStatement pstat;
		ResultSet re;
		boolean flag = false;
		try {
			pstat = conn.prepareStatement(SQL_DELETE);
			pstat.setInt(1, eno);
			if(pstat.executeUpdate()==1)
				flag = true;
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		return flag;
	}	
}
