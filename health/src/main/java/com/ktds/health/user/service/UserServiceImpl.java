package com.ktds.health.user.service;

import com.ktds.health.user.biz.UserBiz;
import com.ktds.health.user.biz.UserBizImpl;
import com.ktds.health.user.vo.UserVO;

public class UserServiceImpl implements UserService {
	
	private UserBiz userBiz;
	
	public UserServiceImpl(){
		userBiz = new UserBizImpl();
	}

	@Override
	public boolean addNewUser(UserVO userVO) {
		return userBiz.addNewUser(userVO);
	}

	@Override
	public UserVO getOneUser(UserVO userVO) {
		return userBiz.getOneUser(userVO);
	}

	@Override
	public boolean managePoint(String userId, int point) {
		return userBiz.managePoint(userId, point);
	}

	@Override
	public boolean isDuplicateUserId(String userId) {
		return userBiz.isDuplicateUserId(userId);
	}

}
