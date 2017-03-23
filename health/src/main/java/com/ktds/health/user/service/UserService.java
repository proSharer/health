package com.ktds.health.user.service;

import com.ktds.health.user.vo.UserVO;

public interface UserService {
	
	public boolean addNewUser(UserVO userVO);
	
	public UserVO getOneUser(UserVO userVO);
	
	public boolean managePoint(String userId, int point);
	
	public boolean isDuplicateUserId(String userId);

}
