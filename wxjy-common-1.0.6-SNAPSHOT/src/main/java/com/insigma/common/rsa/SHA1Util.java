package com.insigma.common.rsa;

import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by admin
 */
public class SHA1Util {

    public static void main(String [] args){

        Map<String,Object> maps = new HashMap<String,Object>();
        maps.put("jsapi_ticket", "sM4AOVdWfPE4DxkXGEs8VMCPGGVi4C3VM0P37wVUCFvkVAy_90u5h9nbSlYy3-Sl-HhTdfl2fzFy1AOcHKP7qg");
        maps.put("noncestr", "Wm3WZYTPz0wzccnW");
        maps.put("timestamp", "1414587457");
        maps.put("url", "http://mp.weixin.qq.com?params=value");

        try {
            String str = SHA1(maps);
            System.out.println(str);
        } catch (DigestException e) {
            e.printStackTrace();
        }

    }

    /**
     * SHA1 ��ȫ�����㷨
     * @param maps ����key-value map����
     * @return
     * @throws DigestException
     */
    public static String SHA1(Map<String,Object> maps) throws DigestException {
        //��ȡ��ϢժҪ - �����ֵ�������ַ���
        String decrypt = getOrderByLexicographic(maps);
        try {
            //ָ��sha1�㷨
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decrypt.getBytes());
            //��ȡ�ֽ�����
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // �ֽ�����ת��Ϊ ʮ������ ��
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString().toUpperCase();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new DigestException("ǩ������");
        }
    }
    /**
     * ��ȡ�������ֵ�����
     * @param maps ����key-value map����
     * @return String �������ַ���
     */
    private static String getOrderByLexicographic(Map<String,Object> maps){
        System.out.println(splitParams(lexicographicOrder(getParamsName(maps)),maps));
        //jsapi_ticket=sM4AOVdWfPE4DxkXGEs8VMCPGGVi4C3VM0P37wVUCFvkVAy_90u5h9nbSlYy3-Sl-HhTdfl2fzFy1AOcHKP7qg&noncestr=Wm3WZYTPz0wzccnW&timestamp=1414587457&url=http://mp.weixin.qq.com?params=value
        //jsapi_ticket=sM4AOVdWfPE4DxkXGEs8VMCPGGVi4C3VM0P37wVUCFvkVAy_90u5h9nbSlYy3-Sl-HhTdfl2fzFy1AOcHKP7qg&noncestr=Wm3WZYTPz0wzccnW&timestamp=1414587457&url=http://mp.weixin.qq.com?params=value
        return splitParams(lexicographicOrder(getParamsName(maps)),maps);
    }
    /**
     * ��ȡ�������� key
     * @param maps ����key-value map����
     * @return
     */
    private static List<String> getParamsName(Map<String,Object> maps){
        List<String> paramNames = new ArrayList<String>();
        for(Map.Entry<String,Object> entry : maps.entrySet()){
            paramNames.add(entry.getKey());
        }
        return paramNames;
    }
    /**
     * �������ư��ֵ�����
     * @param paramNames ��������List����
     * @return �����Ĳ�������List����
     */
    private static List<String> lexicographicOrder(List<String> paramNames){
        Collections.sort(paramNames);
        return paramNames;
    }
    /**
     * ƴ������õĲ������ƺͲ���ֵ
     * @param paramNames �����Ĳ������Ƽ���
     * @param maps ����key-value map����
     * @return String ƴ�Ӻ���ַ���
     */
    private static String splitParams(List<String> paramNames,Map<String,Object> maps){
        StringBuilder paramStr = new StringBuilder();
        for(String paramName : paramNames){
            paramStr.append(paramName);
            for(Map.Entry<String,Object> entry : maps.entrySet()){
                if(paramName.equals(entry.getKey())){
                    paramStr.append("="+String.valueOf(entry.getValue())+"&");
                }
            }
        }
        paramStr.deleteCharAt(paramStr.length()-1);
        return paramStr.toString();
    }
}
