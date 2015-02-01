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
		String cname = data.get("�����༶");
		//����ѧ���������Ϣ
		s.setSno(data.get("ѧ��"));
		s.setSname(data.get("����"));
		s.setSsex(data.get("�Ա�"));
		s.setSid(data.get("���֤��"));
		s.setCname(cname);
		//���ð༶����Ļ�����Ϣ������֮ǰ�Ȳ�ѯ�Ƿ��иð༶����Ϣ���ڣ���������������²���
		String cdepat = dapat.get(data.get("Ժ(ϵ)/��"));		
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
			c.setSyear(Integer.parseInt(data.get("��ѧ���").substring(2,4)));
			c.setClength(Integer.parseInt(data.get("ѧ��")));
			String dname = data.get("�������");
			Map<String,String> map_d = DegreeAction.getMap();
			int dno = Integer.parseInt(map_d.get(dname));
			c.setDno(dno);
			c.setCspecial(data.get("רҵ"));
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
				// TODO �Զ����ɵ� catch ��
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
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}			
    		try {
				return Jsoup.parse(res_input, "GB2312", "http://www.bsuc.cn:8172/jwweb/xsxj/Stu_MyInfo_RPT.aspx");
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
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
