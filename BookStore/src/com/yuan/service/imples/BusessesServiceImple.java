package com.yuan.service.imples;

import java.util.List;
import java.util.UUID;

import com.yuan.beans.Category;
import com.yuan.dao.CategoryDao;
import com.yuan.service.BusessesService;

public class BusessesServiceImple implements BusessesService {
	CategoryDao categoryDao = new CategoryDao();
	@Override
	public boolean addCategory(Category category) {
		if(!"".equals(category.getId())){
			return categoryDao.addCategory(category);
		}else{
			category.setId(UUID.randomUUID().toString());
			return categoryDao.addCategory(category);
		}
		
	}

	@Override
	public boolean deleteCategory(String id) {
		return categoryDao.deleteCategory(id);
	}

	@Override
	public boolean updateCategory(Category category) {
		return categoryDao.updateCategory(category);
	}

	@Override
	public List<Category> getCategorys() {
		return categoryDao.getCategorys();
	}

}
