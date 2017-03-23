package com.ktds.health.user.dao;

import com.ktds.health.user.vo.UserVO;

public interface UserDao {
	
	// 신규 유저 추가.
	public int insertNewUser(UserVO userVO);
	// 유저 
	public UserVO selectOneUser(UserVO userVO);
	// 유저 포인트
	public int updatePoint(String userId, int point);
	// 아이디 중복체크
	public int selectCountByUserId(String userId);

}
