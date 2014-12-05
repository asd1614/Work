package cn.bsexam.test;

public class TestYufa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		java.util.List<cn.bsexam.vo.ClassView> list = 
			cn.bsexam.dao.action.ShowTool.getClist("HS");
		int i = 0;
		while(i<list.size()){
			System.out.println(list.get(i).getCdepat()+"--"+list.get(i).getCname());
			i++;
		}
	}

}
