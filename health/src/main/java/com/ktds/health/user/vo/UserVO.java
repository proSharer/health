package com.ktds.health.user.vo;

import com.ktds.health.authorization.vo.AuthorizationVO;

public class UserVO {

	private String userId;
	private String userName;
	private String password;
	private String authorizationId;
	private int usrPoint;
	private int hght;
	private int wght;

	private AuthorizationVO authorizationVO;

	public AuthorizationVO getAuthorizationVO() {
		if (authorizationVO == null) {
			authorizationVO = new AuthorizationVO();
		}
		return authorizationVO;
	}

	public void setAuthorizationVO(AuthorizationVO authorizationVO) {
		this.authorizationVO = authorizationVO;
	}

	public int getHght() {
		return hght;
	}

	public void setHght(int hght) {
		this.hght = hght;
	}

	public int getWght() {
		return wght;
	}

	public void setWght(int wght) {
		this.wght = wght;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthorizationId() {
		return authorizationId;
	}

	public void setAuthorizationId(String authorizationId) {
		this.authorizationId = authorizationId;
	}

	public int getUsrPoint() {
		return usrPoint;
	}

	public void setUsrPoint(int usrPoint) {
		this.usrPoint = usrPoint;
	}

}
