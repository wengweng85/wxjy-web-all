package com.insigma.common.fastdfs;


import java.io.File;


public class FastdfsClientTest implements Runnable {

	private int i;
	public FastdfsClientTest(int i){
        this.i=i;
	}
	/**
	 * 上传文件测试
	 */
	//@Test
	public void testUpload(){
		try{
			long start=System.currentTimeMillis();
			for(int i=0;i<10;i++){
				String filepath="d:/test.jpg";
				File file=new File(filepath);
				String serverFilePath=FastDFSClient.saveFile(file);
				System.out.println(serverFilePath);
			}
			long end=System.currentTimeMillis();
			System.out.println("耗费:"+(end-start)+"毫秒");
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	
	public static void main(String [] args){
		for(int i=0;i<10;i++){
			FastdfsClientTest fastdfsClientTest=new FastdfsClientTest(i);
			Thread thread=new Thread(fastdfsClientTest);

			thread.start();
		}
	}

	@Override
	public void run() {
		try{
			System.out.println(Thread.currentThread().getName());
			long start=System.currentTimeMillis();
			for(int i=0;i<10;i++){
				String filepath="d:/test.jpg";
				File file=new File(filepath);
				String serverFilePath=FastDFSClient.saveFile(file);
				System.out.println(serverFilePath);
			}
			long end=System.currentTimeMillis();
			System.out.println("耗费:"+(end-start)+"毫秒");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
