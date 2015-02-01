package cn.bsexam.dao.action;
import java.sql.Connection;
import cn.bsexam.vo.*;
import cn.bsexam.dao.interf.IDepat;
import cn.bsexam.dao.impl.DepatManage;
import cn.bsexam.dbc.*;
import java.util.*;
public class DepartList {
	private static Map<String,String> map = null;
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
		if(map!=null)
			return map;
		else{
			DBC dbc = new DatabaseConnectionODBC();
			Connection conn =null;
			conn = dbc.getConnection();
			IDepat dao_d = new DepatManage();
			dao_d.setConnection(conn);
			map = dao_d.viewMap();
			return map;
		}		
	}
}
