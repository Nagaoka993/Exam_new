package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {

	@Override
	public String execute (HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数を宣言 1
		SubjectDao sDao = new SubjectDao();	//科目Dao
		HttpSession session = req.getSession();	//セッション
		School school = new School();
		Teacher teacher = (Teacher)session.getAttribute("user");	//ログインユーザーを取得
		Map<String, String> errors = new HashMap<>();	//エラーメッセージ

		//リクエストパラメーターの取得 2
		String cd = req.getParameter("cd");	//科目コード

		//DBからデータを取得 3
		Subject subject = sDao.get(cd, school);		//科目コードから科目インスタンスを取得
		List<Subject> list = sDao.filter(teacher);	//ログインユーザーの学校コードをもとに科目を取得

		//ビジネスロジック 4
		//DBへデータを保存 5
		//レスポンス値をセット 6
		//条件で手順4～6の内容が分岐
		req.setAttribute("cd", list);
		if (subject != null) {	//科目が存在していた場合
			req.setAttribute("cd", subject.getCd());
			req.setAttribute("name", subject.getName());
		} else {	//科目が存在してなかった場合
			errors.put("cd", "科目が存在していません");
			req.setAttribute("errors", errors);
		}
		return "../studentmanager/subject_update.jsp";
	}

}
