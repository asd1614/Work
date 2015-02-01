package cn.bsexam.dao.action;
import java.util.Map;

import cn.bsexam.dao.impl.DegreeManage;
import cn.bsexam.dao.interf.IDegree;
import cn.bsexam.dbc.*;
public class DegreeAction {
	private static Map<String,String> map =null;
	public static Map<String,String> getMap(){
		if(map!=null)
			return map;
		else{
			DBC dbc = new DatabaseConnectionODBC();
			IDegree dao_d = new DegreeManage();
			dao_d.setConnection(dbc.getConnection());
			map = dao_d.viewMap();
			return map;
		}
	}
}
