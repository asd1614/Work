package cn.bsexam.dao.interf;
import cn.bsexam.vo.ClassMessage;
import cn.bsexam.vo.ClassView;
import cn.bsexam.vo.Degree;

import java.sql.Connection;
import java.util.*;
public interface IClass {
	List<ClassMessage> viewList(String dept);
	List<ClassView> viewList_u(String cdepat);
	boolean insertOne(ClassMessage e);
	boolean insertList(List<ClassMessage> list);
	boolean updateOne(ClassMessage e,String cname);
	boolean deleteOne(String cname);
	void setConnection(Connection conn);
}
