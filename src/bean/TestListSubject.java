package bean;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class TestListSubject {
	private int entYear;//入学年度
	private String studentNo;//学生番号
	private String studentName;//学生名
	private String classNum;//クラス番号
	private List<Integer> points = new ArrayList<Integer>();//1回目の得点,2回目の得点

	public int getEntYear(){
		return entYear;
	}
	public void setEntYear(int entYear){
		this.entYear = entYear;
	}
	public String getStudentNo(){
		return studentNo;
	}
	public void setStudentNo(String studentNo){
		this.studentNo = studentNo;
	}
	public String getStudentName(){
		return studentName;
	}
	public void setStudentName(String studentName){
		this.studentName = studentName;
	}
	public String getClassNum(){
		return classNum;
	}
	public void setClassNum(String classNum){
		this.classNum = classNum;
	}
	public List<Integer> getPoints(){
		return points;
	}
	public void setPoints(int test1,int test2){
		this.points.add(test1);
		this.points.add(test2);
	}
}
