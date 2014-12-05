package cn.bsexam.dao.action;
import java.util.*;
import java.sql.Connection;
import cn.bsexam.vo.ClassView;
import cn.bsexam.dao.interf.IClass;
import cn.bsexam.dao.impl.ClassManage;
import cn.bsexam.dbc.*;
public class ClassList {
	private List<ClassView> clist = null;
	private IClass dao_c = null;
	public ClassList(){
		init();
	}
	private void init(){
		DBC dbc = new DatabaseConnectionODBC();
		Connection conn  = dbc.getConnection();
		this.dao_c = new ClassManage();
		this.dao_c.setConnection(conn);
	}
	public List<ClassView> getCList(String cdepat){
		this.clist = dao_c.viewList_u(cdepat);
		return this.clist;
	}
}
