/**  

* <p>Title: MyDataSource.java</p>  

* <p>Description: </p>  

* <p>Copyright: Copyright (c) 2017</p>  
 

* @author 魏帅  

* @date 2018年6月5日  
   
*/
package com.interfacebean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE/*
							 * , ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR,
							 * ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.PACKAGE
							 */ })
// ElementType.TYPE 用于类，接口，枚举但不能是注解
//
// ElementType.FIELD 作用于字段，包含枚举值
//
// ElementType.METHOD 作用于方法，不包含构造方法
//
// ElementType.PARAMETER 作用于方法的参数
//
// ElementType.CONSTRUCTOR 作用于构造方法
//
// ElementType.LOCAL_VERIABLE 作用于本地变量或者catch语句
//
// ElementType.ANNOTATION_TYPE 作用于注解
//
// ElementType.PACKAGE 作用于包
public @interface MyDataSource {

	// 基于 “MydataSourceDefault” MD5 照耀出来 作为加密值 标识数据库连接池
	public static String jdbcDefault = "9054d57145f7fcedff01add000d66ad9";

	String JDBC_name() default jdbcDefault;

	/**
	 * 数据库驱动文件类目录
	 */
	String driverClassName();

	/**
	 * 数据库 连接URL
	 */
	String jdbcUrl();

	/**
	 * 数据库账号密码
	 */
	String username();

	/**
	 * 数据库 连接密码
	 */
	String password();

	/**
	 * 是否自动提交
	 */
	boolean autoCommit() default true;

	/**
	 * 数据库最小连接数
	 */
	int minimumIdle() default 10 ;

	/**
	 * 池中最大链接数量
	 */
	int maximumPoolSize() default 200;

	
}
