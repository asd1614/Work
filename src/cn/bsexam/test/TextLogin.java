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
					conn.prepareStatement("select RTRIM(sno) from suser where sno = ? and password = ? ");
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter your ID :");
		pstat.setString(1, scan.nextLine());
		System.out.print("Enter your Password : ");
		pstat.setString(2, scan.nextLine());
		ResultSet re = pstat.executeQuery();
		if(re.next())
			System.out.println("Resulte: "+re.getString(1));
			
		}catch(SQLException e){
			System.err.print("Error" + e.getMessage());
		}
	}

}
