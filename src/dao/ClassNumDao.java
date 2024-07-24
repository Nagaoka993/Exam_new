package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;

public class ClassNumDao extends Dao{
	//学校コードを元にクラス番号の一覧を取得する
	public List<String> filter(School school) throws Exception{
		//リストを初期化
		List<String> list  = new ArrayList<>();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//コネクションを初期化する
		Connection connection = null;
		try{
			//データベースへのコネクションを確立
			connection = getConnection();
			//cdを取得する
			String school_cd = school.getCd();
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select class_num from class_num where school_cd=?");
			//プリペアードステートメントに学校コードをバインド
			statement.setString(1, school_cd);
			//プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();
			while(rSet.next()){
				//リザルトセットが存在する場合
				//リストにクラス番号を格納する
				list.add(rSet.getString("class_num"));
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
