package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteAction extends Action {

	@Override
	public String execute (HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数を宣言 1
		SubjectDao sDao = new SubjectDao();	//科目Dao
		HttpSession session = req.getSession();	//セッション
		School school = new School();
		Teacher teacher = (Teacher) session.getAttribute("user"); 	//ログインユーザーを取得

		//リクエストパラメーターの取得 2
		String cd = req.getParameter("cd"); 	//科目コード

		//DBからデータを取得 3
		Subject subject = sDao.get(cd, school);	//科目コードから科目インスタンスを取得
		List<Subject> list = sDao.filter(teacher);	//ログインユーザーの学校コードをもとに科目を取得

		//DBへデータを保存 4
		//レスポンス値をセット 5
		req.setAttribute("cd", list);
		req.setAttribute("cd", subject.getCd());
		req.setAttribute("name", subject.getName());

		return "../studentmanager/subject_delete.jsp";
	}

}