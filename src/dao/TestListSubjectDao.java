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
	private ArrayList<TestListSubject> postFilter(ResultSet rSet) throws Exception{
		//リストを初期化
		ArrayList<TestListSubject> list = new ArrayList<>();
		try{
			//リザルトセットを全権走査
			while(rSet.next()){
				//学生別テスト結果インスタンスを初期化
				TestListSubject testlistsubject = new TestListSubject();
				//学生別テスト結果インスタンスに検索結果をセット
				testlistsubject.setEntYear(rSet.getInt("ent_year"));
				testlistsubject.setClassNum(rSet.getString("t.class_num"));
				testlistsubject.setStudentNo(rSet.getString("no"));
				testlistsubject.setStudentName(rSet.getString("name"));
				//1回目のポイントと2回目のポイントを格納するディクショナリー
				testlistsubject.setPoints(rSet.getInt("test1"),rSet.getInt("test2"));
				list.add(testlistsubject);
			}
		}catch(SQLException | NullPointerException e){
		e.printStackTrace();
	}
	return list;
	}

	//
	public ArrayList<TestListSubject> filter(int entYear,String classNum,Subject subject,School school) throws Exception{
		//リストを初期化
		ArrayList<TestListSubject> list = new ArrayList<>();
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
					"SELECT s.ent_year,s.class_num,s.no,s.name,"
					 +"MAX(CASE WHEN t.NO = 1 THEN t.POINT ELSE NULL END) AS test1,"
					 +"MAX(CASE WHEN t.NO = 2 THEN t.POINT ELSE NULL END) AS test2 "
					 +"FROM test t"
					 +" join"
					 +" student s on t.student_no = s.NO"//学生番号を条件に結合する
					 +" where"//取得したい行を絞り込む
					 +" s.ent_year = ? "
					 +" and .class_num = ?"
					 +" and t.subject_cd = ?"
					 +" and t.school_cd= ? "
					 +" GROUP BY"//学生番号と科目コードの一致する行を結合する
					 +" t.STUDENT_NO,"
					 +" t.SUBJECT_CD"
					 +" ORDER BY"
					 +" t.STUDENT_NO,"
					 +" t.SUBJECT_CD");
			//プリペアードステートメントにバインド
			statement.setInt(1,entYear);//入学年度
			statement.setString(2,classNum);//クラス番号
			statement.setString(3,subject.getCd());//科目コード
			statement.setString(4,school.getCd());//学校コード
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
