/**  

* <p>Title: MyDataSource.java</p>  

* <p>Description: </p>  

* <p>Copyright: Copyright (c) 2017</p>  
 

* @author κ˧  

* @date 2018��6��5��  
   
*/
package com.bean;

/**
 * 
 * <p>
 * Title: MyDataSource
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * @author κ˧
 * 
 * @date 2018��6��5��
 * 
 */
public class MyDataSource extends com.zaxxer.hikari.HikariConfig {
	/**
	 * ���ݿ������ļ���Ŀ¼
	 */
	private String driverClassName;
	/**
	 * ���ݿ� ����URL
	 */
	private String jdbcUrl;
	/**
	 * ���ݿ��˺�����
	 */
	private String username;
	/**
	 * ���ݿ� ��������
	 */
	private String password;
	/**
	 * �Ƿ��Զ��ύ
	 */
	private boolean autoCommit;
	/**
	 * ���ݿ���С������
	 */
	private int minimumIdle;

	/**
	 * ���������������
	 */
	private int maximumPoolSize;

	//
	/**
	 * 
	 * ���� jdbc ���� ����
	 *
	 * @author κ˧
	 * @return
	 * @date 2018��6��5��
	 */
	public void setDriverClassName(String className) {
		super.setDriverClassName(className);
		this.driverClassName = className;
	}

	/**
	 * ���� ���ݿ� ����URL
	 * 
	 * @return jdbcUrl
	 */
	public String getJdbcUrl() {
		return jdbcUrl;
	}

	/**
	 * ���� ���ݿ� ����URL
	 * 
	 * @param jdbcUrl
	 *            the jdbcUrl to set
	 */
	public void setJdbcUrl(String jdbcUrl) {
		super.setJdbcUrl(jdbcUrl);
		this.jdbcUrl = jdbcUrl;
	}

	/**
	 * ���� ���ݿ��˺�
	 * 
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * ���� ���ݿ��˺�����
	 * 
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		super.setUsername(username);
		this.username = username;
	}

	/**
	 * ���� ���ݿ� ��������
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * ���� ���ݿ� ��������
	 * 
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		super.setPassword(password);
		this.password = password;
	}

	/**
	 * ���� �Ƿ��Զ��ύ
	 * 
	 * @return autoCommit
	 */
	public boolean isAutoCommit() {
		return autoCommit;
	}

	/**
	 * ���� �Ƿ��Զ��ύ
	 * 
	 * @param autoCommit
	 *            the autoCommit to set
	 */
	public void setAutoCommit(boolean autoCommit) {
		this.autoCommit = autoCommit;
		super.setAutoCommit(autoCommit);
	}

	/**
	 * ���� ���ݿ���С������
	 * 
	 * @return minimumIdle
	 */
	public int getMinimumIdle() {
		return minimumIdle;
	}

	/**
	 * ���� ���ݿ���С������
	 * 
	 * @param minimumIdle
	 *            the minimumIdle to set
	 */
	public void setMinimumIdle(int minimumIdle) {
		this.minimumIdle = minimumIdle;
		super.setMinimumIdle(minimumIdle);
	}

	/**
	 * ���� ���������������
	 * 
	 * @return maximumPoolSize
	 */
	public int getMaximumPoolSize() {
		return maximumPoolSize;
	}

	/**
	 * ���� ���������������
	 * 
	 * @param maximumPoolSize
	 *            the maximumPoolSize to set
	 */
	public void setMaximumPoolSize(int maximumPoolSize) {
		this.maximumPoolSize = maximumPoolSize;
		super.setMaximumPoolSize(maximumPoolSize);
	}

	/**
	 * ���� ���ݿ������ļ���Ŀ¼
	 * 
	 * @return driverClassName
	 */
	public String getDriverClassName() {
		return driverClassName;
	}

	/**
	 * 
	 */
	public MyDataSource() {
		// TODO Auto-generated constructor stub
		super();
		super.addDataSourceProperty("cachePrepStmts", true);
		super.addDataSourceProperty("prepStmtCacheSize", 500);
		super.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
		super.setConnectionTestQuery("SELECT 1");
	}

}
