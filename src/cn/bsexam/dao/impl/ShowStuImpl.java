package cn.bsexam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.bsexam.dao.interf.IShowStu;
import cn.bsexam.vo.ShowStu;

public class ShowStuImpl implements IShowStu {	
	private Connection conn =null;
	//����һ��ѧ����������ѧ�š��༶��ϵ�����ơ�������ʾ��ʾ��ļ���ѧ����Ϣ��
	private String SQL="SELECT * FROM showstu where sno=? ;";
	private ResultSet rs ;
	private ShowStu showStu = null;
	public void setConnection(Connection conn){
		this.conn = conn;
	}
	public ShowStu findShowStu(String SNO) {
		// TODO �Զ����ɵķ������				
		try {
			ShowStu showStu = new ShowStu();			
			PreparedStatement pstat = conn.prepareStatement(SQL);	
			pstat.setString(1, SNO);
			this.rs = pstat.executeQuery();
			if(rs.next()){				
				showStu.setSname(rs.getString(1));			
				showStu.setSno(rs.getString(2));				
				showStu.setCname(rs.getString(3));				
				showStu.setCdepat(rs.getString(4));
				this.showStu = showStu;
			}			
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return showStu;
	}

}
