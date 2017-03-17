package service;

import bean.Category;

import java.util.List;

/**
 * Created by andy on 2017/3/17.
 */
public interface ManageService {
    boolean addCategory(Category category);
    boolean deleteCategory(String id);
    boolean updateCategory(Category category);
    List<Category> getCategories();
}
