package com.insigma.common.ipparser;

/**
 * ip��ַ������ȡ��
 * @author admin
 * @date 2014-11-21
 *
 */
public class IPSeekerUtil {
	
	/**
	 * ��ȡip��ַ����
	 * @param ipaddress
	 * @return
	 */
    public String getIpCountry(String ipaddress){
        if(!"".equals(ipaddress)){
            IPSeeker ipseeker=new IPSeeker();
            return ipseeker.getIPLocation(ipaddress).getCountry();
        }else{
            return "δ֪����";
        }

    }
    
   /**
    * ��ȡip��ַ����
    * @param ipaddress
    * @return
    */
    public  String getIpArea(String ipaddress){
        if(!"".equals(ipaddress)) {
            IPSeeker ipseeker = new IPSeeker();
            return ipseeker.getIPLocation(ipaddress).getArea();
        }else{
            return "δ֪����";
        }
    }
}
