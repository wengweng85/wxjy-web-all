package com.insigma.common.util;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * ip��ַ������
 *
 * @author admin
 * @date 2012-4-28
 */
public class IPUtil {

    /**
     * �õ�����ϵͳ����ip
     * <p>
     * ͨ���˷�������ͨ��<code>NetworkInterface</code>��<code>getNetworkInterfaces</code>�õ�����ϵͳ��������ӿ���Ϣ
     * ������һ������ӿ��Ͽ��԰󶨶��ip��ַ,��Ҫ�����е�ip��ַ��ɸѡ,ͨ��<code>127.0.0.1</code>�ĵ�ַҪ���ˡ�
     * <p>
     * ��û��<code>192</code>��ͷ��ip��ַ���ڵ������,��<code>192</code>�ĵ�ַ����ѡ,��β�����<code>192</code>��ͷ�ĵ�ַ
     *
     * @return ip��ַ <code>String<code>
     */
    public static String getInetAddress() {
        List<String> ip = new ArrayList<String>();
        List<String> ipother = new ArrayList<String>();
        Enumeration<NetworkInterface> nis = null;//����ӿ���
        try {
            nis = NetworkInterface.getNetworkInterfaces(); //�õ����еĵ�����ӿ�
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                Enumeration<InetAddress> ias = ni.getInetAddresses();//�õ����е�InetAddress
                while (ias.hasMoreElements()) {
                    InetAddress ia = ias.nextElement();
                    if (ia instanceof Inet4Address
                            && !"127.0.0.1".equals(ia.getHostAddress())) {
                        //System.out.println("--����ϵͳ����IPV4֮һ--��" + ia.getHostAddress() + "��");//����ϵͳ����ip
                        //��192��ͷ��ipͨ�����ڲ�ʹ��ip
                        if (!ia.getHostAddress().startsWith("192")) {
                            ip.add(ia.getHostAddress());
                        } else {
                            ipother.add(ia.getHostAddress());
                        }
                    } else if (ia instanceof Inet6Address && !"".equals(ias)) {
                        //System.out.println("--����ϵͳ����IPV6֮һ--��" + ia.getHostAddress() + "��");//����ϵͳ����ip
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ip.size() == 0 && ipother.size() != 0) {
            return ipother.get(0);
        } else if (ip.size() > 0) {
            return ip.get(0);
        } else {
            return "";
        }
    }

    /**
     * �õ��ͻ��˷���ip��ַ
     *
     * @param request
     * @return
     */
    public static String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader(" x-forwarded-for ");
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader(" Proxy-Client-IP ");
        }
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader(" WL-Proxy-Client-IP ");
        }
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null) {
            ip = "";
        }

        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    /**
     * ����
     *
     * @param a
     */
    public static void main(String[] a) {
        System.out.println(getInetAddress());
    }
}
