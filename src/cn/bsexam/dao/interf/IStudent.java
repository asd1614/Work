package cn.bsexam.dao.interf;
import java.sql.Connection;
import java.util.List;

import cn.bsexam.vo.Student;
public interface IStudent {
	Student viewOne(String sno);
	List<Student> viewList();
	boolean insertOne(Student e);
	boolean updateOne(Student e);
	void setConnection(Connection conn);
}
