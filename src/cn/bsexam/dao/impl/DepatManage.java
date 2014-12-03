package cn.bsexam.dao.impl;
import cn.bsexam.vo.Department;
import cn.bsexam.dao.interf.IDepat;
import cn.bsexam.dbc.*;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DepatManage implements IDepat{
	private String SQL_VIEW = 
			"SELECT cdepat, depatname FROM department ORDER BY cdepat ASC;";
	private String SQL_INSERT =
			"INSERT INTO department (cdepat,depatname) VALUES (?,?);";
	private String SQL_INSERT_L =
			"INSERT INTO department (cdepat,depatname) VALUES (?,?) ";
	private String SQL_UPDATE = 
			"UPDATE department set cdepat = ?, depatname = ? WHERE cdepat = ? ;";
	private String SQL_DELETE = 
			"DELETE FROM department WHERE depatname = ? ;";
	private Connection conn = null;
	public void setConnection(Connection conn){
		this.conn = conn;
	}
	public List<Department> viewList(){
		
		PreparedStatement pstat;
		ResultSet re;
		List<Department> list = new ArrayList<Department>();
		try {
			pstat = conn.prepareStatement(SQL_VIEW);
			re = pstat.executeQuery();
			while(re.next()){
				Department depat = new Department();
				depat.setCdepat(re.getString(1));
				depat.setDepatname(re.getString(2));
				list.add(depat);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return list;		
	}
	
	public boolean insertOne(Department e) {
		// TODO 自动生成的方法存根
		
		PreparedStatement pstat;
		ResultSet re;
		boolean flag = false;
		if(e==null)
			return flag;
		try {
			pstat = conn.prepareStatement(SQL_INSERT);
			pstat.setString(1, e.getCdepat());
			pstat.setString(2, e.getDepatname());
			if(pstat.executeUpdate()==1)
				flag = true;
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		return flag;
	}

	public boolean insertList(List<Department> list) {
		// TODO 自动生成的方法存根
		String SQL_INSERT_L = this.SQL_INSERT_L;
		String aft =",(?,?)";
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
					pstat.setString(j++, list.get(i).getCdepat());
					pstat.setString(j++, list.get(i).getDepatname());
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

	public boolean updateOne(Department e,String cdepat) {
		// TODO 自动生成的方法存根
		
		PreparedStatement pstat;
		ResultSet re;
		boolean flag = false;
		if(e==null)
			return flag;
		try {
			pstat = conn.prepareStatement(SQL_UPDATE);
			pstat.setString(1, e.getCdepat());
			pstat.setString(2, e.getDepatname());
			pstat.setString(3, cdepat);
			if(pstat.executeUpdate()==1)
				flag = true;
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		return flag;
	}
	
	public boolean deleteOne(String depatname) {
		// TODO 自动生成的方法存根
		
		PreparedStatement pstat;
		ResultSet re;
		boolean flag = false;
		if(depatname==null)
			return flag;
		try {
			pstat = conn.prepareStatement(SQL_DELETE);
			pstat.setString(1, depatname);
			if(pstat.executeUpdate()==1)
				flag = true;
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		return flag;
	}
	
	public boolean deleteAll() {
		// TODO 自动生成的方法存根
		
		return false;
	}
	
}
