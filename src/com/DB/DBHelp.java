/**  

* <p>Title: DBHelp.java</p>  

* <p>Description: </p>  

* <p>Copyright: Copyright (c) 2017</p>  
 

* @author 魏帅  

* @date 2018年6月6日  
   
*/
package com.DB;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 
 * <p>
 * Title: DBHelp
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
public class DBHelp {
	/**
	 * 连接池
	 */
	private HikariDataSource dataSource;
	/**
	 * 数据库连接 适配器
	 */
//	private Connection conn;

	/**
	 * 返回 数据库连接 适配器
	 * 
	 * @return conn
	 * @throws SQLException
	 */
	protected Connection getConn() throws SQLException {
		return getDataSource().getConnection();
	}

	/**
	 * 设置 数据库连接 适配器
	 * 
	 * @param conn
	 *            the conn to set
	 */
//	protected void setConn(Connection conn) {
//		this.conn = conn;
//	}

	/**
	 * 设置 连接池
	 * 
	 * @param dataSource
	 *            the dataSource to set
	 */
	protected void setDataSource(HikariDataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 
	 */
	protected DBHelp(HikariConfig hcf) {
		// TODO Auto-generated constructor stub
		dataSource = new HikariDataSource(hcf);
	}

	/**
	 * 返回 连接池
	 * 
	 * @return dataSource
	 */
	protected HikariDataSource getDataSource() {
		return dataSource;
	}

}
