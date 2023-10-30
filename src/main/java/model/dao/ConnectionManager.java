package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private final static String URL = "jdbc:mysql://localhost:3306/task_db?useSSL=false&serverTimezone=JST";
	private final static String USER = "testuser";
	private final static String PASSWORD = "password";
	
	//データベースへの接続メソッド
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName("com,mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
}
