package service.imple;

import bean.Book;
import bean.Category;
import dao.BookDao;
import dao.CategoryDao;
import service.ManageService;
import utils.TextUtils;

import java.util.List;
import java.util.UUID;

/**
 * Created by andy on 2017/3/17.
 */
public class ManageSerciveImple implements ManageService {
    CategoryDao mCategoryDao = new CategoryDao();
    BookDao mBookDao = new BookDao();

    public boolean addCategory(Category category) {
        if(TextUtils.isEmpty(category.getId())){
            category.setId(UUID.randomUUID().toString());
        }
        return mCategoryDao.addCategory(category);
    }

    public boolean deleteCategory(String id) {
        return mCategoryDao.deleteCategory(id);
    }

    public boolean updateCategory(Category category) {
        return mCategoryDao.updateCategory(category);
    }

    public List<Category> getCategories() {
        return mCategoryDao.getCategories();
    }

    public Category getCategorieByID(String id) {
        return mCategoryDao.getCategoryByID(id);
    }


    public boolean addBook(Book book) {
        return mBookDao.addBook(book);
    }

    public boolean deleteBook(String id) {
        return false;
    }

    public List<Book> getBooks(int index) {
        return mBookDao.getBooks(index,5);
    }
}
