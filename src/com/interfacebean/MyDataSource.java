/**  

* <p>Title: MyDataSource.java</p>  

* <p>Description: </p>  

* <p>Copyright: Copyright (c) 2017</p>  
 

* @author κ˧  

* @date 2018��6��5��  
   
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
 * @author κ˧
 * 
 * @date 2018��6��5��
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE/*
							 * , ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR,
							 * ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.PACKAGE
							 */ })
// ElementType.TYPE �����࣬�ӿڣ�ö�ٵ�������ע��
//
// ElementType.FIELD �������ֶΣ�����ö��ֵ
//
// ElementType.METHOD �����ڷ��������������췽��
//
// ElementType.PARAMETER �����ڷ����Ĳ���
//
// ElementType.CONSTRUCTOR �����ڹ��췽��
//
// ElementType.LOCAL_VERIABLE �����ڱ��ر�������catch���
//
// ElementType.ANNOTATION_TYPE ������ע��
//
// ElementType.PACKAGE �����ڰ�
public @interface MyDataSource {

	// ���� ��MydataSourceDefault�� MD5 ��ҫ���� ��Ϊ����ֵ ��ʶ���ݿ����ӳ�
	public static String jdbcDefault = "9054d57145f7fcedff01add000d66ad9";

	String JDBC_name() default jdbcDefault;

	/**
	 * ���ݿ������ļ���Ŀ¼
	 */
	String driverClassName();

	/**
	 * ���ݿ� ����URL
	 */
	String jdbcUrl();

	/**
	 * ���ݿ��˺�����
	 */
	String username();

	/**
	 * ���ݿ� ��������
	 */
	String password();

	/**
	 * �Ƿ��Զ��ύ
	 */
	boolean autoCommit() default true;

	/**
	 * ���ݿ���С������
	 */
	int minimumIdle() default 10 ;

	/**
	 * ���������������
	 */
	int maximumPoolSize() default 200;

	
}
