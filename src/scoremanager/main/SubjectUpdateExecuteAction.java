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

public class SubjectUpdateExecuteAction extends Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		SubjectDao sDao = new SubjectDao();	//科目Dao
		HttpSession session = req.getSession();//セッション
		School school = new School();
		Teacher teacher = (Teacher)session.getAttribute("user");	//ログインユーザーを取得
		Map<String, String> errors = new HashMap<>();	//エラーメッセージ

		//リクエストパラメーターの取得 2
		String cd = req.getParameter("cd");
		String name = req.getParameter("name");

		//DBからデータ取得 3
		Subject subject = sDao.get(cd, school);	//科目コードから科目インスタンスを取得
		List<Subject> list = sDao.filter(teacher);	//ログインユーザーの学校コードをもとに科目コードの一覧を取得

		//ビジネスロジック 4
		//DBへデータ保存 5
		//女王圏で4～5が分岐
		if (subject != null) {
			//学生が存在していた場合
			//インスタンスに値をセット
			subject.setName(name);
			//科目を保存
			sDao.save(subject);
		} else {
			errors.put("cd", "科目が存在していません");
		}

		//エラーがあったかどうかで手順6～7の内容が分岐
		//レスポンス値をセット
		//JSPへフォワード
		req.setAttribute("cd", list);

		if (!errors.isEmpty()) {	//エラーがあった場合、更新画面へ戻る
			//リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("cd", cd);
			req.setAttribute("name", name);
			return "../studentmanager/subject_update.jsp";
		}

		return "../studentmanager/subject_update_comp.jsp";
	}

}
