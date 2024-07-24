package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class LoginExecuteAction
extends Action{
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();//セッション
		//セッションからidとパスワードを取得
		String id = req.getParameter("id");
		String password = req.getParameter("password");

		TeacherDao tDao = new TeacherDao();//教員Dao
		Teacher teacher = tDao.login(id, password);
		//デバッグ用
		System.out.println(id+password);
		System.out.println(teacher);


		if(teacher!=null){
			session.setAttribute("user",teacher);//セッションにuserとして教員オブジェクトを渡す
			//メインメニューを実装
			return "../studentmanager/menu.jsp";
		}else{ //パラメータとしてエラーを渡しつつメニューへ戻る。
			String error = "ログインできませんでした";
			req.setAttribute("error",error);
			return "../studentmanager/login.jsp";
		}
	}
}