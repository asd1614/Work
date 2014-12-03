package cn.bsexam.dao.action;
import java.util.*;
import java.sql.*;
import cn.bsexam.vo.SUser;
import cn.bsexam.vo.ShowStu;
import cn.bsexam.vo.Student;
import cn.bsexam.dao.interf.*;
import cn.bsexam.dao.impl.*;
import cn.bsexam.dbc.*;
public class LoginAction {
	public static ShowStu getShowStu(SUser s){
		// 初始化数据库连接
		ISuser dao_s = new SuserManage();
		IShowStu dao_stu = new ShowStuImpl();
		DBC dbc = new DatabaseConnectionODBC();
		Connection conn = dbc.getConnection();
		dao_s.setConnection(conn);
		dao_stu.setConnection(conn);
		//定义返回对象
		String sno = s.getSno();
		ShowStu stu = null;
		String sno_t = dao_s.check(s);
		if(sno.equals(sno_t)){
			stu = dao_stu.findShowStu(sno);
		}
		if(stu!=null)
			return stu;
		else return null;//失败返回空值
	}
	public static Student getStudent(String sno){
		//初始化数据库接连
		IStudent dao_s = new StudentManage();
		DBC dbc =  new DatabaseConnectionODBC();
		Connection conn = dbc.getConnection();
		dao_s.setConnection(conn);
		Student student = dao_s.viewOne(sno);
		return student; 
	}
}
