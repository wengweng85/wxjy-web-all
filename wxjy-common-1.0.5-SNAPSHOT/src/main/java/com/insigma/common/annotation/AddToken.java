package com.insigma.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ����tokenע��,��עͨ�������½�ҳ��,�ڴ�ҳ�����ע����ڱ���ҳ�����
 *
 * @author admin
 * @date 2015-8-17
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AddToken {

}
