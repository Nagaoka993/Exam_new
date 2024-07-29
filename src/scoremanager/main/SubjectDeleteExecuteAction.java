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

public class SubjectDeleteExecuteAction extends Action {

	@Override
	public String execute (HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		SubjectDao sDao = new SubjectDao();	//科目Dao
		HttpSession session = req.getSession();	//セッション
		School school = new School();
		Teacher teacher = (Teacher) session.getAttribute("user");	//ログインユーザーを取得

		//リクエストパラメーターの取得 2
		String cd = req.getParameter("cd");

		//DBからデータを取得 3
		Subject subject = sDao.get(cd, school);	//科目コードから科目インスタンスを取得
		List<Subject> list = sDao.filter(teacher);	//ログインユーザーの学校コードをもとに科目コードの一覧を取得

		//レスポンス値をセット
		req.setAttribute("cd", list);
		if (subject != null) {
			//科目削除を実行して科目削除完了画面へ
			sDao.delete(subject);
		}
		return "../studentmanager/subject_delete_done.jsp";
	}
}
