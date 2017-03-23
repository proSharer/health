package com.ktds.health.board.vo;

import com.ktds.health.user.vo.UserVO;

public class BoardVO {

	private String boardId;
	private String userId;
	private String writeDate;

	private UserVO userVO;


	public UserVO getUserVO() {
		if (userVO == null) {
			userVO = new UserVO();
		}
		return userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}


}
