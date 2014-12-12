package cn.bsexam.test;
import java.sql.*;
import java.util.*;
import cn.bsexam.vo.SUser;
import cn.bsexam.dao.interf.ISuser;
import cn.bsexam.dao.impl.SuserManage;
import cn.bsexam.dbc.DatabaseConnectionODBC;

public class TextLogin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			DatabaseConnectionODBC dbc = new DatabaseConnectionODBC();
			Connection conn = dbc.getConnection();
			PreparedStatement pstat = 
					conn.prepareStatement("select edate from examtype ");
		
		ResultSet re = pstat.executeQuery();
		while(re.next())
			System.out.println("Resulte: "+re.getString(1).substring(0, 10));
			
		}catch(SQLException e){
			System.err.print("Error" + e.getMessage());
		}
	}

}
