package com.yuan.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuan.util.DBUtil;

public class ClientServlet extends HttpServlet {



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String result = "";
		try {
			Statement statement=   DBUtil.getConnect().createStatement();
			ResultSet resultSet =  statement.executeQuery("show tables");
			
			while (resultSet.next()) {
				result = result.concat(", "+resultSet.getString(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		resp.getWriter().write(result);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
