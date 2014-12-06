package cn.bsexam.test;
import java.util.HashMap;
public class TestYufa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] parameter = {"name","idCard","sex","birthday","dapartment","stuClass"};
		HashMap  map = new HashMap();
		String s = "a";
		map.put(s, parameter[1]);
		System.out.println(map.get("a"));
	}

}
