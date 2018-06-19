/**  

* <p>Title: MyDataSource.java</p>  

* <p>Description: </p>  

* <p>Copyright: Copyright (c) 2017</p>  
 

* @author 魏帅  

* @date 2018年6月5日  
   
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
 * @author 魏帅
 * 
 * @date 2018年6月5日
 * 
 */
public class MyDataSource extends com.zaxxer.hikari.HikariConfig {
	/**
	 * 数据库驱动文件类目录
	 */
	private String driverClassName;
	/**
	 * 数据库 连接URL
	 */
	private String jdbcUrl;
	/**
	 * 数据库账号密码
	 */
	private String username;
	/**
	 * 数据库 连接密码
	 */
	private String password;
	/**
	 * 是否自动提交
	 */
	private boolean autoCommit;
	/**
	 * 数据库最小连接数
	 */
	private int minimumIdle;

	/**
	 * 池中最大链接数量
	 */
	private int maximumPoolSize;

	//
	/**
	 * 
	 * 输入 jdbc 连接 对象
	 *
	 * @author 魏帅
	 * @return
	 * @date 2018年6月5日
	 */
	public void setDriverClassName(String className) {
		super.setDriverClassName(className);
		this.driverClassName = className;
	}

	/**
	 * 返回 数据库 连接URL
	 * 
	 * @return jdbcUrl
	 */
	public String getJdbcUrl() {
		return jdbcUrl;
	}

	/**
	 * 设置 数据库 连接URL
	 * 
	 * @param jdbcUrl
	 *            the jdbcUrl to set
	 */
	public void setJdbcUrl(String jdbcUrl) {
		super.setJdbcUrl(jdbcUrl);
		this.jdbcUrl = jdbcUrl;
	}

	/**
	 * 返回 数据库账号
	 * 
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置 数据库账号密码
	 * 
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		super.setUsername(username);
		this.username = username;
	}

	/**
	 * 返回 数据库 连接密码
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置 数据库 连接密码
	 * 
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		super.setPassword(password);
		this.password = password;
	}

	/**
	 * 返回 是否自动提交
	 * 
	 * @return autoCommit
	 */
	public boolean isAutoCommit() {
		return autoCommit;
	}

	/**
	 * 设置 是否自动提交
	 * 
	 * @param autoCommit
	 *            the autoCommit to set
	 */
	public void setAutoCommit(boolean autoCommit) {
		this.autoCommit = autoCommit;
		super.setAutoCommit(autoCommit);
	}

	/**
	 * 返回 数据库最小连接数
	 * 
	 * @return minimumIdle
	 */
	public int getMinimumIdle() {
		return minimumIdle;
	}

	/**
	 * 设置 数据库最小连接数
	 * 
	 * @param minimumIdle
	 *            the minimumIdle to set
	 */
	public void setMinimumIdle(int minimumIdle) {
		this.minimumIdle = minimumIdle;
		super.setMinimumIdle(minimumIdle);
	}

	/**
	 * 返回 池中最大链接数量
	 * 
	 * @return maximumPoolSize
	 */
	public int getMaximumPoolSize() {
		return maximumPoolSize;
	}

	/**
	 * 设置 池中最大链接数量
	 * 
	 * @param maximumPoolSize
	 *            the maximumPoolSize to set
	 */
	public void setMaximumPoolSize(int maximumPoolSize) {
		this.maximumPoolSize = maximumPoolSize;
		super.setMaximumPoolSize(maximumPoolSize);
	}

	/**
	 * 返回 数据库驱动文件类目录
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
