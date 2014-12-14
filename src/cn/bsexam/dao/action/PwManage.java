package cn.bsexam.dao.action;
import java.sql.Connection;

import cn.bsexam.dao.impl.SuserManage;
import cn.bsexam.dao.interf.ISuser;
import cn.bsexam.dbc.DBC;
import cn.bsexam.dbc.DatabaseConnectionODBC;
import cn.bsexam.vo.SUser;
public class PwManage {
	private boolean pw_flag;
	private ISuser dao_su ;
	public PwManage(SUser su){
		DBC dbc = new DatabaseConnectionODBC();
		dao_su = new SuserManage();
		dao_su.setConnection(dbc.getConnection());
		String sno = dao_su.check(su);
		if(sno!=null&&!sno.equals(""))
			pw_flag = true;
		else
			pw_flag = false;
	}
	public boolean isTrue(){
		return pw_flag;
	}
	public boolean alterPW(SUser se){
		boolean flag = false;
		if(pw_flag){
			flag = dao_su.updatePW(se);
		}
		return flag;
	}
}
