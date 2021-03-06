package cn.bsexam.dao.interf;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import cn.bsexam.vo.Department;

public interface IDepat {	
	List<Department> viewList();
	Map<String,String> viewMap();
	boolean insertOne(String depatname);
	boolean insertList(List<Department> list);
	boolean updateOne(Department e,String cdepat);
	boolean deleteOne(String depatname);
	void setConnection(Connection conn);
}
