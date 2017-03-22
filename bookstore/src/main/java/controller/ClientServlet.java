package controller;

import bean.Book;
import bean.Category;
import com.google.gson.Gson;
import service.ManageService;
import service.imple.ManageSerciveImple;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by andy on 2017/3/21.
 */
public class ClientServlet extends HttpServlet {
    ManageService mService = new ManageSerciveImple();
    private Object mBooks;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("op");
        if ("show_home".equals(op)) {
            PrintWriter writer = resp.getWriter();
            writer.write(getBooks());
            writer.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private String getBooks() {
        List<Book> books = mService.getBooks(0);
        for(int i=0;i<books.size();i++){
            Book book = books.get(i);
            Category category = mService.getCategorieByID(book.getCategory());
            if(category!=null)book.setCategory_name(category.getName());
        }


        Gson gson = new Gson();
        String s = gson.toJson(books);
        s = s.replaceAll(getServletContext().getContextPath(),"");
        return s;
    }
}
