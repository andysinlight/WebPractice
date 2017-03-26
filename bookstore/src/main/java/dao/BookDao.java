package dao;

import bean.Book;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */
public class BookDao {
    private QueryRunner mQueryRunner = new QueryRunner(DBUtil.getDataSource());

    public boolean addBook(Book book) {
        int update = 0;
        try {
            update = mQueryRunner.update("insert into book(name,des,category,price,path,img_name) values(?,?,?,?,?,?)",
                    book.getName(),
                    book.getDes(),
                    book.getCategory(),
                    book.getPrice(),
                    book.getPath(),
                    book.getImg_name()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update > 0;
    }

    public boolean deleteBook(String id) {
        int update = 0;
        try {
            update = mQueryRunner.update("delete from book where id=?", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update > 0;
    }

    public boolean updateBook(Book book) {
        int update = 0;
        try {
            update = mQueryRunner.update("insert into book(id,name,des,category,price) values(?,?,?,?,?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update > 0;
    }

    public Book getBookByID(String id) {
        try {
            return mQueryRunner.query("select id,name,des,category,path,img_name,price from book where id=?",
                    new ResultSetHandler<Book>() {
                        public Book handle(ResultSet resultSet) throws SQLException {
                            Book book = null;
                            while (resultSet.next()) {
                                return new Book(resultSet.getLong(1),
                                        resultSet.getString(2),
                                        resultSet.getString(3),
                                        resultSet.getString(4),
                                        resultSet.getString(5),
                                        resultSet.getString(6),
                                        resultSet.getFloat(7));
                            }
                            return book;
                        }
                    }, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Book> getBooksByCategory(String category, int start, int count) {
        try {
            return mQueryRunner.query("select id,name,des,category,path,img_name,price from book  where category = ?limit ?,?",
                    new ResultSetHandler<List<Book>>() {
                        public List<Book> handle(ResultSet resultSet) throws SQLException {
                            List<Book> books = new ArrayList<Book>();
                            while (resultSet.next()) {
                                Book book = new Book(resultSet.getLong(1),
                                        resultSet.getString(2),
                                        resultSet.getString(3),
                                        resultSet.getString(4),
                                        resultSet.getString(5),
                                        resultSet.getString(6),
                                        resultSet.getFloat(7));
                                books.add(book);
                            }
                            return books;
                        }
                    }, category, start, count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<Book>();
    }

    public List<Book> getBooks(int start, int count) {
        try {
            return mQueryRunner.query("select id,name,des,category,path,img_name,price from book limit ?,?",
                    new ResultSetHandler<List<Book>>() {
                        public List<Book> handle(ResultSet resultSet) throws SQLException {
                            List<Book> books = new ArrayList<Book>();
                            while (resultSet.next()) {
                                Book book = new Book(resultSet.getLong(1),
                                        resultSet.getString(2),
                                        resultSet.getString(3),
                                        resultSet.getString(4),
                                        resultSet.getString(5),
                                        resultSet.getString(6),
                                        resultSet.getFloat(7));
                                books.add(book);
                            }
                            return books;
                        }
                    }, start, count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<Book>();
    }


    public int getRecordsCount() {
        try {
            return mQueryRunner.query("select count(1) from book",
                    new ResultSetHandler<Integer>() {
                        private int mCount;

                        public Integer handle(ResultSet resultSet) throws SQLException {
                            while (resultSet.next()) {
                                mCount = resultSet.getInt(1);
                            }
                            return mCount;
                        }
                    });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
