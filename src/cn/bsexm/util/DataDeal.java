package cn.bsexm.util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.*;
import java.io.*;
import java.util.*;

import cn.bsexam.vo.ClassMessage;
import cn.bsexam.vo.ClassView;
import cn.bsexam.vo.Student;
import cn.bsexam.dbc.*;
import cn.bsexam.dao.action.*;
public class DataDeal implements Runnable{
	//http://www.bsuc.cn:8172/jwweb/xsxj/Stu_MyInfo_RPT.aspx
	private String sessionId ;
	private Document doc ;
	private Map<String,String> data;
	private Map<String,String> dapat;
	public DataDeal(String sessionId){
		this.sessionId = sessionId;
		this.dapat = DepartList.getMap();
		
	}
	public void run() {
		this.doc = initInfo(sessionId);
		this.data = dealDoc(doc);
		Student s = new Student();
		String cname = data.get("行政班级");
		//设置学生对象的信息
		s.setSno(data.get("学号"));
		s.setSname(data.get("姓名"));
		s.setSsex(data.get("性别"));
		s.setSid(data.get("身份证号"));
		s.setCname(cname);
		//设置班级对象的基本信息，在这之前先查询是否有该班级的信息存在，如果存在则不再重新插入
		String cdepat = dapat.get(data.get("院(系)/部"));		
		ClassList dao_c = new ClassList();
		List<ClassView> c_list = dao_c.getCList(cdepat);
		boolean flag = false;
		for(ClassView v:c_list){
			if(v.getCname().equals(cname)){
				flag = true;
				break;
			}
		}
		if(!flag){
			ClassMessage c = new ClassMessage();
			c.setCname(cname);
			c.setSyear(Integer.parseInt(data.get("入学年份").substring(2,4)));
			c.setClength(Integer.parseInt(data.get("学制")));
			String dname = data.get("培养层次");
			Map<String,String> map_d = DegreeAction.getMap();
			int dno = Integer.parseInt(map_d.get(dname));
			c.setDno(dno);
			c.setCspecial(data.get("专业"));
			c.setCdepat(cdepat);
			ClassList.insert(c);
		}
		SUpdateAction.insertS(s);
	}
	private static Document initInfo(String sessionId){
		if(sessionId!=null&&!sessionId.equals("")){
			InetAddress stuInfo ; 
			Socket stuConn = null ;
			try {
				stuInfo = InetAddress.getByName("www.bsuc.cn");
				stuConn = new Socket(stuInfo,8172);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			Map<String,String> getHead = HttpHeadHelper.getHeader().Map();
			getHead.put("Cookie",sessionId+"\r\n");
			OutputStreamWriter out;
			InputStream res_input = null;
			HttpHeadHelper res_head;
			try {
				out = new OutputStreamWriter(stuConn.getOutputStream(),"ASCII");
				out.write("GET /jwweb/xsxj/Stu_MyInfo_RPT.aspx HTTP/1.1\r\n");				
	    		for(Map.Entry<String, String> entry:getHead.entrySet()){
	    			out.write(entry.getKey()+":"+entry.getValue());
	    		}    		
	    		out.write("\r\n");
	    		out.flush();
	    		InputStream input = new BufferedInputStream(stuConn.getInputStream());
	    		res_head = new HttpHeadHelper(input);
	    		String status = res_head.get(null);
	    		if(status.equals("HTTP/1.1 200 OK")){
	    			int content_length = Integer.parseInt(res_head.get("Content-Length"));
	    			byte byteData[] = new byte[content_length];
	    			int count = input.read(byteData);
	    			if(count==-1)
	    				return null;
	    			res_input = new ByteArrayInputStream(byteData);
	    			out.close();
	    			input.close();
	    		}else
	    			return null;
			} catch (UnsupportedEncodingException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}			
    		try {
				return Jsoup.parse(res_input, "GB2312", "http://www.bsuc.cn:8172/jwweb/xsxj/Stu_MyInfo_RPT.aspx");
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return null;
	}
	private static Map<String ,String> dealDoc(Document doc){
		Map<String,String> map = new HashMap<String,String>();
		Element body = doc.body();
		Elements tr = body.select("tr");
		ListIterator<Element> iterator=  tr.listIterator();
		iterator.next();
		while(iterator.hasNext()){
			Element e = iterator.next();
			Elements td = e.children();
			ListIterator<Element> td_iterator = td.listIterator();
			while(td_iterator.hasNext()){
				Element td_1 = td_iterator.next();
				Element td_2 =null; 
				if(td_iterator.hasNext())
					td_2 = td_iterator.next();
				else
					break;
				if(td_2.hasText()){
					String name = td_1.html().replaceAll("[&nbsp;]+", "");
					String value = td_2.text();
					map.put(name,value);
				}
			}			
		}
		return map;
	}
	
}
