package com.jxp.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Book;
import util.ConUtil;

public class JdbcTest {
	
	
	
	public static void add(Book book) throws FileNotFoundException {
		Connection connection = ConUtil.getCon();	
		String sqlString = "insert into t_book (id,name,content,pic) values (?,?,?,?)";
		PreparedStatement preparedStatement;
		try {
			connection.setAutoCommit(false);//不自动提交
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setInt(1, book.getId());
			preparedStatement.setString(2, book.getNameString());
			File content = book.getContentFile();
			InputStream inputStream = new FileInputStream(content);
			preparedStatement.setAsciiStream(3, inputStream, content.length());//上传文本
			 
			File picFile = book.getPicFile();
			InputStream picInputStream = new FileInputStream(picFile);
			preparedStatement.setBinaryStream(4, picInputStream, picFile.length());//图片上传
			
			preparedStatement.executeUpdate();
			ConUtil.ConClose(preparedStatement, connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();//错误回滚
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
				
	}
	
	public static void update(Book book) throws FileNotFoundException {
		Connection connection = ConUtil.getCon();
		String sqlString = "update t_book set name=?,content=?,pic=?where id = ? ";
		PreparedStatement preparedStatement;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, book.getNameString());
			File content = book.getContentFile();
			InputStream inputStream = new FileInputStream(content);
			preparedStatement.setAsciiStream(2, inputStream, content.length());//上传文本
			
			File picFile = book.getPicFile();
			InputStream picInputStream = new FileInputStream(picFile);
			preparedStatement.setBinaryStream(3, picInputStream, picFile.length());//图片上传
			
			preparedStatement.setInt(4, book.getId());
			preparedStatement.executeUpdate();
			ConUtil.ConClose(preparedStatement, connection);
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		
	}
	
	public static void del(int id) {
		Connection connection = ConUtil.getCon();
		String sqlString = "delete from t_book where id=?";
		PreparedStatement preparedStatement;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			ConUtil.ConClose(preparedStatement, connection);
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}
	
	public static void check(int id) throws IOException {
		Connection connection = ConUtil.getCon();
		String sqlString = "select * from t_book where id = ?";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				
				Clob clob = resultSet.getClob("content");
				String conString = clob.getSubString(1, (int)clob.length());
				
				System.out.println(conString);
//				System.out.println("id: "+ resultSet.getInt("id") + "name: " + resultSet.getString("name") + "content: " + conString);	
				
				Blob blob = resultSet.getBlob("pic");
				FileOutputStream picFileOutputStream = new FileOutputStream(new File("D:\\pic.jpg"));
				picFileOutputStream.write(blob.getBytes(1, (int) blob.length()));
				picFileOutputStream.close();
				
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {			
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			ConUtil.ConClose(resultSet, preparedStatement, connection);
		}
		
	}
	
	public static void main(String[] args) throws SQLException, IOException {
		
		File contentFile = new File("E:\\eclipse-workspace\\JDBCsvn\\content.txt");
		File picFile = new File("E:\\eclipse-workspace\\JDBCsvn\\pic.png");
		
		Book book = new Book(3,"额额", contentFile, picFile);
		
		check(1);
	}

}
