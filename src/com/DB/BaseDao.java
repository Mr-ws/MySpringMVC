/**  

* <p>Title: DBUtil.java</p>  

* <p>Description: </p>  

* <p>Copyright: Copyright (c) 2017</p>  
 

* @author 魏帅  

* @date 2018年6月6日  
   
*/
package com.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bean.MyDataSource;
import com.zaxxer.hikari.HikariConfig;

/**
 * 
 * <p>
 * Title: DBUtil
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * @author 魏帅
 * 
 * @date 2018年6月6日
 * 
 */
public class BaseDao {

	private DBHelp dbHelp;

	/**
	 * 
	 */
	public BaseDao(HikariConfig config) {
		// TODO Auto-generated constructor stub
		dbHelp = new DBHelp(config);
	}

	public <T> T getJAVABean(String sql, Class<T> clzz, String... params)
			throws SQLException, InstantiationException, IllegalAccessException {
		List<Map<String, Object>> rs = executeSql(sql, params);
		int row = rs.size();
		if (row > 1) {
			throw new SQLException(" Expect to get 1 row, and actually get " + row + " rows ");
		}

		// 因为已经 将 rs 的指针定位到第一个所以 不需要 循环来做 定位指针

		T mapToBean = ResultSetUtil.mapToBean(rs.get(0), clzz);
		return mapToBean;
	}

	public <T> List<T> getListBean(String sql, Class<T> clzz, String... params)
			throws SQLException, InstantiationException, IllegalAccessException {
		List<Map<String, Object>> rs = executeSql(sql, params);
		// 因为已经 将 rs 的指针定位到第一个所以 不需要 循环来做 定位指针
		return ResultSetUtil.listMapToListBean(rs, clzz);
	}

	public Map<String, Object> getMapBean(String sql, String... params) throws SQLException {
		List<Map<String, Object>> rs = executeSql(sql, params);
		if (rs.size() > 1) {
			throw new SQLException(" Expect to get 1 row, and actually get " + rs.size() + " rows ");
		}
		return rs.get(0);
	}

	/**
	 * 执行 sql 语句获取 List<Map<String, Object>> 数据
	 *
	 *
	 * @author 魏帅
	 * @date 2018年6月6日
	 */
	public List<Map<String, Object>> getListMapBean(String sql, String... params) throws SQLException {
		return executeSql(sql, params);
	}

	/**
	 * 将 resultset 转换Map
	 *
	 *
	 * @author 魏帅
	 * @date 2018年6月6日
	 */
	private static Map<String, Object> getResultMap(ResultSet rs) throws SQLException {
		Map<String, Object> hm = new HashMap<String, Object>();
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		for (int i = 1; i <= count; i++) {
			String key = rsmd.getColumnLabel(i);
			Object value = rs.getObject(i);
			hm.put(key, value);
		}
		return hm;
	}

	// 解析 rs 的类型
	@SuppressWarnings("unchecked")
	public <T> T getParamByResultSet(ResultSet rs, Class<T> type, String paramName) throws SQLException {
		T param = null;
		param = (T) rs.getObject(paramName);
		return param;
	}

