package cn.bsexam.dao.action;
import cn.bsexam.dao.action.DepartList;
import java.util.*;
import cn.bsexam.vo.ClassView;
import cn.bsexam.vo.Department;
public class ShowTool {
	private static List<Department> dlist = null;
	private static ClassList dao_c =null;
	private static void d_init(){
		DepartList dao_d = new DepartList();
		dlist = dao_d.getList();
	}
	private static void c_init(){
		dao_c = new ClassList();
	}
	public static List<Department> getDList(){
		if(dlist!=null){
			if(!dlist.isEmpty())
				return dlist;
			else{
				d_init();
				return dlist;
			}
		}else{
			d_init();
			return dlist;
		}
	}
	public static List<ClassView> getClist(String cdepat){
		if(dao_c!=null){
			return dao_c.getCList(cdepat);
		}else{
			c_init();
			return dao_c.getCList(cdepat);
		}
	}
}
