package controller;

import bean.Book;
import bean.Category;
import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import service.ManageService;
import service.imple.ManageSerciveImple;
import utils.TextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by Administrator on 2017/3/16.
 */
public class ManageServlet extends HttpServlet {
    ManageService mManageService = new ManageSerciveImple();
    /*static {
        InputStream inputStream = ManageServlet.class.getClassLoader().getResourceAsStream("constant.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            String[] splits = properties.getProperty("img_store_root_path").split("@");
            StringBuilder stringBuilder = new StringBuilder();
            for(int i=0;i<splits.length;i++){
//                img_store_root_path += splits[i]+File.separator;
                stringBuilder.append(splits[i]);
                if(i<splits.length-1){
                    stringBuilder.append(File.separator);
                }
            }

            img_store_root_path = stringBuilder.toString().substring(1,stringBuilder.length()-1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    private static String img_store_root_path;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    private void addBook(HttpServletRequest request, HttpServletResponse response) {
        Book book = new Book();
        Map<String,String> map = new HashMap();
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print(request.getRemoteAddr());
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if(!isMultipart){
            throw new RuntimeException("请检查您的表单的enctype属性，确定是multipart/form-data");
        }
        DiskFileItemFactory dfif = new DiskFileItemFactory();
        ServletFileUpload parser = new ServletFileUpload(dfif);

//		parser.setFileSizeMax(3*1024*1024);//设置单个文件上传的大小
        parser.setSizeMax(6*1024*1024);//多文件上传时总大小限制

        List<FileItem> items = null;
        try {
            items = parser.parseRequest(request);
        }catch(FileUploadBase.FileSizeLimitExceededException e) {
            out.write("上传文件超出了3M");
            return;
        }catch(FileUploadBase.SizeLimitExceededException e){
            out.write("总文件超出了6M");
            return;
        }catch (FileUploadException e) {
            e.printStackTrace();
            throw new RuntimeException("解析上传内容失败，请重新试一下");
        }

        //处理请求内容
        if(items!=null){
            for(FileItem item:items){
                if(item.isFormField()){
                    processFormField(item,map);
                }else{
                    processUploadField(item,map);
                }
            }
        }

        try {
            BeanUtils.copyProperties(book,map);
            mManageService.addBook(book);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        out.write("上传成功！");

    }

    private void processFormField(FileItem item,Map<String,String> paras) {
        String fieldName = item.getFieldName();//字段名
        String fieldValue;
        try {
            fieldValue = item.getString("UTF-8");
            paras.put(fieldName,fieldValue);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("不支持UTF-8编码");
        }
        System.out.println(fieldName+"="+fieldValue);
    }

    private void processUploadField(FileItem item, Map<String, String> map) {
        try {
            String fileName = item.getName();


            //用户没有选择上传文件时
            if(fileName!=null&&!fileName.equals("")){
                fileName = UUID.randomUUID().toString();

                //扩展名
                String extension = FilenameUtils.getExtension(fileName);
                //MIME类型
                String contentType = item.getContentType();

                if(contentType.startsWith("image/")){

                    //分目录存储：日期解决
                    //			Date now = new Date();
                    //			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    //
                    //			String childDirectory  = df.format(now);
                    img_store_root_path = getServletContext().getRealPath("imgs");

                    //按照文件名的hashCode计算存储目录
                    String childDirectory = makeChildDirectory(img_store_root_path,fileName);

                    String storeDirectoryPath = img_store_root_path+File.separator+childDirectory;
                    String path =getServletContext().getContextPath() + File.separator + "imgs" + File.separator + childDirectory;
                    map.put("path", path);
                    File storeDirectory = new File(storeDirectoryPath);
                    if(!storeDirectory.exists()){
                        storeDirectory.mkdirs();
                    }
                    System.out.println(fileName);
                    map.put("img_name",fileName);
                    item.write(new File(storeDirectoryPath+ File.separator+fileName));//删除临时文件
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("上传失败,请重试");
        }

    }

    //计算存放的子目录
    private String makeChildDirectory(String realPath, String fileName) {
        int hashCode = fileName.hashCode();
        int dir1 = hashCode&0xf;// 取1~4位
        int dir2 = (hashCode&0xf0)>>4;//取5~8位

        String directory = ""+dir1+File.separator+dir2;

        System.out.print("img_path"+directory);

        File file = new File(realPath,directory);
        if(!file.exists())
            file.mkdirs();

        return directory;
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
