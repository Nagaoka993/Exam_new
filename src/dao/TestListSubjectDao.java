package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
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
				testlistsubject.setSubjectName(rSet.getString("name"));
				testlistsubject.setSubjectCd(rSet.getString("subject_cd"));
				testlistsubject.setNum(rSet.getInt("no"));
				testlistsubject.setPoint(rSet.getInt("point"));
				list.add(testlistsubject);
			}
		}catch(SQLException | NullPointerException e){
		e.printStackTrace();
	}
	return list;
	}

	//
	public List<TestListSubject> filter(Student student) throws Exception{
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
					"select*from test t  join  subject s on t.subject_cd = s.cd where student_name=?");
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
