package com.insigma.common.excel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * excel�ļ�������ع�����֮���ݸ�ʽУ����
 * @author admin
 * 
 *
 */
public class ExcelDataFormatValidUtil {
	
	/**
	 * �Ƿ��ǿ�ֵ
	 * @param data
	 * @return true У��ʧ�� false У��ɹ�
	 */
	public boolean isNull(String data){
		if(null==data||data.equals("")){
			 return true;
		}else{
			return false;
		}
	}
	/**
	 *  �Ƿ������ڸ�ʽ
	 * @param data
	 * @param format ҪУ������ڸ�ʽ
	 * @return
	 */
	public boolean isDate(String data,String format){
		if(null==data||data.equals("")){
			 return false;
		}else{
			data=data.replace(".", "/").replace("-", "/");
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			try {
				 sdf.parse(data);
			} catch (ParseException e) {
				e.printStackTrace();
				return true;
			}
			return false;
		}
	}
	//����У��
	//���ָ�ʽУ��
	
}
