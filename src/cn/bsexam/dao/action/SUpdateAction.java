package cn.bsexam.dao.action;
import java.sql.Connection;

import cn.bsexam.vo.ShowStu;
import cn.bsexam.vo.Student;
import cn.bsexam.dao.interf.*;
import cn.bsexam.dao.impl.*;
import cn.bsexam.dao.action.*;
import cn.bsexam.dbc.*;
public class SUpdateAction {
	private static int Name_Length = 16;	//最长不超过16个汉字
	private static int Sid_Length = 18;
	public static boolean update(Student s){
		DBC dbc = new DatabaseConnectionODBC();
		Connection conn = dbc.getConnection();
		IStudent dao_s	= new StudentManage();
		dao_s.setConnection(conn);
		boolean flag = false;
		if(s.getSname().length()<=16)
			if(s.getSid().length()<=18){
				flag = dao_s.updateOne(s);
			}
		return flag;
	}
	public static ShowStu refreshStu(String sno){
		DBC dbc = new DatabaseConnectionODBC();
		Connection conn = dbc.getConnection();
		IShowStu dao_stu = new ShowStuImpl();
		dao_stu.setConnection(conn);
		ShowStu stu = dao_stu.findShowStu(sno);
		return stu ;
	}
	public static Student updateImage_f(Student s){
		boolean flag =false;
		DBC dbc = new DatabaseConnectionODBC();
		Connection conn = dbc.getConnection();
		IStudent dao_s	= new StudentManage();
		dao_s.setConnection(conn);
		flag = dao_s.updateImage(s);
		if(flag){
			return LoginAction.getStudent(s.getSno());
		}else
			return s;
	}
	public static boolean insertS(Student s){
		boolean flag =false;
		DBC dbc = new DatabaseConnectionODBC();
		Connection conn = dbc.getConnection();
		IStudent dao_s	= new StudentManage();
		dao_s.setConnection(conn);
		flag = dao_s.insertOne(s);
		return flag;
	}
}
