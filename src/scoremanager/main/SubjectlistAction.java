package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;


public class SubjectlistAction extends Action{
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		//セッションからユーザ情報を取得する
		HttpSession session=request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		String subjectCd="";
		String  subjectName= "";

		SubjectDao subDao = new SubjectDao();//科目DAO


		Map<String, String> errors = new HashMap<>();

		System.out.println(teacher);
		List<Subject> list = subDao.filter(teacher);

		//リクエストにデータをセット
		request.setAttribute("subject", list);

		//JSPへフォワード
		return "/studentmanager/subject_list.jsp";
	}
}