package com.insigma.common.util;

import org.springframework.util.StringUtils;

/**
 * �ַ�������
 * @author kezp
 *
 */
public class StringUtil {

	/**
	 * �Ƿ������ַ�
	 * @param ch
	 * @return
	 */
	public static boolean isValidChar(char ch) {
		int hightByte;
		int lowByte;
		byte[] bytes = (String.valueOf(ch)).getBytes();
		if (bytes.length > 2) { // ����
			return false;
		}
		if (bytes.length == 1) { // Ӣ���ַ�
			hightByte = bytes[0];
			if ((hightByte >= 32) && (hightByte <= 126)) {
				return true;
			} else {
				return false;
			}
		}
		if (bytes.length == 2) { // �����ַ�
			hightByte = bytes[0] & 0xff;
			lowByte = bytes[1] & 0xff;
			if ((hightByte >= 129 && hightByte <= 254)
					&& (lowByte >= 64 && lowByte <= 254)) { //81 40 - FE FE ת��asciiΪ�� 129 64 - 254 254
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * �õ��ַ���ascii����������
	 * @param ch
	 * @return
	 */
	public static int getAscii(char ch) {
		int ascii = 0;
		int hightByte;
		int lowByte;
		byte[] bytes = (String.valueOf(ch)).getBytes();

		if (bytes.length == 1) { // Ӣ���ַ�
			ascii = bytes[0];
		}
		if (bytes.length == 2) { // �����ַ�
			hightByte = bytes[0] & 0xff;
			lowByte = bytes[1] & 0xff;
			ascii = hightByte * 256 + lowByte;
		}
		return ascii;
	}

	/**
	 * ����xml�������ַ�
	 * @param xmlString
	 * @return
	 */
	public static String encodeXML(String xmlString) {
		//&lt; <  С�ں�
		//&gt; > ���ں�
		//&amp; &  ��
		//&apos; ' ������
		//&quot; " ˫����
		xmlString = xmlString.replaceAll("&lt;", "<");
		xmlString = xmlString.replaceAll("&gt;", ">");
		xmlString = xmlString.replaceAll("&amp;", "&");
		xmlString = xmlString.replaceAll("&apos;", "'");
		xmlString = xmlString.replaceAll("&quot;", "\"");

		char ch;
		String str;
		StringBuffer buf = new StringBuffer();
		//���������ַ�
		for (int i = 0; i < xmlString.length(); i++) {
			ch = xmlString.charAt(i);
			if (!isValidChar(ch)) { //����Ƿ������ַ���
				continue;
			}
			//str = String.valueOf(ch);
			str = Character.toString(ch);
			if ('<' == ch) {
				str = "&lt;";
			}
			if ('>' == ch) {
				str = "&gt;";
			}
			if ('&' == ch) {
				str = "&amp;";
			}
			if ('\'' == ch) {
				str = "&apos;";
			}
			if ('"' == ch) {
				str = "&quot;";
			}
			buf.append(str);
		}
		return buf.toString();
	}
	
	/**
	 * �����ļ�·��
	 * @param pathString
	 * @return
	 */
	public static String resolvePath(String pathString) {
		String strpath = StringUtils.replace(pathString,"\\", "/");
		if (!strpath.endsWith("/")){
			strpath = strpath +"/";
		}
		return strpath;
	}
	
	/**
	 * �ַ�����д
	 * @param str
	 * @return
	 */
	public static String reverse(String str){
		StringBuffer sb = new StringBuffer(str);
		return sb.reverse().toString();
	}
	
	
	/**
	 * ����
	 * @param args
	 * @throws Exception
	 */
	//public static void main(String[] args) throws Exception {
		
		/*************��Ascii��***********************/
		//char c1 ='��';
		//char c2 ='a';
		//System.out.println(getAscii(c1));
		//System.out.println(getAscii(c2));
		//char c3 ='	';  //tab ����
		//System.out.println(getAscii(c3));
		//char c4 ='\"';  //˫����
		//System.out.println(getAscii(c4));
		/*********************************************/
		
		/*************�������ֶ�*************************/
		//System.out.println(isValidChar(c3));
		//System.out.println(isValidChar(c4));
		/**********************************************/
		
		/*************�滻XML�е������ַ�******************/
		//String strxml = encodeXML("fasdf<,xm;>,\\");
		//System.out.println(strxml);
		/**********************************************/
		
		/**********��c:\kezp\test\�滻Ϊ******c:/kezp/test/*****************/
		///System.out.println(resolvePath("c:\\kezp\\ada"));
		//System.out.println(reverse("c:\\kezp\\ada"));
		/**********************************************/
	//}
	
	public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

	/**
	 * �ַ�������
	 */
	public static String stripHtml(String content) {
		if(content != null) {
			// <p>�����滻Ϊ����
			content = content.replaceAll("<p.*?>", "");
			content = content.replaceAll("<P.*?>", "");
			content = content.replaceAll("<div.*?>", "");
			content = content.replaceAll("<DIV.*?>", "");
			content = content.replaceAll("<br\\s*/?>", "");
			//&nbsp;�滻Ϊ�ո�
			content = content.replaceAll("&nbsp;", " ");
			content = content.replaceAll("\\<.*?>", "");
			content = content.replaceAll("@", "");
			content = content.replaceAll("\r\n", "");
			content = content.replaceAll("\n", "");
			content = content.trim();
		}
		return content;
	}
}
