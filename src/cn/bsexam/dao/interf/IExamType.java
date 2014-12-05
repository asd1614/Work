package cn.bsexam.dao.interf;

import java.sql.Connection;
import java.util.List;

import cn.bsexam.vo.ExamType;

public interface IExamType {
	List<ExamType> viewList();
	boolean insertOne(ExamType e);
	boolean insertList(List<ExamType> list);
	boolean updateOne(ExamType e,int eno);
	boolean deleteOne(int eno);
	void setConnection(Connection conn);
}
