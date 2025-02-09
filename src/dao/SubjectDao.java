package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;

public class SubjectDao extends Dao{

	public Subject get(String cd, School school) throws Exception{
		Subject subject = new Subject();
		//データベースへのコネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		school.getName();
		try{
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select*from subject where cd=?");
			//プリペアードステートメントに科目をバインド
			statement.setString(1, cd);
			//プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();

			if(rSet.next()){
				//リザルトセットが存在する場合
				//科目インスタンスに検索結果をセット
				subject.setCd(rSet.getString("cd"));
				subject.setName(rSet.getString("name"));
				// SchoolDao のインスタンスを生成
				SchoolDao schoolDao = new SchoolDao();
				//学校フィールドには学校コードで検索した学校インスタンスをセット
				subject.setSchool(schoolDao.get(rSet.getString("school_cd")));
			}else{
				//リザルトセットが存在しない場合
				//科目インスタンスにnullをセット
				subject = null;
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
		return subject;
	}
	//↑のgetメソッドの、科目コードではなく科目名で取得できるようしたver.
	public Subject get2(String subject_name, School school) throws Exception{
		Subject subject = new Subject();
		//データベースへのコネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		school.getName();
		try{
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select*from subject where name=?");
			//プリペアードステートメントに科目をバインド
			statement.setString(1, subject_name);
			//プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();

			if(rSet.next()){
				//リザルトセットが存在する場合
				//科目インスタンスに検索結果をセット
				subject.setCd(rSet.getString("cd"));
				subject.setName(rSet.getString("name"));
				// SchoolDao のインスタンスを生成
				SchoolDao schoolDao = new SchoolDao();
				//学校フィールドには学校コードで検索した学校インスタンスをセット
				subject.setSchool(schoolDao.get(rSet.getString("school_cd")));
			}else{
				//リザルトセットが存在しない場合
				//科目インスタンスにnullをセット
				subject = null;
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
		return subject;
	}

	private List<Subject> postFilter(ResultSet rSet, School school) throws Exception {
		//リストを初期化
		List<Subject> list = new ArrayList<>();
		try {
			//リザルトセットを全権走査
			while(rSet.next()) {
				//科目インスタンスを初期化
				Subject subject = new Subject();
				//科目インスタンスに検索結果をセット
				subject.setCd(rSet.getString("cd"));
				subject.setName(rSet.getString("name"));
				subject.setSchool(school);
				//リストに追加
				list.add(subject);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Subject> filter(Teacher teacher) throws Exception {
		//リストを初期
		List<Subject> list = new ArrayList<>();
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//リザルトセット
		ResultSet rSet = null;
		//SQL文をソート
		String order = "order by cd asc";
		//TeacherDAOのインスタンスを初期化
		TeacherDao teacherDao = new TeacherDao();
		SchoolDao schoolDao = new SchoolDao();
		//schoolbeanのインスタンスを初期化
		School school = new School();
		try {
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select * from subject where school_cd = ?" + order);
			//プリペアードステートメントに学校コードをバインド
			statement.setString(1, teacher.getSchool_cd());
			//プリペアードステートメントを実行
			rSet = statement.executeQuery();
			//
			school = schoolDao.get(teacher.getSchool_cd());

			//リストへの格納処理を実行
			list = postFilter(rSet, school);
		} catch (Exception e) {
			throw e;
		} finally {
			//プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			//コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return list;

	}

	public boolean save (Subject subject) throws Exception {
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//実行数
		int count = 0;

		try {
			//subjectテーブルから科目を取得
			Subject old = get(subject.getCd(),subject.getSchool());
			if (old == null) {
				//科目が存在しなかった場合
				//プリペアードステートメントにINSERT文をセット
				statement = connection.prepareStatement(
						"insert into subject(cd, name, school_cd) values(?, ?, ?)");
				//プリペアードステートメントに値をバインド
				statement.setString(1, subject.getCd());
				statement.setString(2, subject.getName());
				statement.setString(3, subject.getSchool().getCd()); //School_cdを追加
			} else {
				//科目が存在した場合
				//プリペアードステートメントにUPDATE文を設置
				statement = connection.prepareStatement(
						"update subject set name = ? where cd = ? and school_cd = ?");
				//プリペアードステートメントに値をバインド
				statement.setString(1, subject.getName());
				statement.setString(2, subject.getCd()); //更新対象の科目コード
				statement.setString(3,subject.getSchool().getCd());
			}
			//プリペアードステートメントを実行
			count = statement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			//プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		if (count > 0) {
			//実行件数が1件以上ある場合
			return true;
		} else {
			return false;
		}
	}
	public boolean delete(Subject subject) throws Exception {
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//実行件数
		int count = 0;

		try {
			//プリペアードステートメントにDELETE文をセット
			statement = connection.prepareStatement(
					"delete from subject where cd  = ?");
			//プリペアードステートメントに値をバインド
			statement.setString(1, subject.getCd());
			//プリペアードステートメントを実行
			count = statement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			//プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			//コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		if (count > 0) {
			//実行件数が1以上ある場合
			return true;
		} else {
			return false;
		}
	}
	//teacherの所属している学校の科目名一覧を取得するメソッドを追記*testlistactionで使用
	public ArrayList<String> getSubjectNameList(Teacher teacher)throws Exception{
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//リザルトセット
		ResultSet rSet = null;
		ArrayList<String> list = new ArrayList<>();
		try {
			//プリペアードステートメントにselect文を設置
			statement = connection
					.prepareStatement("select name from subject where school_cd=?");
				//プリペアードステートメントに値をバインド
			statement.setString(1,teacher.getSchool_cd());
			//プライベートステートメントを実行
			rSet = statement.executeQuery();
			//結果をリストに格納する
			while(rSet.next()){
				list.add(rSet.getString("name"));
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
		return list;
	}
}