package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao{
	//rSetをインスタンスに格納するためのメソッド
	private Test postFilter(ResultSet rSet) throws Exception{
		//インスタンスを初期化
		Test test = new Test();
		try{
			//リザルトセットを全権走査
			while(rSet.next()){
				//SQLの結果を元にインスタンスを取得するためのDaoを初期化
				StudentDao studentDao = new StudentDao();
				SubjectDao subjectDao = new SubjectDao();
				SchoolDao schoolDao = new SchoolDao();
				//テスト結果インスタンスに検索結果をセット
				//リザルトセットの学生番号をもとに学生インスタンスをセット
				test.setStudent(studentDao.get(rSet.getString("student_no")));
				test.setClassNum(rSet.getString("class_num"));
				//リザルトセットのschool_cdをもとに学校インスタンスを取得
				School school=schoolDao.get(rSet.getString("school_cd"));
				//リザルトセットの科目コードをもとに科目インスタンスをセット
				Subject subject = subjectDao.get(rSet.getString("subject_cd"), school);
				test.setSubject(subject);
				test.setSchool(school);
				test.setNo(rSet.getInt("no"));
				test.setPoint(rSet.getInt("point"));
			}
		}catch(SQLException | NullPointerException e){
		e.printStackTrace();
	}
	return test;
	}


	//入学年度、クラス、科目、テストの回数を条件に絞り込んだ行を返す
	//入学年度、クラスは引数で渡すstudentオブジェクトから取得
	public Test filter(Student student,Subject subject,int times) throws Exception{
		//beanを初期化
		Test test = new Test();
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//リザルトセット
		ResultSet rSet = null;
	try{
		statement = connection.prepareStatement(//学生番号、科目コード、テストの番号を条件に検索
				"select*from test where student_no=? and subject_cd=? and no = ?");
		//プリペアードステートメントにバインド
		statement.setString(1,student.getNo());
		statement.setString(2,subject.getCd());
		statement.setInt(3, times);
		//プライベートステートメントを実行
		rSet = statement.executeQuery();
		//リストへの格納処理を実行
		test = postFilter(rSet);
		//プライベートステートメントを実行
		rSet = statement.executeQuery();
		//インスタンスへの格納処理を実行
		test = postFilter(rSet);
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
	return test;
	}

	public boolean update(String  student_no,String subject_cd,int no,int new_point)throws Exception{
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//実行件数をカウント
		int count = 0;
	try{
		statement = connection.prepareStatement(
				"update test set point=? where student_no=? and  subject_cd=? and no=?"
				);
		statement.setInt(1,new_point);
		statement.setString(2, student_no);
		statement.setString(3, subject_cd);
		statement.setInt(4,no);
		//実行件数を取得
		count = statement.executeUpdate();
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
	}if(0<count){
		return true;
	}else{
		return false;
	}
	}
}
