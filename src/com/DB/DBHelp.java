/**  

* <p>Title: DBHelp.java</p>  

* <p>Description: </p>  

* <p>Copyright: Copyright (c) 2017</p>  
 

* @author κ˧  

* @date 2018��6��6��  
   
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
 * @author κ˧
 * 
 * @date 2018��6��6��
 * 
 */
public class DBHelp {
	/**
	 * ���ӳ�
	 */
	private HikariDataSource dataSource;
	/**
	 * ���ݿ����� ������
	 */
//	private Connection conn;

	/**
	 * ���� ���ݿ����� ������
	 * 
	 * @return conn
	 * @throws SQLException
	 */
	protected Connection getConn() throws SQLException {
		return getDataSource().getConnection();
	}

	/**
	 * ���� ���ݿ����� ������
	 * 
	 * @param conn
	 *            the conn to set
	 */
//	protected void setConn(Connection conn) {
//		this.conn = conn;
//	}

	/**
	 * ���� ���ӳ�
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
	 * ���� ���ӳ�
	 * 
	 * @return dataSource
	 */
	protected HikariDataSource getDataSource() {
		return dataSource;
	}

}
