package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.UserBean;

public class UserDAO {
    /**
     *このメソッドでは入力されたユーザIDからユーザの情報全部を取得するときなどに使います。
 * ユーザIDを引数にユーザ情報を取得するメソッド
 * @param user_id
 * @return　ユーザ情報
 * @throws SQLException
 * @throws ClassNotFoundException
 */
public UserBean getUser(String user_id) throws SQLException, ClassNotFoundException {
    UserBean user = new UserBean();
	//SQL文user_idを指定して、レコードを取得
	    String sql = "select * from m_user where user_id = ?";
	//データベースへ接続
	    try(Connection con = ConnectionManager.getConnection();
	            PreparedStatement pstmt = con.prepareStatement(sql)){
	//受け取ったuser_idをSQL文へ代入
	        pstmt.setString(1, user_id);
	//SQL文を実行して実行結果を取得
	        ResultSet res = pstmt.executeQuery();
	        while(res.next()) {
	//実行結果よりそれぞれのカラムの値を取得
	            String password = res.getString("password");
	            String user_name = res.getString("user_name");
	//userオブジェクトへセット
	            user.setPassword(password);
	            user.setUser_id(user_id);
	            user.setUser_name(user_name);
	        }
	        return user;
	    }
	}
	
	/**
	 * ユーザIDを引数にユーザ名を取得するメソッド
	 * @param user_id
	 * @return　ユーザ名
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public String getUserName(String user_id) throws SQLException, ClassNotFoundException {
	//SQL文User_idよりuser_nameを取得
	    String sql = "select user_name from m_user where user_id = ?";
	//usre_nameの初期値をセット
	    String user_name ="不明";
	//データベースへ接続
	    try(Connection con = ConnectionManager.getConnection();
	            PreparedStatement pstmt = con.prepareStatement(sql)){
	//受け取ったuser_idをSQL文へ代入
	        pstmt.setString(1, user_id);
	//SQL文を実行して実行結果を取得
	        ResultSet res = pstmt.executeQuery();
	
	        while(res.next()) {
	//実行結果よりそれぞれのカラムの値を取得
	            user_name = res.getNString("user_name");
	        }
	    }
	    return user_name;
	}
	
	
	    /**
	     * ログイン認証用メソッド
	     * @param user_id
	     * @param password
	     * @return boolean型でtrueならログイン可能 falseならログイン不可能
	     * @throws SQLException
	     * @throws ClassNotFoundException
	     */
	public boolean logincheck(String user_id, String password) throws SQLException, ClassNotFoundException {
	//そもそも入力欄が空欄だった場合を除外する
	    if (user_id == null || user_id.length() == 0 || password == null || password.length() == 0){
	      return false;
	    }
	//SQL文　入力されたuser_id, passwordを指定してレコードを取得
	    String sql = "select * from m_user where user_id = ? && password = ?";
	//データベースへ接続
	    try (Connection con = ConnectionManager.getConnection();
	            PreparedStatement pstmt = con.prepareStatement(sql)){
	        pstmt.setString(1, user_id);
	        pstmt.setString(2, password);
	        ResultSet res = pstmt.executeQuery();
	//SQL文の実行結果があれば、ユーザが存在するのでtrue。結果が無いということはそのユーザは登録されていないのでfalse
	        if(res.next()) {
	            return true;
	        } else {
	            return false;
	        }
	    }
	}

}
