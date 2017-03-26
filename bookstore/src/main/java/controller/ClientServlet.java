package controller;

import bean.Book;
import bean.Category;
import bean.Page;
import com.google.gson.Gson;
import service.ManageService;
import service.imple.ManageSerciveImple;
import utils.TextUtils;

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
        int index = 0;
        String index_in = req.getParameter("index");
        if (!TextUtils.isEmpty(index_in)) {
            index = Integer.valueOf(req.getParameter("index"));
        }
        if ("show_home".equals(op)) {
            PrintWriter writer = resp.getWriter();
            String bookID = req.getParameter("id");
            writer.write(getBooks(bookID,index));
            writer.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private String getBooks(String cId,int index) {
        Page<Book> page;
        if (TextUtils.isEmpty(cId)) {
            page = mService.getBooks(index);

        } else {
            page = mService.getBooksByCategoty(index, cId);
        }

        for (int i = 0; i < page.getData().size(); i++) {
            Book book =  page.getData().get(i);
            Category category = mService.getCategorieByID(book.getCategory());
            if (category != null) book.setCategory_name(category.getName());
        }
        Gson gson = new Gson();
        String s = gson.toJson(page);
        s = s.replaceAll(getServletContext().getContextPath(), "");
        return s;
    }
}
