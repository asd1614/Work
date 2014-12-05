package cn.bsexam.dao.impl;
import cn.bsexam.vo.IdCard;
import cn.bsexam.dao.interf.IIdcard;
import cn.bsexam.dbc.*;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class IdCardManage implements IIdcard{
	private String SQL_VIEW = 
			"SELECT idno, idname FROM bsexam.idcard ORDER BY dno ASC;";
	private String SQL_INSERT =
			"INSERT INTO bsexam.idcard (idno,idname) VALUES (?,?);";
	private String SQL_INSERT_L =
			"INSERT INTO bsexam.idcard (idno,idname) VALUES (?,?) ";
	private String SQL_UPDATE = 
			"UPDATE idcard set idno = ?, idname = ? WHERE idno = ? ;";
	private String SQL_DELETE = 
			"DELETE FROM idcard WHERE idno = ? ;";	
	private Connection conn = null;
	public void setConnection(Connection conn){
		this.conn = conn;
	}
	public List<IdCard> viewList(){
		
		PreparedStatement pstat;
		ResultSet re;
		List<IdCard> list = new ArrayList<IdCard>();
		try {
			pstat = conn.prepareStatement(SQL_VIEW);
			re = pstat.executeQuery();
			while(re.next()){
				IdCard e = new IdCard();
				e.setIdno(re.getInt(1));
				e.setIdname(re.getString(2));
				list.add(e);
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
		return list;		
	}
	
	public boolean insertOne(IdCard e) {
		// TODO �Զ����ɵķ������
		
		PreparedStatement pstat;
		ResultSet re;
		boolean flag = false;
		if(e==null)
			return flag;
		try {
			pstat = conn.prepareStatement(SQL_INSERT);
			pstat.setInt(1, e.getIdno());
			pstat.setString(2, e.getIdname());
			if(pstat.executeUpdate()==1)
				flag = true;
		} catch (SQLException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		return flag;
	}

	public boolean insertList(List<IdCard> list) {
		// TODO �Զ����ɵķ������
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
					pstat.setInt(j++, list.get(i).getIdno());
					pstat.setString(j++, list.get(i).getIdname());
					}
				if(pstat.executeUpdate()==size){
					flag = true;
				}
			} 
		}catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			}		
		return flag;
	}

	public boolean updateOne(IdCard e,int idno) {
		// TODO �Զ����ɵķ������
		
		PreparedStatement pstat;
		ResultSet re;
		boolean flag = false;
		if(e==null)
			return flag;
		try {
			pstat = conn.prepareStatement(SQL_UPDATE);
			pstat.setInt(1, e.getIdno());
			pstat.setString(2, e.getIdname());
			pstat.setInt(3, idno);
			if(pstat.executeUpdate()==1)
				flag = true;
		} catch (SQLException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		return flag;
	}
	
	public boolean deleteOne(int idno) {
		// TODO �Զ����ɵķ������
		
		PreparedStatement pstat;
		ResultSet re;
		boolean flag = false;
		try {
			pstat = conn.prepareStatement(SQL_DELETE);
			pstat.setInt(1, idno);
			if(pstat.executeUpdate()==1)
				flag = true;
		} catch (SQLException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		return flag;
	}	
}
