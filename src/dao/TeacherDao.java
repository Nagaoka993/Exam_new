package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Teacher;

public class TeacherDao extends Dao{
	//Teacherテーブルの中の引数で与えたIDの行を返す
	public Teacher get(String id) throws Exception{
		//Teacherインスタンスを初期化;
		Teacher teacher = new Teacher();
		//データベースへのコネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;

		try{
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select*from teacher where id=?");
			//プリペアードステートメントにを教員idをバインド
			statement.setString(1, id);
			//プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();

			if(rSet.next()){
				//リザルトセットが存在する場合
				//教員インスタンスに検索結果をセット
				teacher.setId(rSet.getString("id"));
				teacher.setPassword(rSet.getString("password"));
				teacher.setName(rSet.getString("name"));
				teacher.setSchool_cd(rSet.getString("school_cf"));
				// SchoolDao のインスタンスを生成
				SchoolDao schoolDao = new SchoolDao();
				//学校フィールドには学校コードで検索した学校インスタンスをセット
				teacher.setSchool(schoolDao.get(rSet.getString("school_cd")));
			}else{
				//リザルトセットが存在しない場合
				//教員インスタンスにnullをセット
				teacher = null;
			}
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
		return teacher;
	}

	/*引数で与えたIDとパスワードの組み合わせがあるかTeacherテーブルを確認し、
	 あればその行をTeacherオブジェクトとして返し、なければTeacherにnullを返す*/
	//つまりloginメソッドの戻り値がnullであればログイン失敗するように実装する

	public Teacher login(String id,String password) throws Exception{
		//Teacherインスタンスを初期化;
		Teacher teacher = new Teacher();
		//データベースへのコネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;

		try{
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select*from teacher where id=? and password = ?");
			//プリペアードステートメントに教員番号をバインド
			statement.setString(1, id);
			//プリペアードステートメント
			statement.setString(2,password);
			//プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();

			if(rSet.next()){
				//リザルトセットが存在する場合
				//教員インスタンスに検索結果をセット
				teacher.setId(rSet.getString("id"));
				teacher.setPassword(rSet.getString("password"));
				teacher.setName(rSet.getString("name"));
				teacher.setSchool_cd(rSet.getString("school_cd"));
				// SchoolDao のインスタンスを生成
				SchoolDao schoolDao = new SchoolDao();
				//学校フィールドには学校コードで検索した学校インスタンスをセット
				teacher.setSchool(schoolDao.get(rSet.getString("school_cd")));
			}
		else{
			//リザルトセットが存在しない場合
			//Teacherインスタンスにnullをセット
			teacher = null;
		}
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
		return teacher;
	}
}