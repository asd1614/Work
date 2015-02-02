package cn.bsexam.test;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import cn.bsexam.dbc.*;
import cn.bsexam.dao.action.ExamAction;
import cn.bsexam.vo.ExamType;
public class TestYufa {

	public static void main(String[] args)  {
		InetAddress jwweb = null ;
		try{
			byte ip[] = {(byte) 192,(byte) 168,0,6};
			jwweb = InetAddress.getByAddress(ip);
		}catch(UnknownHostException e){
			try {
				jwweb = InetAddress.getByName("www.bsuc.cn");
			} catch (UnknownHostException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
		System.out.println(jwweb.toString());
	}

}