	// 执行 sql语句
	private List<Map<String, Object>> executeSql(String sql, String... params) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		// ResultSet executeQuery(String sqlString)：执行查询数据库的SQL语句 ，返回一个结果集（ResultSet）对象。
		ResultSet rs = null;
		try {
			conn = dbHelp.getConn();

			stmt = conn.prepareStatement(sql);

			for (int i = 0; i < params.length; i++) {
				stmt.setObject(i + 1, params[i]);
			}
			rs = stmt.executeQuery();
			List<Map<String, Object>> mapres = null;
			rs.last();
			int row = rs.getRow();
			if (row <= 0) {
				return mapres;
			} else {
				mapres = new ArrayList<Map<String, Object>>();
			}
			rs.first();
			mapres.add(getResultMap(rs));

			while (rs.next()) {
				mapres.add(getResultMap(rs));
			}
			return mapres;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stmt, rs);
		}
		return null;
	}

	private void closeAll(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (conn != null)
			conn.close();
	}

	@SuppressWarnings("null")
	public int update(String sql, Object... params) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		// ResultSet executeQuery(String sqlString)：执行查询数据库的SQL语句 ，返回一个结果集（ResultSet）对象。

		try {
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();

			// Now you can extract all the records
			// to see the updated records

			stmt.execute();
			return stmt.getUpdateCount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stmt, null);
		}
		return 0;

	}

	/**
	 * 
	 * 返还主键 （LONG）
	 *
	 * @author 魏帅
	 * @date 2018年6月19日
	 */
	public Long addAndRTLongKey(String sql, Object... params) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbHelp.getConn();

			// 指定返回生成的主键
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// 如果使用静态的SQL，则不需要动态插入参数
			for (int i = 0; i < params.length; i++) {
				Object param = params[i];
				pstmt.setObject(i + 1, param);
			}
			pstmt.executeUpdate();
			// 检索由于执行此 Statement 对象而创建的所有自动生成的键
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return null;
	}

	/**
	 * 
	 * 添加 并 返还主键 （Object）
	 *
	 * @author 魏帅
	 * @date 2018年6月19日
	 */
	public Object addAndRTObjectKey(String sql, Object... params) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbHelp.getConn();

			// 指定返回生成的主键
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// 如果使用静态的SQL，则不需要动态插入参数
			for (int i = 0; i < params.length; i++) {
				Object param = params[i];
				pstmt.setObject(i + 1, param);
			}
			pstmt.executeUpdate();
			// 检索由于执行此 Statement 对象而创建的所有自动生成的键
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				return rs.getObject(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return null;
	}

	/**
	 * 添加 数据 并返还复合主键
	 *
	 *
	 * @author 魏帅
	 * @date 2018年6月19日
	 */
	public Object[] addAndRTarrayKey(String sql, Object... params) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbHelp.getConn();

			// 指定返回生成的主键
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// 如果使用静态的SQL，则不需要动态插入参数
			for (int i = 0; i < params.length; i++) {
				Object param = params[i];
				pstmt.setObject(i + 1, param);
			}
			pstmt.executeUpdate();
			// 检索由于执行此 Statement 对象而创建的所有自动生成的键
			rs = pstmt.getGeneratedKeys();
			List<Object> keys = new ArrayList<Object>();
			while (rs.next()) {
				keys.add(rs.getObject(1));
			}
			return keys.toArray();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return null;
	}

	/**
	 * 添加 并返还主键 （String）
	 *
	 *
	 * @author 魏帅
	 * @date 2018年6月19日
	 */
	public String addAndRTStringKey(String sql, Object... params) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbHelp.getConn();

			// 指定返回生成的主键
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// 如果使用静态的SQL，则不需要动态插入参数
			for (int i = 0; i < params.length; i++) {
				Object param = params[i];
				pstmt.setObject(i + 1, param);
			}
			pstmt.executeUpdate();
			// 检索由于执行此 Statement 对象而创建的所有自动生成的键
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return null;
	}

	/**
	 * 添加 并返还主键 （Integer）
	 *
	 *
	 * @author 魏帅
	 * @date 2018年6月19日
	 */
	public Integer addAndRTIntegerKey(String sql, Object... params) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbHelp.getConn();

			// 指定返回生成的主键
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// 如果使用静态的SQL，则不需要动态插入参数
			for (int i = 0; i < params.length; i++) {
				Object param = params[i];
				pstmt.setObject(i + 1, param);
			}
			pstmt.executeUpdate();
			// 检索由于执行此 Statement 对象而创建的所有自动生成的键
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return null;
	}

	public static void main(String[] args) {

		System.out.println("9999");
		/*
		 * <!-- <Resource auth="Container" driverClassName="com.mysql.jdbc.Driver"
		 * maxActive="100" maxIdle="20" maxWait="5000" name="jdbc/rongtongjygy"
		 * testWhileIdle="true" timeBetweenEvictionRunsMillis="28800000"
		 * type="javax.sql.DataSource" url=
		 * "jdbc:mysql://192.168.2.117:3306/rongtongbgh?zeroDateTimeBehavior=convertToNull"
		 * username="zontonec" password="RongTong2015" validationQuery="select 1" /> -->
		 */

		MyDataSource config = new MyDataSource();
		config.setJdbcUrl("jdbc:mysql://192.168.2.117:3306/rongtongbgh?zeroDateTimeBehavior=convertToNull");
		config.setUsername("zontonec");
		config.setPassword("RongTong2015");
		config.setDriverClassName("com.mysql.jdbc.Driver");
		config.setAutoCommit(true);
		config.setMinimumIdle(10);
		config.setMaximumPoolSize(200);
		BaseDao baseDao = new BaseDao(config);
		try {
			Map<String, Object> mapBean = baseDao.getMapBean("select * from  jygy_user_kid limit 1 ");
			System.out.println(mapBean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
