package cn.bsexam.dao.impl;
import cn.bsexam.vo.Degree;
import cn.bsexam.dao.interf.IDegree;
import cn.bsexam.dbc.*;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DegreeManage implements IDegree{
	private Connection conn = null;
	private String SQL_VIEW = 
			"SELECT dno, dname FROM bsexam.degree ORDER BY dno ASC;";
	private String SQL_INSERT =
			"INSERT INTO bsexam.degree (dno,dname) VALUES (?,?);";
	private String SQL_INSERT_L =
			"INSERT INTO bsexam.degree (dno,dname) VALUES (?,?) ";
	private String SQL_UPDATE = 
			"UPDATE degree set dno = ?, dname = ? WHERE dno = ? ;";
	private String SQL_DELETE = 
			"DELETE FROM degree WHERE dno = ? ;";
	public void setConnection(Connection conn){
		this.conn = conn;
	}
	public List<Degree> viewList(){
		
		PreparedStatement pstat;
		ResultSet re;
		List<Degree> list = new ArrayList<Degree>();
		try {
			pstat = conn.prepareStatement(SQL_VIEW);
			re = pstat.executeQuery();
			while(re.next()){
				Degree degree = new Degree();
				degree.setDno(re.getInt(1));
				degree.setDname(re.getString(2));
				list.add(degree);
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
				String dno = String.valueOf(re.getInt(1));
				String dname = re.getString(2);
				map.put(dname, dno);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return map;		
	}
	public boolean insertOne(Degree e) {
		// TODO 自动生成的方法存根
		
		PreparedStatement pstat;
		ResultSet re;
		boolean flag = false;
		if(e==null)
			return flag;
		try {
			pstat = conn.prepareStatement(SQL_INSERT);
			pstat.setInt(1, e.getDno());
			pstat.setString(2, e.getDname());
			if(pstat.executeUpdate()==1)
				flag = true;
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		return flag;
	}

	public boolean insertList(List<Degree> list) {
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
					pstat.setInt(j++, list.get(i).getDno());
					pstat.setString(j++, list.get(i).getDname());
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

	public boolean updateOne(Degree e,int dno) {
		// TODO 自动生成的方法存根
		
		PreparedStatement pstat;
		ResultSet re;
		boolean flag = false;
		if(e==null)
			return flag;
		try {
			pstat = conn.prepareStatement(SQL_UPDATE);
			pstat.setInt(1, e.getDno());
			pstat.setString(2, e.getDname());
			pstat.setInt(3, dno);
			if(pstat.executeUpdate()==1)
				flag = true;
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		return flag;
	}
	
	public boolean deleteOne(int dno) {
		// TODO 自动生成的方法存根
		
		PreparedStatement pstat;
		ResultSet re;
		boolean flag = false;
		try {
			pstat = conn.prepareStatement(SQL_DELETE);
			pstat.setInt(1, dno);
			if(pstat.executeUpdate()==1)
				flag = true;
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		return flag;
	}	
}
