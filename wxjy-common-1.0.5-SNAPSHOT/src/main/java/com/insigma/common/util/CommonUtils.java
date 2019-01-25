package com.insigma.common.util;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.MalformedURLException;

public class CommonUtils {
	/**
	 * ����·���µ��ļ���
	 * @param path
	 * @return
	 */
	public static String getNewFile(String path){
		File savePath = new File(path);
		File[] files = savePath.listFiles();
		if(files.length == 0){
			return "";
		}
		File file = null;
		for(int i=0; i<files.length; i++){
			if(i==0){
				file = files[i];
			}else if(files[i].lastModified() >= files[i-1].lastModified()){
				file = files[i];
			}
		}
		return file.getName();
	}

	/*
	 * ��ȡ��Ŀ�ĸ�Ŀ¼
	 * ��Ϊtomcat��weblogic��ȡ�ĸ�Ŀ¼��һ�£�������Ҫ�˷���
	 */
	public static String getWebRootUrl(HttpServletRequest request) {
		String fileDirPath = request.getSession().getServletContext().getRealPath("/");
		if (fileDirPath == null) {
			//�������Ϊ�գ����ʾ������Ϊweblogic������Ҫʹ������ķ���
			try {
				fileDirPath = request.getSession().getServletContext().getResource("/").getFile() + "/wxjy-api/WEB_INF/";
				System.out.print("Weblogic��ȡ��Ŀ�ĸ�Ŀ¼:"+fileDirPath);
				return fileDirPath;
			} catch (MalformedURLException e) {
				System.out.print("��ȡ��Ŀ�ĸ�Ŀ¼����");
			}
		} else {
			fileDirPath += "/wxjy-api/WEB_INF/";
		}
		return fileDirPath;
	}
}
