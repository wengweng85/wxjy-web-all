package com.insigma.common.fm.function;

import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * �Զ���Freemarker����<br>
 * <li>ȡ�����ֵ�������</li>
 * 
 * @author zbq
 * @date 2010-7-21
 */
public class ShortWordsFunction implements TemplateMethodModel {

	public static final String DEFAULT_FILL_WORDS = "...";

	/**
	 * �����ֳ������ֽ�ȡ�滻Ϊ�ض��ַ�<br>
	 * ����˳��: ԭ���֡����������������ȡ���滻�ַ�<br>
	 * ����1������2������ڡ���������ڷ��ؿ��ַ���������3������Ĭ��Ϊ"..."
	 */
	
	@Override
    @SuppressWarnings("rawtypes")
	public Object exec(List paramList) throws TemplateModelException {
		return getWords(paramList);
	}

	/**
	 * ȡ������������<br>
	 * <li>��������Ϊ2��3���⡢���ؿ��ַ���</li>
	 * 
	 * @param paramList
	 * @return String
	 * @throws TemplateModelException
	 */
	@SuppressWarnings("rawtypes")
	private String getWords(List paramList) throws TemplateModelException {
		int paramCnt = paramList.size();
		if (paramCnt != 2 && paramCnt != 3) {
			throw new TemplateModelException("parameter count must be 2 or 3");
		}
		return getShortWords(paramList);
	}

	/**
	 * ��������ֽڳ��ȶ����ֽ��н�ȡ����������ַ�
	 */
	@SuppressWarnings("rawtypes")
	private String getShortWords(List paramList) {
		String src = (String) paramList.get(0); // ԭ����
		int maxLength = Integer.parseInt(((String) paramList.get(1)));
		int maxByteCnt = maxLength;

		String fillWords = DEFAULT_FILL_WORDS;
		if (paramList.size() == 3) {
			fillWords = (String) paramList.get(2);
		}

		String result = getShortString(src, maxByteCnt, fillWords);
		return result;
	}

	/**
	 * ���ַ�ת��Ϊ�ֽڡ�ȡ����󳤶��ֽں���������ַ�
	 */
	private String getShortString(String str, int maxSize, String fillWords) {
		int length = str.length();
		if (length > maxSize) {
			str = str.substring(0, maxSize);
			str = mergeWords(str, fillWords);
		}
		return str;
	}

	/**
	 * ���ַ�ת��Ϊ�ֽڡ�ȡ����󳤶��ֽں���������ַ�
	 * 
	 * private String getShortString1(String str, int maxByteCnt, String
	 * fillWords) { byte[] byteArray = str.getBytes();
	 * 
	 * int size = byteArray.length; if (size > maxByteCnt) { str =
	 * byte2String(byteArray, maxByteCnt); str = mergeWords(str, fillWords); }
	 * return str; }
	 */
	/**
	 * ���ֽڽ�ȡ��ת��Ϊ�ַ���
	 * 
	 * private String byte2String(byte[] array, int length) { byte[] returnArray
	 * = new byte[length];
	 * 
	 * for (int i = 0; i < array.length; i++) { if (i > length - 1) { break; }
	 * returnArray[i] = array[i]; } return new String(returnArray); }
	 */
	
	
	/**
	 * �ϲ��������ֺ������ַ�<br>
	 * <li>���� + ... => ����...</li>
	 */
	private String mergeWords(String words, String mergeInfo) {
		StringBuffer newWords = new StringBuffer();
		newWords.append(words).append(mergeInfo);
		return newWords.toString();
	}
}
