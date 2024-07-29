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
		ArrayList<Test> Test_list = (ArrayList<Test>)req.getAttribute("Test_list");
	    //  全ての点数入力値を取得
	    String[] pointValues = req.getParameterValues("point");
	    String[] studentNoSet = req.getParameterValues("student_no_set[]");
	    String[] subjectCdSet = req.getParameterValues("subject_cd_set[]");
	    String[] NoSet = req.getParameterValues("no_set[]");

        // 点数リストを取得
        ArrayList<Integer> oldPointList = new ArrayList<>();
        ArrayList<Integer> newPointList = new ArrayList<>();

        int count = 0; // カウント変数を初期化
        while (true) {
            String oldPointParamName = "point_old_" + count;
            String newPointParamName = "point_new_" + count;

            String oldPointStr = req.getParameter(oldPointParamName);
            String newPointStr = req.getParameter(newPointParamName);

            // パラメータが見つからない場合はループを抜ける
            if (oldPointStr == null || newPointStr == null) {
                break;
            }

            // 数値に変換してリストに追加
            oldPointList.add(Integer.parseInt(oldPointStr));
            newPointList.add(Integer.parseInt(newPointStr));

            count++; // カウントをインクリメント
        }


	    //Daoを初期化
	    TestDao testDao = new TestDao();
	    boolean isUpdate = false;
	    for(int i=0;i<Test_list.size();i++){
	    	if(oldPointList.get(i)!=newPointList.get(i)){
            	isUpdate = testDao.update(studentNoSet[i],subjectCdSet[i],Integer.parseInt(NoSet[i]),newPointList.get(i));
	    	}
	    }
	    if(isUpdate){
	    	return "../studentmanager/test_regist_done.jsp";
	    }else{
	    	return "../studentmanager/error.jsp";
	    }
	}
}