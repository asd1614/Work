package cn.bsexam.check;
import cn.bsexam.vo.Student;
public class StudentCheck {
	private static int Name_Length = 16;	//�������16������
	private static int StudentId_Length = 20;
	private static int IdCard_Length = 18;
	public static int valuesCheck(Student s){
		if(s.getSname().length()>Name_Length){
			return 1;
		}
		if(!s.getSsex().equals("��")){
			if(!s.getSsex().equals("Ů")){
				return 2;
			}
		}
		if(String.valueOf(s.getSno()).length()>StudentId_Length){
			return 3;
		}
		if(String.valueOf(s.getSid()).length()>IdCard_Length){
			return 4;
		}		
		return 0;
	}
}
