import bean.Book;
import dao.BookDao;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/3/17.
 */
public class TestBookDao {

    BookDao mBookDao = new BookDao();

    @Test
    public void addBook() {
       /* 39139a8c-5eff-4b20-ad8f-312b1e865884
        5ad04019-7ac8-4ab5-b869-5341dfa0548b
        d13d2e6f-ea55-4134-9ded-dcedb5982ed8*/

     /*   Book book = new Book(UUID.randomUUID().toString(),
                "java",
                "一本学习java 的好书",
                "5ad04019-7ac8-4ab5-b869-5341dfa0548b",
                "/book",
                "book1",
                100f);*/
//        assert mBookDao.addBook(book);
    }

    @Test
    public void deleteBook() {
       /* 39139a8c-5eff-4b20-ad8f-312b1e865884
        5ad04019-7ac8-4ab5-b869-5341dfa0548b
        d13d2e6f-ea55-4134-9ded-dcedb5982ed8*/
//        0b70a0b9-b164-4d78-88da-f59a4caff706
        assert mBookDao.deleteBook("0b70a0b9-b164-4d78-88da-f59a4caff706");
    }

    @Test
    public void getBooks() {
        List<Book> books = mBookDao.getBooks(1, 2);
        assert books.size() == 2;
    }

    @Test
    public void getBookByID() {
        Book book = mBookDao.getBookByID("1");
        assert book != null;
    }


    @Test
    public void getBookByCategory() {
        List<Book> booksByCategory = mBookDao.getBooksByCategory("5ad04019-7ac8-4ab5-b869-5341dfa0548b", 0, 2);
        assert booksByCategory.size() > 0;
    }
}
