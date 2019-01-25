package com.insigma.rpc.xml;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;
/**
 * 
 * @author qiand
 * ͨ��ע��ת��Data �� DataTime
 * 
 */
public class JaxbDateTimeAdapter extends XmlAdapter<String, Date> {
	private static final String FORMAT = "yyyyMMddHHmmss";
	
	
	@Override
	public String marshal(Date date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
		return sdf.format(date);
	}

	@Override
	public Date unmarshal(String dateStr) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
		return sdf.parse(dateStr);
	}

}
