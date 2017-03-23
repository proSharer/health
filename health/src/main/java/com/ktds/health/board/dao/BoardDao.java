package com.ktds.health.board.dao;

import java.util.List;

import com.ktds.health.board.vo.BoardSearchVO;
import com.ktds.health.board.vo.BoardVO;

public interface BoardDao {
	
	// 보드에 일지 추가
	public int insertNewBoard(BoardVO boardVO);
	// 보드 페이지 만들기
	public int selsctAllBoardsCount(BoardSearchVO boardSearchVO);
	// 
	public List<BoardVO> selectAllBoards(BoardSearchVO boardSearchVO);
	// 
	public BoardVO selectOneBoard(String boardId);
	// 보드 삭제
	public int deleteOneBoard(String boardId);
	
	

}
