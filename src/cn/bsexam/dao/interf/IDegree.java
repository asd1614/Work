package cn.bsexam.dao.interf;

import java.sql.Connection;
import java.util.List;

import cn.bsexam.vo.Degree;

public interface IDegree {	
	List<Degree> viewList();
	boolean insertOne(Degree e);
	boolean insertList(List<Degree> list);
	boolean updateOne(Degree e,int dno);
	boolean deleteOne(int dno);
	void setConnection(Connection conn);
}
