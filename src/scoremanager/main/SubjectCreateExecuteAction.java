package scoremanager.main;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;
public class SubjectCreateExecuteAction extends Action{
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	//セッションを取得
	HttpSession session = req.getSession();
	//データベースアクセスのためのDAOをインスタンス化
		SubjectDao subDao  = new SubjectDao(); //科目情報

		//変数宣言と初期化
		String cd = ""; //科目コード
		String name = ""; //科目名
		Subject subject = null; //科目情報格納用

		//エラーメッセージ格納用Map
		Map<String, String> errors = new HashMap<>();

		//ログイン中の教師情報をセッションから取得
		Teacher teacher = (Teacher)session.getAttribute("user");

		//HTMLフォームから送信されたデータを取得
		cd = req.getParameter("cd");
		name = req.getParameter("name");

		//科目コードが3文字であるかチェック
		if (cd.length() != 3){
			errors.put("cd", "科目コードは3文字で入力してください");
		}else {
			//科目コードが重複していないかチェック
			Subject existingSubject = subDao.get(cd,
			((Teacher)session.getAttribute("user")).getSchool()); //getメソッドを使用

			if (existingSubject != null){
				//重複する科目コードが存在する場合
				errors.put("cd", "科目コードが重複しています");
			} else {
				//新しい科目インスタンスを作成し、フォームデータを設定
				subject = new Subject();
				subject.setCd(cd);
				subject.setName(name);
				subject.setSchool(((Teacher)session.getAttribute("user")).getSchool());
				//データベースに保存
				subDao.save(subject);
			}
		}

		//エラー発生時の処理
		if (!errors.isEmpty()){
			//エラーメッセージがある場合
			req.setAttribute("errors", errors);//エラーメッセージを設定
			req.setAttribute("cd", cd);
			req.setAttribute("name", name);
			return "../studentmanager/subject_create.jsp";//入力フォームに戻る
		} else{
			//エラーがなければ登録完了画面へ
			return "../studentmanager/subject_create_done.jsp";
		}
	}
}