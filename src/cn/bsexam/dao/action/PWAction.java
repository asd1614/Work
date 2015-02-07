package cn.bsexam.dao.action;
import java.sql.Connection;
import cn.bsexam.dao.impl.SysUserManage;
import cn.bsexam.dao.interf.ISysUser;
import cn.bsexam.dbc.DBC;
import cn.bsexam.dbc.DatabaseConnectionODBC;
import cn.bsexam.vo.SysUser;
public class PWAction {
	public static boolean alter(SysUser u){
		DBC dbc = new DatabaseConnectionODBC();
		ISysUser dao_s = new SysUserManage();
		dao_s.setConnection(dbc.getConnection());
		return dao_s.alter(u);
	}
	public static boolean check(SysUser u){
		DBC dbc = new DatabaseConnectionODBC();
		ISysUser dao_s = new SysUserManage();
		dao_s.setConnection(dbc.getConnection());
		return dao_s.check(u);
	}
}
