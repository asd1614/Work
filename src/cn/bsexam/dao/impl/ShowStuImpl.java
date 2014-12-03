package cn.bsexam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.bsexam.dao.interf.IShowStu;
import cn.bsexam.vo.ShowStu;

public class ShowStuImpl implements IShowStu {	
	private Connection conn =null;
	//返回一个学生的姓名、学号、班级、系别名称。用来显示表示层的简略学生信息。
	private String SQL="SELECT * FROM showstu where sno=? ;";
	private ResultSet rs ;
	private ShowStu showStu = null;
	public void setConnection(Connection conn){
		this.conn = conn;
	}
	public ShowStu findShowStu(String SNO) {
		// TODO 自动生成的方法存根				
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return showStu;
	}

}
