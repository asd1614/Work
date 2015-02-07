package cn.bsexam.dao.action;
import java.sql.Connection;
import cn.bsexam.vo.*;
import cn.bsexam.dao.interf.IDepat;
import cn.bsexam.dao.impl.DepatManage;
import cn.bsexam.dbc.*;
import java.util.*;
public class DepartList {
	private List<Department> departList = null;
	public DepartList(){		
		this.init();
	}
	private void init(){
		DBC dbc = new DatabaseConnectionODBC();
		Connection conn =null;
		conn = dbc.getConnection();
		IDepat dao_d = new DepatManage();
		dao_d.setConnection(conn);
		this.departList = dao_d.viewList();
	}
	public List<Department> getList(){
		return this.departList;
	}
	public static Map<String,String> getMap(){
		DBC dbc = new DatabaseConnectionODBC();
		Connection conn =null;
		conn = dbc.getConnection();
		IDepat dao_d = new DepatManage();
		dao_d.setConnection(conn);
		Map<String,String> map = dao_d.viewMap();
		return map;		
	}
	public static boolean insert(String depatname){
		boolean flag = false;
		if(depatname!=null){
			DBC dbc = new DatabaseConnectionODBC();
			IDepat dao_d = new DepatManage();
			dao_d.setConnection(dbc.getConnection());
			return dao_d.insertOne(depatname);
		}
		return flag;
	}
}
