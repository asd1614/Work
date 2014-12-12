package cn.bsexam.dao.interf;

import java.sql.Connection;
import java.util.List;

import cn.bsexam.vo.SE;

public interface ISE {
	List<SE> viewList();
	SE viewOne(String sno);
	boolean insertOne(SE e);
	boolean insertList(List<SE> list);
	boolean updateOne(SE e);
	boolean deleteOne(String sno,String edate);
	void setConnection(Connection conn);
}
