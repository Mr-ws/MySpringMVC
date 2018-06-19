/**  

* <p>Title: Resource.java</p>  

* <p>Description: </p>  

* <p>Copyright: Copyright (c) 2017</p>  
 

* @author 魏帅  

* @date 2018年6月2日  
   
*/
package com.interfacebean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.finalClass.InterfaceFinal;

/**
 * 资源标签 使用此标签 的属性将被注入实体
 * <p>
 * Title: Resource
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * @author 魏帅
 * 
 * @date 2018年6月2日
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD/*
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
public @interface MyResource {
	String value() default InterfaceFinal.defStr;
}
