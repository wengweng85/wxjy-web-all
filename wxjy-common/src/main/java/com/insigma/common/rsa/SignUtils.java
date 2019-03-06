package com.insigma.common.rsa;

import com.insigma.common.util.RandomNumUtil;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * SignUtils
 */
public class SignUtils {

	private final static String KEY = "123456789F";

	private final static String ALGORITHM = "SHA-1";

	private final static String SIGN_SIGNATURE="signature";

	private final static String SIGN_TIMESTAMP="timestamp";

	private final static String SIGN_NONCE="nonce";

	//ʱ�����Ч����
	private static final long TIMESTAMP=1*60*1000;

	/**
	 * ���ݸ���ժҪ�㷨���ƴ���һ����ϢժҪʵ��
	 */
	static MessageDigest getDigest(String algorithm) {
		try {
			return MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage());
		}
	}


	/**
	 * ��ȡ SHA-512 ��ϢժҪʵ��
	 *
	 * @return SHA-512 ��ϢժҪʵ��
	 * @throws RuntimeException �� {@link NoSuchAlgorithmException} ����ʱ
	 */
	public static MessageDigest getSha512Digest() {
		return getDigest(ALGORITHM);
	}

	/**
	 * ��ȡժҪ��ʮ�������ַ���
	 * @param data
	 * @return ժҪ��ʮ�������ַ���
	 */
	public static String getHexString(byte[] data) {
		return Hex.encodeHexString(data);
	}


	public static String getKey(){
		return KEY;
	}


	/**
	 * ��ȥ�����еĿ�ֵ��ǩ������
	 * @param sArray ǩ��������
	 * @return ȥ����ֵ��ǩ�����������ǩ��������
	 */
	public static Map<String, String> paraFilter(Map<String, String> sArray) {
		Map<String, String> result = new HashMap<String, String>();
		if (sArray == null || sArray.size() <= 0) {
			return result;
		}
		for (String key : sArray.keySet()) {
			String value = sArray.get(key);
			if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
					|| key.equalsIgnoreCase("sign_type")) {
				continue;
			}
			result.put(key, value);
		}
		return result;
	}

	/**
	 * ����������Ԫ�����򣬲����ա�����=����ֵ����ģʽ�á�&���ַ�ƴ�ӳ��ַ���
	 * @param params ��Ҫ���򲢲����ַ�ƴ�ӵĲ�����
	 * @return ƴ�Ӻ��ַ���
	 */
	public static String createLinkString(Map<String, String> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			try {
				if(CharUtil.isChinese(value)){
					value = URLEncoder.encode(value, "UTF-8");
				}
			} catch (UnsupportedEncodingException e) {
				System.out.println(e.getMessage());
			}

			if (i == keys.size() - 1) {//ƴ��ʱ�����������һ��&�ַ�
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}

		return prestr;
	}

	/**
	 * ����ǩ��
	 * @Title: createSign
	 * @param paramMap
	 * @return
	 */
	public static String createSign(Map<String,String> paramMap){
		//1.������������ˣ����˵�sign_type��sign�ֶ�����
		paramMap = paraFilter(paramMap);
		//2.���ɴ�ǩ���ַ���
		String s = createLinkString(paramMap);
		//3.����key
		s = s + getKey();
		byte[] shaByte = getSha512Digest().digest(s.getBytes());
		String ed = getHexString(shaByte);
		return ed;
	}


	/**
	 * ǩ�������ɵ�ַ��Ŀ
	 * @return
	 */
	public static String signature() {
		HashMap map=new HashMap();
		String nonce= UUID.randomUUID().toString();
		String timestamp=new Long(System.currentTimeMillis()+TIMESTAMP).toString();
		map.put(SIGN_NONCE, nonce);
		map.put(SIGN_TIMESTAMP,timestamp);
		String sign=signature(timestamp,nonce);
		map.put(SIGN_SIGNATURE,sign);
		return createLinkString(map);
	}

	/**
	 * ǩ��
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	private static String signature(String timestamp, String nonce) {
		String[] arr = new String[]{KEY, timestamp, nonce};
		Arrays.sort(arr);//��token��timestamp��nonce�������������ֵ�������
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		MessageDigest md = null;
		String sign = null;
		try {
			md = MessageDigest.getInstance(ALGORITHM);
			byte[] digest = md.digest(content.toString().getBytes());// �����������ַ���ƴ�ӳ�һ���ַ�������sha1����
			sign = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sign;
	}

	/**
	 * ��ǩ
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		String[] arr = new String[]{KEY, timestamp, nonce};
		Arrays.sort(arr);//��token��timestamp��nonce�������������ֵ�������
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		MessageDigest md = null;
		String tmpStr = null;
		try {
			md = MessageDigest.getInstance(ALGORITHM);
			byte[] digest = md.digest(content.toString().getBytes());// �����������ַ���ƴ�ӳ�һ���ַ�������sha1����
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}

	/**
	 * ���ֽ�����ת��Ϊʮ�������ַ���
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * ���ֽ�ת��Ϊʮ�������ַ���
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}

	public static void main(String [] args){
		String param=signature();
		System.out.println(param);
	}



}