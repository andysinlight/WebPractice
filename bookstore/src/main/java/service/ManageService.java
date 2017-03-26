package service;

import bean.Book;
import bean.Category;
import bean.Page;

import java.util.List;

/**
 * Created by andy on 2017/3/17.
 */
public interface ManageService {
    boolean addCategory(Category category);
    boolean deleteCategory(String id);
    boolean updateCategory(Category category);
    List<Category> getCategories();
    Category getCategorieByID(String id);

    boolean addBook(Book book);
    boolean deleteBook(String id);
    Page<Book> getBooks(int index);
    Page<Book> getBooksByCategoty(int index, String category);
}
