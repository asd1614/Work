package cn.bsexam.dao.interf;

import java.sql.Connection;
import java.util.List;

import cn.bsexam.vo.IdCard;

public interface IIdcard {
	List<IdCard> viewList();
	boolean insertOne(IdCard e);
	boolean insertList(List<IdCard> list);
	boolean updateOne(IdCard e,int idno);
	boolean deleteOne(int idno);
	void setConnection(Connection conn);
}
