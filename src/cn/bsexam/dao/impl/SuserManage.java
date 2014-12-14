package cn.bsexam.dao.impl;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.bsexam.dao.interf.ISuser;
import cn.bsexam.vo.SUser;

public class SuserManage implements ISuser {

	private Connection conn = null;
	//查询单个学生的查询SQL语句
	private String SQL_VIEW = "SELECT sno FROM suser WHERE sno=? AND password=?;";
	//插入单个学生SQL语句
	private String SQL_INSERT = "INSERT INTO suser(sno,password) VALUES (?,?)";
	//删除某个学生SQL语句
	private String SQL_DELETE = "DELETE FROM suser WHERE sno=? ;";
	//修改密码
	private String SQL_UPDATE = "UPDATE suser set password =? WHERE sno = ? ;";
	//设置数据库连接
	public void setConnection(Connection conn){
		this.conn = conn;
	}
	//插入某个学生数据
	public boolean insertOne(SUser u) {
		// TODO 自动生成的方法存根
		boolean flag = false;//插入操作成功标志
		if(u!=null){
			try {
				PreparedStatement pstat = this.conn.prepareStatement(SQL_INSERT+";");
				pstat.setString(1, u.getSno());
				pstat.setString(2, u.getPassword());
				int i = pstat.executeUpdate();
				if(i==1)
					flag=true;//如果只有一行受影响，表示插入成功
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return flag;//否则插入失败
	}

	//多行数据插入，SQL SERVER 2005 不能使用，可以在MYSQL上正常使用
	public boolean insertList(List<SUser> list) {
		// TODO 自动生成的方法存根
		String SQL_INSERT = this.SQL_INSERT;
		boolean flag = false;
		String _sql = ",(?,?) "; //在VALUES 子句后的多个数据
		if(!list.isEmpty()){
			try {
				int size =  list.size();
				//根据要插入的数据有多少个来决定加入多少个括号
				for(int i=1;i<size;i++){
					SQL_INSERT = SQL_INSERT+_sql;
				}
				PreparedStatement pstat = this.conn.prepareStatement(SQL_INSERT+";");
				for(int i=0,j=1;i<size;i++){
					pstat.setString(j++, list.get(i).getSno());
					pstat.setString(j++, list.get(i).getPassword());
				}
				int n = pstat.executeUpdate();
				if(n==size)
					flag=true;
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return flag;
	}

	//实现查询某个学生的账号是否正确
	public String check(SUser s) {
		// TODO 自动生成的方法存根
		try{
			PreparedStatement pstat = this.conn.prepareStatement(SQL_VIEW);
			pstat.setString(1, s.getSno());
			pstat.setString(2, s.getPassword());
			ResultSet re = pstat.executeQuery();
			if(re.next())
				return re.getString(1).trim();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	//删除某个学生的数据
	public boolean delete(String sno) {
		// TODO 自动生成的方法存根
		boolean flag = false;
		try{
			PreparedStatement pstat = this.conn.prepareStatement(SQL_DELETE);
			int i = 0;
			i = pstat.executeUpdate();
			if(i==1)
				flag = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public boolean updatePW(SUser s) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try{
			PreparedStatement pstat = this.conn.prepareStatement(SQL_UPDATE);
			pstat.setString(1, s.getPassword());
			pstat.setString(2, s.getSno());
			if(pstat.executeUpdate()==1){
				flag = true;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return flag;
	}

}
