package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action{
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");	//ログインユーザーを取得
		//Daoの初期化
		SubjectDao subjectDao = new SubjectDao();
		StudentDao studentDao = new StudentDao();
		ClassNumDao classnumDao = new ClassNumDao();
		//リストの初期化
		List<String> ent_year_list = new ArrayList<String>();
		List<String> subjectname_list = new ArrayList<String>();
		List<String> classnum_list = new ArrayList<String>();

		//プルダウン用にリストを取得*SQLはDaoで実行
		ent_year_list = studentDao.getEntYearList(teacher);
		subjectname_list = subjectDao.getSubjectNameList(teacher);
		classnum_list = classnumDao.filter(teacher.getSchool());

		//デバッグ用,必要に応じてコメントアウトから外す
		//System.out.println(ent_year_list);
		//System.out.println(subjectname_list);
		//System.out.println(classnum_list);

		//リストを遷移先を渡す
		req.setAttribute("ent_year_list",ent_year_list);
		req.setAttribute("subjectname_list",subjectname_list);
		req.setAttribute("classnum_list", classnum_list);

	return "../studentmanager/test_list.jsp";
	}
}