package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import bean.TestListSubject;
import dao.SchoolDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action{
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");	//ログインユーザーを取得

		String ent_year_str = req.getParameter("ent_year");
		String class_num = req.getParameter("class_num");
		String subject_name = req.getParameter("subject_name");

		String student_num = req.getParameter("student_num");

		//科目情報で検索されたときの処理
		if(ent_year_str!= null && class_num!=null && subject_name!=null){
			int ent_year = Integer.parseInt(ent_year_str);//int型へ変換
			//Daoを初期化
			SubjectDao subjectDao = new SubjectDao();
			SchoolDao schoolDao = new SchoolDao();
			//beanを取得
			Subject subject = subjectDao.get2(subject_name,teacher.getSchool());


			//Daoを初期化
			TestListSubjectDao testlistsubjectDao = new TestListSubjectDao();
			//リストを初期化
			List<TestListSubject> list = new ArrayList<>();
			list = testlistsubjectDao.filter(ent_year,class_num, subject, teacher.getSchool());

			//リストをセッションに渡す
			req.setAttribute("list",list);
			//科目別成績一覧表示する
			return "../studentmanager/test_list_subject.jsp";
		}

		//学生番号で検索された時の処理
		if(student_num != null){
			//Daoを初期化
			StudentDao studentDao =  new StudentDao();
			//学生番号に対応するStudentBeanを取得
			Student student = new Student();
			student = studentDao.get(student_num);
			//リストを初期化
			List<TestListStudent> list = new ArrayList<>();
			//Daoを初期化
			TestListStudentDao testliststudentDao = new TestListStudentDao();
			//studentbeanを渡し、一覧で表示させたい情報をList<TestListStudent>インスタンスに格納する
			list = testliststudentDao.filter(student);

			//一覧ページで表示させたい情報をセッションに渡す
			req.setAttribute("student", student);
			req.setAttribute("student_list",list);

			//学生別成績一覧表示する;
			return "../studentmanager/test_list_student.jsp";
		}

		//パラメータが存在しなかったとき
		return "../studentmanaget/test_list_null.jsp";
	}
}