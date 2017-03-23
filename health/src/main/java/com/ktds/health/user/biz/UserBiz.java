package com.ktds.health.user.biz;

import com.ktds.health.user.vo.UserVO;

public interface UserBiz {
	
	// 신규 유저 가입.
	public boolean addNewUser(UserVO userVO);
	
	// 로그인시 유저 정보 불러오기.
	public UserVO getOneUser(UserVO userVO);
	
	// 최초 가입시 포인트지급.
	public boolean managePoint(String userId, int point);
	
	// 아이디의 중복체크를 확인.
	public boolean isDuplicateUserId(String userId);

}
