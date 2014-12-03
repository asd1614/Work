package cn.bsexam.dao.impl;
import cn.bsexam.vo.SE;
import cn.bsexam.dao.interf.ISE;
import cn.bsexam.dbc.*;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class SEManage implements ISE{
	private String SQL_VIEW_L = 
			"SELECT sno, edate,eno,egrade FROM bsexam.se ;";
	private String SQL_VIEW = 
			"SELECT sno, edate,eno,egrade FROM bsexam.se WHERE sno= ? ;";
	private String SQL_INSERT =
			"INSERT INTO bsexam.se (sno,edate,eno,egrade) VALUES (?,?,?,?);";
	private String SQL_INSERT_L =
			"INSERT INTO bsexam.se (sno,edate,eno,egrade) VALUES (?,?,?,?) ";
	private String SQL_UPDATE = 
			"UPDATE se set sno = ?, edate = ?, eno=?, egrade=?  WHERE sno = ? ;";
	private String SQL_DELETE = 
			"DELETE FROM se WHERE sno = ? ;";
	private Connection conn = null;
	public void setConnection(Connection conn){
		this.conn = conn;
	}
	public List<SE> viewList(){
		
		PreparedStatement pstat;
		ResultSet re;
		List<SE> list = new ArrayList<SE>();
		try {
			pstat = conn.prepareStatement(SQL_VIEW_L);
			re = pstat.executeQuery();
			while(re.next()){
				SE e = new SE();
				e.setSno(re.getString(1));
				e.setEdate(re.getTimestamp(2));
				e.setEno(re.getInt(3));
				e.setEgrade(re.getDouble(4));
				list.add(e);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return list;		
	}
	public SE viewOne(String sno){
		
		PreparedStatement pstat;
		ResultSet re;
		SE e = new SE();
		try {
			pstat = conn.prepareStatement(SQL_VIEW);
			pstat.setString(1, sno);
			re = pstat.executeQuery();
			re.next();
			e.setSno(re.getString(1));
			e.setEdate(re.getTimestamp(2));
			e.setEno(re.getInt(3));
			e.setEgrade(re.getDouble(4));
			
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}		
		return e;			
	}
	public boolean insertOne(SE e) {
		// TODO 自动生成的方法存根
		
		PreparedStatement pstat;
		ResultSet re;
		boolean flag = false;
		if(e==null)
			return flag;
		try {
			pstat = conn.prepareStatement(SQL_INSERT);
			pstat.setString(1, e.getSno());
			pstat.setTimestamp(2, e.getEdate());
			pstat.setInt(3, e.getEno());
			pstat.setDouble(4, e.getEgrade());
			if(pstat.executeUpdate()==1)
				flag = true;
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		return flag;
	}

	public boolean insertList(List<SE> list) {
		// TODO 自动生成的方法存根
		String SQL_INSERT_L = this.SQL_INSERT_L;
		String aft =", (?,?,?,?) ";
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
					pstat.setString(j++, list.get(i).getSno());					
					pstat.setInt(j++, list.get(i).getEno());
					pstat.setDouble(j++, list.get(i).getEgrade());
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

	public boolean updateOne(SE e,String sno) {
		// TODO 自动生成的方法存根
		
		PreparedStatement pstat;
		ResultSet re;
		boolean flag = false;
		if(e==null)
			return flag;
		try {
			pstat = conn.prepareStatement(SQL_UPDATE);
			pstat.setString(1, e.getSno());
			pstat.setTimestamp(2, e.getEdate());
			pstat.setInt(3, e.getEno());
			pstat.setDouble(4, e.getEgrade());
			pstat.setString(5, sno);
			if(pstat.executeUpdate()==1)
				flag = true;
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		return flag;
	}
	
	public boolean deleteOne(String sno) {
		// TODO 自动生成的方法存根
		
		PreparedStatement pstat;
		ResultSet re;
		boolean flag = false;
		try {
			pstat = conn.prepareStatement(SQL_DELETE);
			pstat.setString(1, sno);
			if(pstat.executeUpdate()==1)
				flag = true;
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		return flag;
	}	
}
