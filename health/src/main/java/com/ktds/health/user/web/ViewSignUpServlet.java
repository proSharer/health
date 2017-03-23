package com.ktds.health.user.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.health.common.web.MultipartHttpServletRequest;
import com.ktds.health.user.service.UserService;
import com.ktds.health.user.service.UserServiceImpl;
import com.ktds.health.user.vo.UserVO;

public class ViewSignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService;

	public ViewSignUpServlet() {
		userService = new UserServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/user/signUp.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MultipartHttpServletRequest multipart = new MultipartHttpServletRequest(request);
		
		String userId = multipart.getParameter("userId");
		String userName = multipart.getParameter("userName");
		String userPassword = multipart.getParameter("userPassword1");
		String height = multipart.getParameter("userHeight");
		String weight = multipart.getParameter("userWeight");
		
		// 아이디 공란 체크
		if (userId == null || userId.length() == 0) {
			response.sendRedirect("/health/user/signUp?errorCode=0");
			return;
		}
		// 이름 공란 체크
		if (userName == null || userName.length() == 0) {
			response.sendRedirect("health/user/signUp?errorCode=1");
			return;
		}
		// 패스워드 공란 체크
		if (userPassword == null || userName.length() == 0) {
			response.sendRedirect("/health/user/signUp?errorCode=2");
			return;
		}
		// 아이디 중복 체크
		if (userService.isDuplicateUserId(userId)) {
			response.sendRedirect("/health/user/signUp?errorCode=3");
			return;
		}

		UserVO userVO = new UserVO();

		userVO.setUserId(userId);
		userVO.setUserName(userName);
		userVO.setPassword(userPassword);
		userVO.setHght(Integer.parseInt(height));
		userVO.setWght(Integer.parseInt(weight));
		

		if (userService.addNewUser(userVO)) {
			response.sendRedirect("/health/board/list");
		} else {
			response.sendRedirect("/health/user/signUp");
		}

	}

}
