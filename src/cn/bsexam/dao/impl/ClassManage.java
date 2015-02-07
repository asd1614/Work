package cn.bsexam.dao.impl;
import cn.bsexam.vo.ClassMessage;
import cn.bsexam.vo.ClassView;
import cn.bsexam.dao.interf.IClass;
import cn.bsexam.dbc.*;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ClassManage implements IClass {
	private Connection conn = null;
	private String SQL_VIEW_U = 
			"SELECT cdepat,cname FROM classmessage WHERE cdepat=? ;";
	private String SQL_VIEW = 
			"SELECT cname,syear,clength,dno,cspecial,cdepat FROM classmessage "
			+ " WHERE cdepat in (SELECT cdepat FROM department WHERE depatname = ?);";
	private String SQL_INSERT =
			"INSERT INTO bsexam.classmessage (cname,syear,clength,dno,cspecial,cdepat) "
			+ " VALUES (?,?,?,?,?,?);";
	private String SQL_INSERT_L =
			"INSERT INTO bsexam.classmessage (cname,syear,clength,dno,cspecial,cdepat) "
					+ " VALUES (?,?,?,?,?,?)";
	private String SQL_UPDATE = 
			"UPDATE bsexam.classmessage "
			+ " SET cname=?,syear=?,clength=?,dno=?,cspecial=?,cdepat=?  "
			+ " WHERE cname=? ;";
	private String SQL_DELETE = 
			"DELETE FROM classmessage WHERE cname = ? ;";
	public void setConnection(Connection conn){
		this.conn = conn;
	}
	public List<ClassMessage> viewList(String depat){		
		PreparedStatement pstat;
		ResultSet re;
		List<ClassMessage> list = new ArrayList<ClassMessage>();
		try {
			pstat = conn.prepareStatement(SQL_VIEW);
			pstat.setString(1, depat);
			re = pstat.executeQuery();
			while(re.next()){
				ClassMessage e = new ClassMessage();
				e.setCname(re.getString(1));
				e.setSyear(re.getInt(2));
				e.setClength(re.getInt(3));
				e.setDno(re.getInt(4));
				e.setCspecial(re.getString(5));
				e.setCdepat(String.valueOf(re.getInt(6)));
				list.add(e);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return list;		
	}
	
	public boolean insertOne(ClassMessage e) {
		// TODO 自动生成的方法存根
		
		PreparedStatement pstat;
		ResultSet re;
		boolean flag = false;
		if(e==null)
			return flag;
		try {
			pstat = conn.prepareStatement(SQL_INSERT);
			pstat.setString(1, e.getCname());
			pstat.setInt(2, e.getSyear());
			pstat.setInt(3, e.getClength());
			pstat.setInt(4, e.getDno());
			pstat.setString(5, e.getCspecial());
			pstat.setInt(6, Integer.parseInt(e.getCdepat()));
			if(pstat.executeUpdate()==1)
				flag = true;
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		return flag;
	}

	public boolean insertList(List<ClassMessage> list) {
		// TODO 自动生成的方法存根
		String SQL_INSERT_L = this.SQL_INSERT_L;
		String aft =",(?,?,?,?,?,?)";
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
					pstat.setString(j++, list.get(i).getCname());
					pstat.setInt(j++, list.get(i).getSyear());
					pstat.setInt(j++, list.get(i).getClength());
					pstat.setInt(j++, list.get(i).getDno());
					pstat.setString(j++, list.get(i).getCspecial());
					pstat.setInt(j++, Integer.parseInt(list.get(i).getCdepat()));
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

	public boolean updateOne(ClassMessage e,String cname) {
		// TODO 自动生成的方法存根
		
		PreparedStatement pstat;
		ResultSet re;
		boolean flag = false;
		if(e==null)
			return flag;
		try {
			pstat = conn.prepareStatement(SQL_UPDATE);
			pstat.setString(1, e.getCname());
			pstat.setInt(2, e.getSyear());
			pstat.setInt(3, e.getClength());
			pstat.setInt(4,e.getDno());
			pstat.setString(5, e.getCspecial());
			pstat.setInt(6, Integer.parseInt(e.getCdepat()));
			pstat.setString(7,cname);
			if(pstat.executeUpdate()==1)
				flag = true;
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		return flag;
	}
	
	public boolean deleteOne(String cname) {
		// TODO 自动生成的方法存根
		
		PreparedStatement pstat;
		ResultSet re;
		boolean flag = false;
		try {
			pstat = conn.prepareStatement(SQL_DELETE);
			pstat.setString(1, cname);
			if(pstat.executeUpdate()==1)
				flag = true;
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		return flag;
	}
	
	public List<ClassView> viewList_u(String cdepat) {
		// TODO Auto-generated method stub
		PreparedStatement pstat;
		ResultSet re;
		List<ClassView> list = new ArrayList<ClassView>();
		try {
			pstat = conn.prepareStatement(SQL_VIEW_U);
			pstat.setInt(1, Integer.parseInt(cdepat));
			re = pstat.executeQuery();
			while(re.next()){
				ClassView e = new ClassView();							
				e.setCdepat(re.getString(1));
				e.setCname(re.getString(2));	
				list.add(e);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return list;		
	}	
}
