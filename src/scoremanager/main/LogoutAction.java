package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction extends Action{
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//セッションを取得
		 HttpSession session = req.getSession(true);
		//セッションを削除
		 session.invalidate();


		//ログアウトしましたと表示
		return "../studentmanager/logout.jsp";
	}
}
