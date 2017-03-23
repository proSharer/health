package com.ktds.health.user.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.health.user.service.UserService;
import com.ktds.health.user.service.UserServiceImpl;
import com.ktds.health.user.vo.UserVO;

public class ViewSignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService;

	public ViewSignInServlet() {

		userService = new UserServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/user/signIn.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 유저 아이디와 패스워드를 jsp에서 받아서 온다.
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		
		UserVO userVO = new UserVO();
		
		userVO.setUserId(userId);
		userVO.setPassword(userPassword);
		// 설정한 두 값을 메소드에 전달해서 값을 받아온다.
		userVO = userService.getOneUser(userVO);
		// 세션을 생성해서 
		HttpSession session = request.getSession();
		session.setAttribute("_USER_", userVO);

		if (userVO == null) {
			response.sendRedirect("/helath/user/signIn");
		} else {
			response.sendRedirect("/helath/user/list");
		}

	}

}
