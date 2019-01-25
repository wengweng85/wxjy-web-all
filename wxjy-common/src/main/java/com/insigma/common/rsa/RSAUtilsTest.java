package com.insigma.common.rsa;

import net.sf.json.JSONObject;

/**
 * admin
 */
public class RSAUtilsTest {

    public static  void main2(String [] a){
        try{
            //1.
            //����֤��
            RSAUtils.initKey();
            //2.
            //Ҫ���ܵ��ַ���
            String inputStr = "{\"aac002\":\"420624198411037915\"}";
            //���ļ��л�ȡ��Կ
            String publickey=RSAUtils.readKey("d:/publicKey.keystore");
            //��������base64
            String encodedata=RSAUtils.encryptByPublicKey(inputStr,publickey);

            //3.����
            //���ļ��л�ȡ��Կ
            String privatekey=RSAUtils.readKey("d:/privateKey.keystore");
            String outputStr=RSAUtils.decryptByPrivateKey(encodedata,privatekey);
            System.out.println("outputstr="+outputStr);

            String server_inputStr = "{\"aac003\":\"420624198411037915\"}";
            //����˼���base64
            String serverencodedata=RSAUtils.encryptByPrivateKey(server_inputStr,privatekey);
            String server_outputStr=RSAUtils.decryptByPublicKey(serverencodedata,publickey);
            System.out.println("server_outputStr="+server_outputStr);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * main
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        //��������
        String inputStr="{\"code_type\":\"JJ_AREA\",\"code_value\":\"c099\"}";
        // aac002=420624198411037915
        //��������
        RSAUtils.EncryptDataStruct encryptDataStruct = RSAUtils.encryptByAesAndRsaPublickey(inputStr);
        String data= JSONObject.fromObject(encryptDataStruct).toString();
        System.out.println("request data:"+data);

        //ģ�����˽���
        RSAUtils.decryptByAesAndRsaPrivateKey(encryptDataStruct);

        //����˼���
        String outputStr="";

        String responseparam="T1/gUoPDW/mAawkszRsYk7oSavzjCUrZDNK2EWLsbWWnmgWjyq/JrptFUt/D+hQ0/DS0OFmbRvMsLEIwdhtZpA==@RAsBIEW4HGnqfhiny0+qnUKGXOCXgHTUMiEISLa1usDYUKxSmVkalahB7/jqPpDbjbkn+ZZR/PjUCGLVk1pgpVUXOiwK7dxq5beirOSqxVfuV8Ph3Kgg+Ra0kHX5Ma05s3HV59SlMy6Q1e3BYq0ZWkRGNbHU9kkVl6LTjHfjXaI=";
        String decodedata= RSAUtils.decryptByAesAndRsaPublickey(responseparam);
        System.out.println("response data:"+decodedata);
    }
}
