package com.yuan.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.yuan.beans.Category;
import com.yuan.util.DBUtil;

public class CategoryDao {
	QueryRunner runner = new QueryRunner(DBUtil.getDataSource());

	public boolean addCategory(Category category) {
		try {
			runner.update("insert into category(id,name,des) values(?,?,?)",
					category.getId(),
					category.getName(),
					category.getDes());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteCategory(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateCategory(Category category) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Category> getCategorys() {
		// TODO Auto-generated method stub
		return null;
	}

}
