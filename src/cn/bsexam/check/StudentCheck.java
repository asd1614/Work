package cn.bsexam.check;
import cn.bsexam.vo.Student;
import javax.servlet.http.*;
public class StudentCheck {
	private static int Name_Length = 16;	//最长不超过16个汉字
	private static int Sid_Length = 18;
	private static String[] parameter = {"name","idCard","sex","stuClass"};
	public static Student getStudent(HttpServletRequest request){
		HttpSession session = request.getSession();
		Student s = (Student)session.getAttribute("s");
		String str[] = new String[6];
		for(int i = 0;i<parameter.length;i++){
			String temp = request.getParameter(parameter[i]);
			if(!temp.equals(""))
				str[i] = temp;
			}
		s.setSname(str[0]);
		s.setSid(str[1]);
		s.setSsex(str[2]);
		s.setCname(str[3]);
		return s;
	}
	
}
