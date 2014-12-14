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
	//��ѯ����ѧ���Ĳ�ѯSQL���
	private String SQL_VIEW = "SELECT sno FROM suser WHERE sno=? AND password=?;";
	//���뵥��ѧ��SQL���
	private String SQL_INSERT = "INSERT INTO suser(sno,password) VALUES (?,?)";
	//ɾ��ĳ��ѧ��SQL���
	private String SQL_DELETE = "DELETE FROM suser WHERE sno=? ;";
	//�޸�����
	private String SQL_UPDATE = "UPDATE suser set password =? WHERE sno = ? ;";
	//�������ݿ�����
	public void setConnection(Connection conn){
		this.conn = conn;
	}
	//����ĳ��ѧ������
	public boolean insertOne(SUser u) {
		// TODO �Զ����ɵķ������
		boolean flag = false;//��������ɹ���־
		if(u!=null){
			try {
				PreparedStatement pstat = this.conn.prepareStatement(SQL_INSERT+";");
				pstat.setString(1, u.getSno());
				pstat.setString(2, u.getPassword());
				int i = pstat.executeUpdate();
				if(i==1)
					flag=true;//���ֻ��һ����Ӱ�죬��ʾ����ɹ�
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		return flag;//�������ʧ��
	}

	//�������ݲ��룬SQL SERVER 2005 ����ʹ�ã�������MYSQL������ʹ��
	public boolean insertList(List<SUser> list) {
		// TODO �Զ����ɵķ������
		String SQL_INSERT = this.SQL_INSERT;
		boolean flag = false;
		String _sql = ",(?,?) "; //��VALUES �Ӿ��Ķ������
		if(!list.isEmpty()){
			try {
				int size =  list.size();
				//����Ҫ����������ж��ٸ�������������ٸ�����
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
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		return flag;
	}

	//ʵ�ֲ�ѯĳ��ѧ�����˺��Ƿ���ȷ
	public String check(SUser s) {
		// TODO �Զ����ɵķ������
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

	//ɾ��ĳ��ѧ��������
	public boolean delete(String sno) {
		// TODO �Զ����ɵķ������
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
