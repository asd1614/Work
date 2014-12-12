package cn.bsexam.dao.action;
import java.sql.Connection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import cn.bsexam.vo.SE;
import cn.bsexam.vo.ExamType;
import cn.bsexam.dao.interf.ISE;
import cn.bsexam.dao.interf.IExamType;
import cn.bsexam.dao.impl.*;
import cn.bsexam.dbc.*;
public class ExamAction {
	private Map<String,ExamType> map =null;
	public ExamAction(){
		this.init();
	}
	private void init(){
		map = new Hashtable<String,ExamType>();
		DBC dbc = new DatabaseConnectionODBC();
		IExamType dao_ex = new ExamManage();
		dao_ex.setConnection(dbc.getConnection());
		List<ExamType> list = dao_ex.viewList();
		for(int i=0 ;i<list.size();i++){
			map.put(list.get(i).getEno(),list.get(i));
		}
	}
	public Map<String,ExamType> getMap(){
		return this.map;
	}
	public void refresh(){
		this.init();
	}
	public boolean insertSE(SE se){
		boolean flag = false;
		if(se!=null){
			DBC dbc = new DatabaseConnectionODBC();
			ISE dao_se = new SEManage();
			dao_se.setConnection(dbc.getConnection());
			flag = dao_se.insertOne(se);
		}
		return flag;
	}
	public boolean updateSE(SE se){
		boolean flag = false;
		if(se!=null){
			DBC dbc = new DatabaseConnectionODBC();
			ISE dao_se = new SEManage();
			dao_se.setConnection(dbc.getConnection());
			flag = dao_se.updateOne(se);
		}
		return flag;
	}
}
