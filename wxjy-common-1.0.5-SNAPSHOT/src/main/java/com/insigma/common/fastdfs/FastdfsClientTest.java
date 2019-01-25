package com.insigma.common.fastdfs;


import org.junit.Test;

import java.io.FileInputStream;
import java.util.Date;


public class FastdfsClientTest {

	/**
	 * �ϴ��ļ�����
	 */
	@Test
	public void testUpload(){
		try{
			long start=new Date().getTime();
			for(int i=0;i<1;i++){
				Fastdfs fastdfs=Fastdfs.getInstance();
				String filepath="d:/test.jpg";
				FileInputStream input=new FileInputStream(filepath);
				byte[] byt = new byte[input.available()];
				input.read(byt);
				fastdfs.uploadFile("group1",byt,"jpg");
			}

			long end=new Date().getTime();
			System.out.println("�ķ�:"+(end-start)+"����");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * �ϴ������ļ�����
	 */
	@Test
	public void testSlaveUpload(){
		try{
			long start=new Date().getTime();
			Fastdfs fastdfs=Fastdfs.getInstance();
			String filePath="d:/test.jpg";
			String result=fastdfs.uploadFile(filePath);
			System.out.println(result);
			long end=new Date().getTime();
			System.out.println("�ķ�:"+(end-start)+"����");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**	 * ���ز���
	 */
	//@Test
	public void testDownload(){
		try{
			long start=new Date().getTime();
			Fastdfs fastdfs=Fastdfs.getInstance();
			String downloadfilepath="/group1/M00/00/00/Cr6OBVnxa5KADbNVB7jLnZd7Z5I048.zip";
			fastdfs.downloadFile(downloadfilepath);
			long end=new Date().getTime();
			System.out.println("�ķ�:"+(end-start)+"����");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * ɾ������
	 */
	//@Test
	public void testDelete(){
		try{
			long start=new Date().getTime();
			Fastdfs fastdfs=Fastdfs.getInstance();
			String downloadfilepath="/group1/M00/00/00/Cr6OBFn8H8SADrRFB7jLnZd7Z5I022.zip";
			fastdfs.deleteFile(downloadfilepath);
			long end=new Date().getTime();
			System.out.println("�ķ�:"+(end-start)+"����");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
