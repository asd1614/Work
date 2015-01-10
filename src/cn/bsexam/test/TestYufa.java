package cn.bsexam.test;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import cn.bsexam.dbc.*;
import cn.bsexam.dao.action.ExamAction;
import cn.bsexam.vo.ExamType;
public class TestYufa {

	public static void main(String[] args)  {
		 String str = "cn.bsexam.dbc.DatabaseConnectionODBC";
		 Connection conn;
		 try {
			 DBC dbc = new DatabaseConnectionODBC();
			 conn = dbc.getConnection();
			 if(conn==null)
				 throw new ClassNotFoundException();
		} catch(ClassNotFoundException e1){
			System.out.println("我抛出了一个空指针异常");
			try {
				 DBC dbc = new DatabaseConnectionNo();
				 conn = dbc.getConnection();
				 if(conn==null)
					 throw new ClassNotFoundException();
			} catch(Exception e2){
				System.out.println("我抛出了一个空指针异常11");
			}
		}
	}

}