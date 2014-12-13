package cn.bsexam.test;
import java.io.File;
import java.util.HashMap;
import java.util.List;

import cn.bsexam.dao.action.ExamAction;
import cn.bsexam.vo.ExamType;
public class TestYufa {

	public static void main(String[] args) {
		ExamAction dao = new ExamAction();
		List<ExamType> list = dao.getList();
		System.out.println(list.get(1).getEname());
	}

}
