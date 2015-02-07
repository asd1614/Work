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
			"SELECT cdepat, depatname FROM department ORDER BY depatname ASC;";
	private String SQL_INSERT =
			"INSERT INTO department (depatname) VALUES (?);";
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
				String cdepat = String.valueOf(re.getInt(1));
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
	public Map<String,String> viewMap(){
		PreparedStatement pstat;
		ResultSet re;
		Map<String,String> map = new HashMap<String,String>();
		try {
			pstat = conn.prepareStatement(SQL_VIEW);
			re = pstat.executeQuery();
			while(re.next()){
				String cdepat = String.valueOf(re.getInt(1));
				String depatname = re.getString(2);
				map.put(depatname, cdepat);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return map;	
	}
	public boolean insertOne(String depatname) {
		// TODO 自动生成的方法存根
		
		PreparedStatement pstat;
		ResultSet re;
		boolean flag = false;
		if(depatname==null)
			return flag;
		try {
			pstat = conn.prepareStatement(SQL_INSERT);
			pstat.setString(1, depatname);
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
		return false;
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
			pstat.setInt(1, Integer.parseInt(e.getCdepat()));
			pstat.setString(2, e.getDepatname());
			pstat.setInt(3, Integer.parseInt(cdepat));
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
