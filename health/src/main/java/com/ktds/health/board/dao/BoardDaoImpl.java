package com.ktds.health.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ktds.health.board.vo.BoardSearchVO;
import com.ktds.health.board.vo.BoardVO;

public class BoardDaoImpl implements BoardDao {
	
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String user = "HEALTH";
	private static final String password = "health";

	@Override
	public int insertNewBoard(BoardVO boardVO) {
		
		loadOracle();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			
			StringBuffer query = new StringBuffer();
			query.append(" INSERT	INTO	BOARD ");
			query.append(" 					( ");
			query.append(" 						B.BOARD_ID ");
			query.append(" 						, B.USR_ID ");
			query.append(" 						, B.WRT_DT ");
			query.append(" 						, B.ATHRZTN_ID ");
			query.append(" 						, U.USR_ID U_USR_ID ");
			query.append(" 						, AT.ATHRZTN_NM ");
			query.append(" 					) ");
			query.append(" VALUES 			  ");
			query.append(" 					( ");
			query.append(" 						? ");
			query.append(" 						, ? ");
			query.append(" 						, TO_CHAR(?, 'YYYYMMDDHH24') ");
			query.append(" 						, ? ");
			query.append(" 						, ? ");
			query.append(" 						, ? ");
			query.append(" 						) ");
			query.append(" 	WHERE ");
			
			
			stmt = conn.prepareStatement(query.toString());
			
			stmt.setString(1, boardVO.getBoardId());
			stmt.setString(2, boardVO.getUserId());
			stmt.setString(3, boardVO.getWriteDate());
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
		return 0;
	}

	@Override
	public int selsctAllBoardsCount(BoardSearchVO boardSearchVO) {
		return 0;
	}

	@Override
	public List<BoardVO> selectAllBoards(BoardSearchVO boardSearchVO) {
		return null;
	}

	@Override
	public BoardVO selectOneBoard(String boardId) {
		return null;
	}

	@Override
	public int deleteOneBoard(String boardId) {
		return 0;
	}
	
	public void loadOracle(){
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	public void close(Connection conn, PreparedStatement stmt, ResultSet rs){
		if(rs != null){
		try {
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		}
		if( stmt != null){
		try {
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		}
		if( conn != null ){
		try {
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		}
		
	}

}
