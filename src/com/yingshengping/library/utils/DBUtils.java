package com.yingshengping.library.utils;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DBUtils {

	private static String driver;
	private static String url;
	private static String user;
	private static String pass;
	private static Connection con = null;
	
	static {
		try {
			File file = new File("resources/db.properties");
			FileInputStream fis = new FileInputStream(file);
			Properties pro = new Properties();
			pro.load(fis);
			
			driver = pro.getProperty("driver");
			url = pro.getProperty("url");
			user = pro.getProperty("user");
			pass = pro.getProperty("pass");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void connect() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);
		con.setAutoCommit(false);
	}
	
	public static void commit() throws SQLException {
		if(con != null) {
			con.commit();
		}
	}
	
	public static void rollback() throws SQLException {
		if(con != null) {
			con.rollback();
		}
	}
	
	public static void close() throws SQLException {
		if(con != null) {
			con.close();
		}
	}
	
	public static int execute(String sql, List<Object> params) throws SQLException {
		int result = 0;
		
		if(con == null) {
			return result;
		}
		
		if(sql == null || sql.length() == 0) {
			return result;
		}
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		if(params != null && params.size() != 0 ) {
			int i = 1;
			for(Object o : params) {
				pstmt.setObject(i, o);
			}
		}
		
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}
	
	public static List<Map<String, Object>> executeQuery(String sql, List<Object> params) throws SQLException{
		List<Map<String, Object>> result = null;
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		int i = 1;
		for(Object o : params) {
			pstmt.setObject(i, o);
			i++;
		}
		
		ResultSet rs = pstmt.executeQuery();
		if(rs == null) {
			return result;
		}
		
		result = new ArrayList<>();
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		while(rs.next()) {
			Map<String, Object> m = new HashMap<>();
			for(int j = 1 ; j <= count ; j++) {
				m.put(rsmd.getColumnName(j), rs.getObject(j));
			}
			result.add(m);
		}
		
		return result;
	}
	
}
