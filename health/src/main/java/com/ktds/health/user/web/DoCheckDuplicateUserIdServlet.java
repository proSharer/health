package com.ktds.health.user.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.health.user.service.UserService;
import com.ktds.health.user.service.UserServiceImpl;

public class DoCheckDuplicateUserIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService;

	public DoCheckDuplicateUserIdServlet() {
		
		userService = new UserServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		boolean isDuplicate = userService.isDuplicateUserId(userId);
		
		StringBuffer json = new StringBuffer();
		json.append(" { ");
		json.append(" 	\"status\" : \"success\",  "); // 자바에서 따옴표 안 따옴표를 쓰는 방법
		json.append(" 	\"duplicated\" : " + isDuplicate);
		json.append(" 	} ");
		
		PrintWriter writer = response.getWriter();
		writer.write(json.toString());
		writer.flush();
		writer.close();
		
	}

}
