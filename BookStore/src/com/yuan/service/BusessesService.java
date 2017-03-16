package com.yuan.service;

import java.util.List;

import com.yuan.beans.Category;

public interface BusessesService {
	
	boolean addCategory(Category category);
	
	boolean deleteCategory(String id);
	
	boolean updateCategory(Category category);
	
	List<Category> getCategorys();

}
