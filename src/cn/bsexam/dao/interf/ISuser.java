package cn.bsexam.dao.interf;
import cn.bsexam.vo.SUser;

import java.sql.Connection;
import java.util.List;
public interface ISuser {
	boolean insertOne(SUser u);
	boolean insertList(List<SUser> list);
	String check(SUser s);
	boolean delete(String sno);
	void setConnection(Connection conn);
}
