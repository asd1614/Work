package cn.bsexam.dao.interf;
import java.sql.Connection;

import cn.bsexam.vo.SysUser;
public interface ISysUser {
	boolean check(SysUser u);
	boolean alter(SysUser u);
	void setConnection(Connection conn);
}
