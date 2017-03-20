package controller;

import bean.Category;
import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;
import service.ManageService;
import service.imple.ManageSerciveImple;
import utils.TextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by Administrator on 2017/3/16.
 */
public class ClientServlet extends HttpServlet {
    ManageService mManageService = new ManageSerciveImple();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        DataSource dataSource = DBUtil.getDataSource();
//        resp.getWriter().write(dataSource.toString());
        String op = req.getParameter("op");
        if (TextUtils.isEmpty(op)) return;

        if ("add_category".equals(op)) {
            addCategory(req, resp);
        } else if ("show_categories".equals(op)) {
            showCategories(req, resp);
        } else if ("delete_category".equals(op)) {
            String id = req.getParameter("id");
            deleteCategory(id, resp);
        }else if("add_book".equals(op)){
            addBook(req,resp);
        }

    }

    private void addBook(HttpServletRequest req, HttpServletResponse resp) {
//        req.get


    }

    private void deleteCategory(String id, HttpServletResponse resp) {
        if (!mManageService.deleteCategory(id)) {
            resp.setStatus(500);
        }
    }

    private void showCategories(HttpServletRequest req, HttpServletResponse resp) {
        List<Category> categories = mManageService.getCategories();
        Gson gson = new Gson();
        String s = gson.toJson(categories);
        try {
            resp.getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addCategory(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Category category = new Category();
            BeanUtils.copyProperties(category, req.getParameterMap());
            mManageService.addCategory(category);
            String url = req.getContextPath() + "/pages/manage/success.html";
            System.out.print(url);
            resp.sendRedirect(url);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
