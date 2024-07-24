package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao{

	//複数行にわたるSQLの結果をリストに格納するためのメソッド
	private List<TestListSubject> postFilter(ResultSet rSet) throws Exception{
		//リストを初期化
		List<TestListSubject> list = new ArrayList<>();
		try{
			//リザルトセットを全権走査
			while(rSet.next()){
				//学生別テスト結果インスタンスを初期化
				TestListSubject testlistsubject = new TestListSubject();
				//学生別テスト結果インスタンスに検索結果をセット
				testlistsubject.setEntYear(rSet.getInt(""));
				testlistsubject.setClassNum(rSet.getString(""));
				testlistsubject.setStudentNo(rSet.getString(""));
				testlistsubject.setStudentName(rSet.getString(""));
				//1回目のポイントと2回目のポイントを格納するディクショナリー
				Map<Integer, Integer> points = new HashMap<>();
				testlistsubject.setPoints(points);
				list.add(testlistsubject);
			}
		}catch(SQLException | NullPointerException e){
		e.printStackTrace();
	}
	return list;
	}

	//
	public List<TestListSubject> filter(int entYer,String classNum,Subject subject,School school) throws Exception{
		//リストを初期化
		List<TestListSubject> list = new ArrayList<>();
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//リザルトセット
		ResultSet rSet = null;
		try{
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(
					//テストテーブルの結果に科目cdを付け足す
					"select  "
					+ 		"case when no = 1 then point end as 1回目,*"
					+ 			" when no = 2 then point end as 2回目,"
					+ ""
					+ "from test t  join  subject s on t.subject_cd = s.cd where student_n=?");
			//プリペアードステートメントに学生番号をバインド
			statement.setString(1,student.getNo());
			//プライベートステートメントを実行
			rSet = statement.executeQuery();
			//リストへの格納処理を実行
			list = postFilter(rSet);
		}catch(Exception e){
			throw e;
		}finally{
			//プリペアードステートメントを閉じる
			if(statement != null){
				try{
					statement.close();
				}catch(SQLException sqle){
					throw sqle;
				}
			}
			//コネクションを閉じる
			if(connection != null){
				try{
					connection.close();
				}catch (SQLException sqle){
					throw sqle;
				}
			}
		}
		return list;
	}
}
