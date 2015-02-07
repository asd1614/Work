package cn.bsexam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.bsexam.dao.interf.ISysUser;
import cn.bsexam.vo.SysUser;

public class SysUserManage implements ISysUser {
	private String VIEW = "SELECT suser FORM sysuser WHERE suser = ? and password = ? ;";
	private String UPDATE = "UPDATE sysuser set password = ? WHERE suser = ? ;";
	private Connection conn ;
	public void setConnection(Connection conn){
		this.conn = conn;
	}
	public boolean check(SysUser u) {
		// TODO 自动生成的方法存根
		boolean flag = false;
		try {
			PreparedStatement pstat = this.conn.prepareStatement(VIEW);
			pstat.setString(1, u.getSuser());
			pstat.setString(2,u.getPassword());
			ResultSet re = pstat.executeQuery();
			if(re.next())
				flag = true;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return flag;
	}
	public boolean alter(SysUser u) {
		// TODO 自动生成的方法存根
		boolean flag = false;
		try{
			PreparedStatement pstat = this.conn.prepareStatement(UPDATE);
			pstat.setString(1, u.getPassword());
			pstat.setString(2, u.getSuser());
			if(pstat.executeUpdate()==1)
				flag = true;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return flag;
	}

}
