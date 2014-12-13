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
	private List<ExamType> list =null;
	public ExamAction(){
		this.init();
	}
	private void init(){
		DBC dbc = new DatabaseConnectionODBC();
		IExamType dao_ex = new ExamManage();
		dao_ex.setConnection(dbc.getConnection());
		this.list = dao_ex.viewList();
	}
	public List<ExamType> getList(){
		return this.list;
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
	public SE getSE(String sno,String edate){
		ISE dao_se = new SEManage();
		DBC dbc = new DatabaseConnectionODBC();
		dao_se.setConnection(dbc.getConnection());
		SE se = dao_se.viewOne(sno, edate);
		return se;
	}
}
