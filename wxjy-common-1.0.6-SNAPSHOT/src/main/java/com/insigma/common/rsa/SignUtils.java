package com.insigma.common.rsa;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;


public class SignUtils {
	
    
    private final static String KEY = "544BFCD7B77AA5B0CE22F86F7AB0E722";
    
    private final static String ALGORITHM = "SHA-512";
    
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
	 * @throws RuntimeException
	 *             �� {@link NoSuchAlgorithmException} ����ʱ
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
	
   public static String getBytesBASE64(byte[] data) {
        return Base64.getBASE64(data);
    }
   
   	public static byte[] getBytesBASE64(String data){
   		return Base64.getBytesBASE64(data);
   	}
   	
   	public static String getKey(){
   		return SignUtils.KEY;
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
    * @date 2016��12��24�� ����5:22:45  
    * @author xiongbin
    */
   public static String createSign(Map<String,String> paramMap){
	   //1.������������ˣ����˵�sign_type��sign�ֶ�����
   		paramMap = SignUtils.paraFilter(paramMap);
   		//2.���ɴ�ǩ���ַ���
	    String s =  SignUtils.createLinkString(paramMap);
	    
	    //3.����key
	    s = s + SignUtils.getKey();
	    
	    byte[] shaByte = SignUtils.getSha512Digest().digest(s.getBytes());
	    
	    String ed = SignUtils.getHexString(shaByte);
	    
	    
	    return ed;
   }
   
	

	
}