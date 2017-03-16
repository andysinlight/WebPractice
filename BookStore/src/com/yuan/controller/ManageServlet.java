package com.yuan.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;

import com.yuan.beans.Category;
import com.yuan.service.BusessesService;
import com.yuan.service.imples.BusessesServiceImple;

public class ManageServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String function = req.getParameter("op");
		if("addCategory".equals(function)){
			addCategory(req, resp);
		}else if("".equals(function)){
		}
	}
	
	
	
	private void addCategory(HttpServletRequest req,HttpServletResponse resp) {
//		req.getParameter(name);
		Category category = new Category();
			
		try {
				BeanUtils.copyProperties(category,req.getParameterMap());
				BusessesService service = new BusessesServiceImple();
				service.addCategory(category);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			System.out.print(category.toString());
	}
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
