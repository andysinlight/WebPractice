package dao;

import bean.Category;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andy on 2017/3/17.
 */
public class CategoryDao {
    private QueryRunner mRunner = new QueryRunner(DBUtil.getDataSource());

    public boolean addCategory(Category category) {
        int update = 0;
        try {
            update = mRunner.update("insert into category (id,name,des) value(?,?,?)",
                    category.getId(),
                    category.getName(),
                    category.getDes()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update > 0;
    }

    public boolean deleteCategory(String id) {
        int update = 0;
        try {
            update = mRunner.update("delete from category where id =?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update>0;
    }

    public boolean updateCategory(Category category) {
        return false;
    }

    public Category getCategoryByID(String id) {
        try {
            return mRunner.query("select * from category where id = ?", new ResultSetHandler <Category>() {

                public Category handle(ResultSet resultSet) throws SQLException {
                    if(resultSet.next()) {
                        Category category = new Category();
                        category.setId(resultSet.getString(1));
                        category.setName(resultSet.getString(2));
                        category.setDes(resultSet.getString(3));
                        return category;
                    }
                    return null;
                }
            },id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<Category> getCategories() {
        try {
            return mRunner.query("select * from category", new ResultSetHandler<List<Category>>() {

                public List<Category> handle(ResultSet resultSet) throws SQLException {
                    ArrayList<Category> categories = new ArrayList<Category>();
                    while (resultSet.next()) {
                        Category category = new Category();
                        category.setId(resultSet.getString(1));
                        category.setName(resultSet.getString(2));
                        category.setDes(resultSet.getString(3));
                        categories.add(category);
                    }
                    return categories;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
