package com.yuan.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBUtil {
	static DataSource dataSource ;
	static{
		InputStream input = DBUtil.class.getClassLoader().getResourceAsStream("db_connect.properties");
		Properties properties = new Properties();
		try {
			properties.load(input);
			dataSource = BasicDataSourceFactory.createDataSource(properties);
			System.out.print("成功加载数据库配置"+dataSource.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static DataSource getDataSource(){
		return dataSource;
	}
	
	public static Connection getConnect(){
		Connection connection =null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}

	
}
