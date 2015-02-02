package cn.bsexam.dao.impl;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.bsexam.dao.interf.IStudent;
import cn.bsexam.vo.Student;

public class StudentManage implements IStudent {
	private Connection conn = null;
	private String SQL_VIEW = "SELECT sno,sname,ssex,sid,cname,image_f FROM student WHERE sno=? ;";
	private String SQL_VIEW_L = "SELECT sno,sname,ssex,sid,cname FROM student OREDER BY sno;";
	private String SQL_INSERT = "INSERT INTO student(sno,sname,ssex,sid,cname) VALUES (?,?,?,?,?);";
	private String SQL_UPDATE = "UPDATE student SET sname=?, ssex=?, sid=?, cname=? WHERE sno=? ;";
	private String SQL_UPDATE_Image_f ="UPDATE student SET image_f=? WHERE sno=? ;";
	//private String SQL_DELETE = "DELETE FROM studetn WHERE sno =? ;";
	public void setConnection(Connection conn){
		this.conn = conn;
	}
	public Student viewOne(String sno) {
		// TODO 自动生成的方法存根
		Student e = null;
		if(sno!=null){
			try {
				PreparedStatement pstat = this.conn.prepareStatement(SQL_VIEW);
				pstat.setString(1, sno);
				ResultSet re = pstat.executeQuery();
				while(re.next()){
					e = new Student();
					e.setSno(re.getString(1));					
					e.setSname(re.getString(2));					
					e.setSsex(re.getString(3));					
					e.setSid(re.getString(4));					
					e.setCname(re.getString(5));
					e.setImage_f(re.getBoolean(6));
				}
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
		return (e==null)?null:e;
	}

	
	public List<Student> viewList() {
		// TODO 自动生成的方法存根
		List<Student> list = new ArrayList<Student>();
		try {
			PreparedStatement pstat = this.conn.prepareStatement(SQL_VIEW_L);
			ResultSet re = pstat.executeQuery();
			while(re.next()){
				Student e = new Student();
				e.setSno(re.getString(1));
				e.setSname(re.getString(2));
				e.setSsex(re.getString(3));
				e.setSid(re.getString(4));
				e.setCname(re.getString(5));
				list.add(e);
			}
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		return list;
	}

	
	public boolean insertOne(Student e) {
		// TODO 自动生成的方法存根
		boolean flag=false;
		if(e!=null){
			try {
				PreparedStatement pstat = this.conn.prepareStatement(SQL_INSERT);
				pstat.setString(1, e.getSno());
				pstat.setString(2, e.getSname());
				pstat.setString(3, e.getSsex());
				pstat.setString(4, e.getSid());
				pstat.setString(5, e.getCname());
				int i = pstat.executeUpdate();
				if(i==1)
					flag = true;
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
		return flag;
	}

	
	public boolean updateOne(Student e) {
		// TODO 自动生成的方法存根
		boolean flag=false;
		if(e!=null){
			try {
				PreparedStatement pstat = this.conn.prepareStatement(SQL_UPDATE);
				pstat.setString(5, e.getSno());
				pstat.setString(1, e.getSname());
				pstat.setString(2, e.getSsex());
				pstat.setString(3, e.getSid());
				pstat.setString(4, e.getCname());
				int i = pstat.executeUpdate();
				if(i==1)
					flag = true;
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
		return flag;
	}
	public boolean updateImage(Student e) {
		// TODO 自动生成的方法存根
		boolean flag=false;
		if(!e.getImage_f()){
			try {
				PreparedStatement pstat = this.conn.prepareStatement(SQL_UPDATE_Image_f);
				pstat.setString(2, e.getSno());
				pstat.setBoolean(1, true);
				int i = pstat.executeUpdate();
				if(i==1)
					flag = true;
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
		return flag;
	}
}
