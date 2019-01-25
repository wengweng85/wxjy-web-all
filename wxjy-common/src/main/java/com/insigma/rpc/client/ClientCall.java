package com.insigma.rpc.client;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.caucho.hessian.client.HessianProxyFactory;
import com.insigma.resolver.AppException;
import com.insigma.rpc.hessianservice.HessianService;
import com.insigma.rpc.infostruct.Data;
import com.insigma.rpc.infostruct.Message;
import com.insigma.rpc.infostruct.enums.BusCode;
import com.insigma.rpc.infostructofsebao.Head;
import com.insigma.rpc.infostructofsebao.Request;
import com.insigma.rpc.infostructofsebao.Response;

/**
 * ���÷���
 * 
 * @author admin
 *
 */
public class ClientCall {
	
	//����hessian��Զ�̷�����õ�ַ
	private String HESSIAN_SERVICE_URL="http://127.0.0.1:8091/myweb/hessianService";  
	

	/**
	 * ��ѯ������Ϣ212003
	 * 
	 * Ŀ��ϵͳͨ���˽��׿��Բ�ѯָ��Ŀ��ϵͳ����ID�����������֤�������Ӧ�ĸ�����Ϣ��
	 * ���������ҵ�����е�Ŀ��ϵͳ����ID��Ϊ�գ�����Ŀ��ϵͳ����ID��ȷ������ӳ��ĸ��˻�����Ϣ��û���ҵ�����ʾ������Ϣ��
	 * ���������ҵ�����е�Ŀ��ϵͳ����IDΪ�գ��������������֤�������Ҹ��˻�����Ϣ�����������֤�ű�����һ����ֵ��
	 * �˽��׿��ܷ��ض������������ĸ�����Ϣ
	 * ����Ŀ��ϵͳ�����ж�ѡ������������Ϣ�������Ӧ������ĳ��������Ϣ�е�Ŀ��ϵͳ����IDΪ�գ����ʾĿ��ϵͳ��δ��ø�����Ϣ���и�����Ϣӳ���
	 * ��Ŀ��ϵͳ���Ե��ý���5.2.2�޸ĸ�����Ϣ212002��ʵ�ָ�����Ϣӳ��󶨡� ������ҵ�����¼��Ϊ1������Ӧ����ҵ�����¼��Ϊ������
	 * 
	 * @param BAC010  Ŀ��ϵͳ����ID
	 * @param AAC002 ֤������
	 * @param AAC003 ����
	 * @return
	 * @throws AppException
	 */
	public void jk_Test(JkDTO jkac01dto) throws AppException {
		Message message = new Message();
		List<Data> datalist = new ArrayList<Data>();
		Data data = new Data();
		// ���Ŀ��ϵͳ����ID��Ϊ��
		if (null != jkac01dto.getBac010() && !"".equals(jkac01dto.getBac010())) {
			 data.setBAC010(jkac01dto.getBac010());//Ŀ��ϵͳ����ID
		} else {
			throw new AppException("���˱�Ų���Ϊ��");
		}
		// aac002 �Լ�aac003���ٵ���һ��
		if (null != jkac01dto.getAac002() && !"".equals(jkac01dto.getAac002())) {
			data.setAAC002(jkac01dto.getAac002());// ֤������
		} else {
			throw new AppException("���֤���벻��Ϊ��");
		}
		// ����
		if (null != jkac01dto.getAac003() && !"".equals(jkac01dto.getAac003())) {
			data.setAAC003(jkac01dto.getAac003());// ����
		} else {
			throw new AppException("��������Ϊ��");
		}
		/*
		if (null != jkac01dto.getAac006() && !jkac01dto.getAac006().equals("")) {
			// data.setAAC006(jkac01dto.getAAC006());//��������
		} else {
			throw new AppException("�������ڲ���Ϊ��");
		}
        */
		if (null != jkac01dto.getAac004() && !"".equals(jkac01dto.getAac004())) {
			data.setAAC004(jkac01dto.getAac004());//�Ա�
		} else {
			throw new AppException("�Ա���Ϊ��");
		}
		/*
		if (null != jkac01dto.getAac005() && !jkac01dto.getAac005().equals("")) {
			// data.setAAC005(jkac01dto.getAAC005());//���岻��Ϊ��
		} else {
			throw new AppException("���岻��Ϊ��");
		}*/
		datalist.add(data);
		message.setDATALIST(datalist);
		message.setDATASIZE(1);
		Message returnmsg = call(message,BusCode.JK_212003);
		String status = returnmsg.getSTATUS();
		System.out.println(status);
			
	}

	/**
	 *���˲α���Ϣ��ѯ��22KOW6��
	 *
	 * @return
	 * @throws AppException
	 */
	public Response jk_Test2(JkDTO dto) throws AppException{
		Request request=new Request();
		Head head=new Head();
		head.setAac002(dto.getAac002());
		request.setHead(head);
		Response returnmsg = call(request,BusCode.JK_22KOW6);
		return returnmsg;
	 }
	
	
	/**
	 * ����hessian�Ľӿڵ���
	 * @param requestmsg
	 * @param buscode
	 * @return
	 * @throws AppException
	 */
	private Message call(Message request,BusCode buscode) throws AppException {
        HessianProxyFactory factory = new HessianProxyFactory();  
        try {  
        	HessianService hessianservice = (HessianService) factory.create(HessianService.class, HESSIAN_SERVICE_URL);  
        	if((buscode.getCode()).equals(BusCode.JK_212003.getCode())){
        		 return hessianservice.jk_test(request);
        	}else{
        		throw new  AppException(buscode.getCode()+"�ӿ����Ͳ�����");
        	}
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
            throw new  AppException("Զ�̵���ʧ��,�����ַ�Ƿ���ȷ"+HESSIAN_SERVICE_URL);
        }  
	}
	
	/**
	 * ����hessian�Ľӿڵ���
	 * @param requestmsg
	 * @param buscode
	 * @return
	 * @throws AppException
	 */
	private Response call(Request requestmsg,BusCode buscode) throws AppException {
        HessianProxyFactory factory = new HessianProxyFactory();  
        try {  
        	HessianService hessianservice = (HessianService) factory.create(HessianService.class, HESSIAN_SERVICE_URL);  
        	if((buscode.getCode()).equals(BusCode.JK_22KOW6.getCode())){
        		 return hessianservice.jk_test2(requestmsg);
       	   }else{
        		throw new  AppException(buscode.getCode()+"�ӿ����Ͳ�����");
           }
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
            throw new  AppException("Զ�̵���ʧ��,�����ַ�Ƿ���ȷ"+HESSIAN_SERVICE_URL);
        }  
	}
	
}
