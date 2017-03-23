package com.ktds.health.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ktds.health.user.vo.UserVO;

public class UserDaoImpl implements UserDao {

	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String user = "HEALTH";
	private static final String password = "health";

	@Override
	public int insertNewUser(UserVO userVO) {

		loadOracle();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = DriverManager.getConnection(url, user, password);

			StringBuffer query = new StringBuffer();
			query.append(" INSERT	INTO USR ");
			query.append(" 				( ");
			query.append(" 					USR_ID ");
			query.append(" 					, USR_NM ");
			query.append(" 					, USR_PW ");
			query.append(" 					, HGHT ");
			query.append(" 					, WGHT ");
			query.append(" 					, USR_PNT ");
			query.append(" 					, ATHRZTN_ID ");
			query.append(" 				) ");
			query.append(" VALUES		  ");
			query.append(" 				( ");
			query.append(" 					? ");
			query.append(" 					, ? ");
			query.append(" 					, ? ");
			query.append(" 					, ? ");
			query.append(" 					, ? ");
			query.append(" 					, 0 ");
			query.append(" 					, 'AT-2017032220-000010' ");
			query.append(" 				) ");

			stmt = conn.prepareStatement(query.toString());

			stmt.setString(1, userVO.getUserId());
			stmt.setString(2, userVO.getUserName());
			stmt.setString(3, userVO.getPassword());
			stmt.setInt(4, userVO.getHght());
			stmt.setInt(5, userVO.getWght());

			return stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			close(conn, stmt, null);
		}
	}

	@Override
	public UserVO selectOneUser(UserVO userVO) {

		loadOracle();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			conn = DriverManager.getConnection(url, user, password);
			StringBuffer query = new StringBuffer();

			query.append(" SELECT	U.USR_ID ");
			query.append(" 			, U.USR_NM ");
			query.append(" 			, U.USR_PW ");
			query.append(" 			, U.USR_PNT ");
			query.append(" 			, U.HGHT ");
			query.append(" 			, U.WGHT ");
			query.append(" 			, U.ATHRZTN_ID ");
			query.append(" 			, AT.ATHRZTN_ID ");
			query.append(" 			, AT.ATHRZTN_NM ");
			query.append(" 			, AT.PRNT_ATHRZTN_ID ");
			query.append(" FROM		USR U ");
			query.append(" 			, ATHRZTN AT ");
			query.append(" WHERE	U.ATHRZTN_ID = AT.ATHRZTN_ID ");
			query.append(" AND		USR_ID = ? ");
			query.append(" AND		USR_PW = ? ");

			stmt = conn.prepareStatement(query.toString());

			stmt.setString(1, userVO.getUserId());
			stmt.setString(2, userVO.getPassword());

			rs = stmt.executeQuery();

			UserVO user = null;

			if (rs.next()) {
				user = new UserVO();
				user.setUserId(rs.getString("USR_ID"));
				user.setUserName(rs.getString("USR_NM"));
				user.setPassword(rs.getString("USR_PW"));
				user.setUsrPoint(rs.getInt("USR_PNT"));
				user.setHght(rs.getInt("HGHT"));
				user.setWght(rs.getInt("WGHT"));
				user.setAuthorizationId("ATHRZTN_ID");
				
				user.getAuthorizationVO().setAuthorizationId(rs.getString("ATHRZTN_ID"));
				user.getAuthorizationVO().setAuthorizationName(rs.getString("ATHRZTN_NM"));
				user.getAuthorizationVO().setParentAuthorizationId(rs.getString("PRNT_ATHRZTN_ID"));

			}
			return user;

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			close(conn, stmt, rs);
		}
	}

	@Override
	public int updatePoint(String userId, int point) {

		loadOracle();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = DriverManager.getConnection(url, user, password);

			StringBuffer query = new StringBuffer();

			query.append(" UPDATE	USR ");
			query.append(" SET		USR_PNT = USR_PNT + ? ");
			query.append(" WHERE	USR_ID = ? ");

			stmt = conn.prepareStatement(query.toString());

			stmt.setInt(1, point);
			stmt.setString(2, userId);

			return stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			close(conn, stmt, null);
		}
	}
	
	@Override
	public int selectCountByUserId(String userId) {
		
		System.out.println(userId);
		loadOracle();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			
			StringBuffer query = new StringBuffer();
			
			query.append(" SELECT	COUNT(1) CNT   ");
			query.append(" FROM		USR            ");
			query.append(" WHERE	USR_ID = ?     ");
			
			stmt = conn.prepareStatement(query.toString());
			
			stmt.setString(1, userId);
			
			rs = stmt.executeQuery();
			
			if(rs.next()){
				return rs.getInt("CNT");
			}
			
			return 0;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			close(conn, stmt, rs);
		}
	}

	public void loadOracle() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public void close(Connection conn, PreparedStatement stmt, ResultSet rs) {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

}
