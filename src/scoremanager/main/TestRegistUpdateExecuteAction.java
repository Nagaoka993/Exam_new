package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import bean.Teacher;
import bean.Test;
import dao.TestDao;

import tool.Action;

public class TestRegistUpdateExecuteAction extends Action{
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");//ログインユーザーを取得
		ArrayList<Test> Test_list = (ArrayList<Test>)session.getAttribute("Test_list");
	    //  全ての点数入力値を取得
	    String[] pointOldSet = req.getParameterValues("point_old_set[]");
	    String[] pointNewSet = req.getParameterValues("point_new_set[]");
	    String[] studentNoSet = req.getParameterValues("student_no_set[]");
	    String[] subjectCdSet = req.getParameterValues("subject_cd_set[]");
	    String[] NoSet = req.getParameterValues("no_set[]");



	    //Daoを初期化
	    TestDao testDao = new TestDao();
	    boolean isUpdate = false;
	    for(int i=0;i<Test_list.size();i++){


	    	if(Integer.parseInt(pointOldSet[i])!= Integer.parseInt((pointNewSet[i]))){
            	isUpdate = testDao.update(studentNoSet[i],subjectCdSet[i],Integer.parseInt(NoSet[i]),Integer.parseInt(pointNewSet[i]));
	    	}
	    }
	    if(isUpdate){
	    	return "../studentmanager/test_regist_done.jsp";
	    }else{
	    	return "../studentmanager/error.jsp";
	    }
	}
}