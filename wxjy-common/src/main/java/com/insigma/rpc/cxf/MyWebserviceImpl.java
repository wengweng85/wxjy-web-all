package com.insigma.rpc.cxf;


/**
 * �ӿ�ʵ��
 * @author admin
 *
 */
public class MyWebserviceImpl implements MyWebService { 
	@Override public int add(int a, int b) { 
		System.out.println(a+"+"+b+"="+(a+b)); return a+b; 
	} 
	@Override 
	public int minus(int a, int b) { 
		System.out.println(a+"-"+b+"="+(a-b)); return a-b; 
	} 
	
	//�����������ݱ�׼
}
