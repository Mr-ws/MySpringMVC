package com.interfacebean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.finalClass.InterfaceFinal;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD/*
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
public @interface MyMapping {
	String value() default InterfaceFinal.defStr;
}
