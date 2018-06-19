/**  

* <p>Title: Resource.java</p>  

* <p>Description: </p>  

* <p>Copyright: Copyright (c) 2017</p>  
 

* @author κ˧  

* @date 2018��6��2��  
   
*/
package com.interfacebean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.finalClass.InterfaceFinal;

/**
 * ��Դ��ǩ ʹ�ô˱�ǩ �����Խ���ע��ʵ��
 * <p>
 * Title: Resource
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * @author κ˧
 * 
 * @date 2018��6��2��
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD/*
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
public @interface MyResource {
	String value() default InterfaceFinal.defStr;
}
