package com.insigma.common.fm.function;

import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class DisplayFiledFunction implements TemplateMethodModel {

	@SuppressWarnings("rawtypes")
	@Override
	public Object exec(List arguments) throws TemplateModelException {
		return isDisplay(arguments);
	}
	
	/**
	 * �ж��ֶ��Ƿ���ʾ(����1��2�������)<br>
	 * <li>����1: ҳ������ʾ�������ֶ�</li><br>
	 * <li>����2: Ҫ��ʾ��ĳ�ֶε�����</li>
	 */
	@SuppressWarnings("rawtypes")
	private Integer isDisplay(List arguments) {
		String displayFileds = (String) arguments.get(0);
		String filed = (String) arguments.get(1);
		if(displayFileds.contains(filed)){
			return 1;
		}
		return 0;
	}

}
