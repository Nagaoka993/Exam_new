package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TestDao;
import dao.SubjectDao;
import dao.StudentDao;
import dao.ClassNumDao;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import tool.Action;



public class TestRegistExecuteAction extends Action{
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");//ログインユーザーを取得
		String ent_year_str = req.getParameter("ent_year");
		int ent_year = Integer.parseInt(ent_year_str);
		String class_num = req.getParameter("class_num");
		String subject_name = req.getParameter("subject_name");


		int times = Integer.parseInt(req.getParameter("times"));//テストの回数を取得
		//リストを初期化
		ArrayList<Test> list = new ArrayList<Test>();
		ArrayList<Student> stulist = new ArrayList<Student>();

		//daoを初期化
		TestDao testDao = new TestDao();
		SubjectDao subjectDao = new SubjectDao();
		StudentDao studentDao = new StudentDao();
		ClassNumDao classnumDao = new ClassNumDao();
		//入学年度、クラス番号に一致した学生を検索
		stulist = (ArrayList<Student>) studentDao.filter(teacher.getSchool(),ent_year);
		Subject subject = subjectDao.get2(subject_name, teacher.getSchool());
		for(Student i:stulist ){
			//リストにTestインスタンスを追加
			list.add(testDao.filter(i, subject, times));
		}
		//リストの初期化
		List<String> ent_year_list = new ArrayList<String>();
		List<String> subjectname_list = new ArrayList<String>();
		List<String> classnum_list = new ArrayList<String>();

		//プルダウン用にリストを取得*SQLはDaoで実行
		ent_year_list = studentDao.getEntYearList(teacher);
		subjectname_list = subjectDao.getSubjectNameList(teacher);
		classnum_list = classnumDao.filter(teacher.getSchool());


		//リストを遷移先を渡す
		req.setAttribute("ent_year_list",ent_year_list);
		req.setAttribute("subjectname_list",subjectname_list);
		req.setAttribute("classnum_list", classnum_list);
		session.setAttribute("Test_list", list);
		//デバッグ用
		System.out.println(list);


		return "../studentmanager/test_regist_list.jsp";
	}
}
