package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConUtil {
	
	private static String passwordString = "123456";
	
	private static String userString = "root";
	
	private static String urlString = "jdbc:mysql://localhost:3306/test?characterEncoding=utf-8&serverTimezone=GMT";

	private static String nameString = "com.mysql.cj.jdbc.Driver";
			
	//获取数据连接
	public static Connection getCon() {
		Connection connection = null;
		try {
			Class.forName(nameString);
			System.out.println("驱动加载成功");
		} catch (ClassNotFoundException e) {
			System.out.println("驱动加载失败");
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(urlString, userString, passwordString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		
		return connection;
	
	}
	
	public static void ConClose(Statement statement,Connection connection) {
		
		try {
			statement.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection.commit();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void ConClose(ResultSet resultSet,Statement statement,Connection connection) {
		
		try {
			statement.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			resultSet.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection.commit();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
