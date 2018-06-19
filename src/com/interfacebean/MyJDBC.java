package com.interfacebean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
public @interface MyJDBC {
	String nameDefault = MyDataSource.jdbcDefault;

	String Name() default nameDefault;
}
