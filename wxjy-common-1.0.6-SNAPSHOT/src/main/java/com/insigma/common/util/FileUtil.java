package com.insigma.common.util;

import com.insigma.resolver.AppException;
import org.apache.commons.lang.StringUtils;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
Java IO ��һ��ʹ��ԭ�� �� 

һ����������Դ��ȥ�򣩷��ࣺ

1 �����ļ��� FileInputStream, FileOutputStream, ( �ֽ��� )FileReader, FileWriter( �ַ� )

2 ���� byte[] �� ByteArrayInputStream, ByteArrayOutputStream( �ֽ��� )

3 ���� Char[]: CharArrayReader, CharArrayWriter( �ַ��� )

4 ���� String: StringBufferInputStream, StringBufferOuputStream ( �ֽ��� )StringReader, StringWriter( �ַ��� )

5 �������������� InputStream, OutputStream,( �ֽ��� ) Reader, Writer( �ַ��� )

�������Ƿ��ʽ������֣�

1 ��Ҫ��ʽ������� PrintStream, PrintWriter

�������Ƿ�Ҫ����֣�

1 ��Ҫ���壺 BufferedInputStream, BufferedOutputStream,( �ֽ��� ) BufferedReader, BufferedWriter( �ַ��� )

�ġ������ݸ�ʽ�֣�

1 �������Ƹ�ʽ��ֻҪ����ȷ���Ǵ��ı��ģ� : InputStream, OutputStream �������д� Stream ����������

2 �����ı���ʽ������Ӣ���뺺�ֻ��������뷽ʽ���� Reader, Writer �������д� Reader, Writer ������

�塢����������֣�

1 �����룺 Reader, InputStream ���͵�����

2 ������� Writer, OutputStream ���͵�����

����������Ҫ��

1 ���� Stream �� Reader,Writer ��ת���ࣺ InputStreamReader, OutputStreamWriter

2 ��������������� ObjectInputStream, ObjectOutputStream

3 �����̼�ͨ�ţ� PipeInputStream, PipeOutputStream, PipeReader, PipeWriter

4 ���ϲ����룺 SequenceInputStream

5 �����������Ҫ�� PushbackInputStream, PushbackReader, LineNumberInputStream, LineNumberReader

*/

/**
 * �ļ�������
 * @author kezp
 *
 */
public class FileUtil {
	/**
	 * ��BΪ��λ
	 */
	private static String sign_B = "B";
	/**
	 * ��KΪ��λ
	 */
	private static String sign_K = "K";
	
	/**
	 * ��MΪ��λ
	 */
	private static String sign_M = "M";
	
	/**
	 * ��GΪ��λ
	 */
	private static String sign_G = "G";
	
	private static int count=0;
	
	@SuppressWarnings("rawtypes")
	private static HashMap hm = new HashMap();
	/**
	 * �����ļ���
	 * @param dir
	 * @throws Exception
	 */
	public static void createFolder(String dir){
		File file = new File(dir);
		if (!file.exists()) {  //��������ڣ��򴴽�
			file.mkdirs();
		}
	}
	
	
	/**
	 * �����ļ���ȫ�¸���
	 * @param fullname
	 * @param fileContent
	 * @throws Exception
	 */
	public static void createFile(String fullname ,String fileContent) throws Exception {
		createFile(fullname ,fileContent,false);
	}
	

	/**
	 * �����ļ�
	 * @param fullname
	 * @param fileContent
	 * @throws Exception
	 */
	public static void createFile(String fullname ,String fileContent,boolean append) throws Exception {
		File file = new File(fullname);
		if (!file.exists()){
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file,append);   
		fw.append(fileContent);
		fw.close();
	}
	
	
	/**
	 * ɾ���ļ�
	 * @param fullname
	 * @throws Exception
	 */
	public static void delFile(String fullname) throws Exception {
		File file = new File(fullname);
		file.delete();
	}
	/**
	 * ɾ���ļ�
	 * @param fullname
	 * @throws Exception
	 */
	public static void delFileOnExist(String fullname) throws Exception {
		try {
			File file = new File(fullname);
			if (file.exists()) {
				file.delete();
			}
		}catch (Exception e){
			System.out.println("·���ļ�������");
		}
	}
	
	/**
	 * �ļ�������
	 * @param oldname
	 * @param newName
	 * @throws Exception
	 */
	public static void reNameFile(String oldname,String newName) throws Exception {
		File oldfile = new File(oldname);
		File newfile = new File(newName);
		oldfile.renameTo(newfile);
	}
	

	/**
	 * �ļ�����
	 * @param sourceFile
	 * @param targetFile
	 * @throws Exception
	 */
	public static void copyFile(File sourceFile,File targetFile) throws Exception {
 
        FileInputStream input = new FileInputStream(sourceFile);  
        BufferedInputStream inBuff=new BufferedInputStream(input);  
  
        FileOutputStream output = new FileOutputStream(targetFile);  
        BufferedOutputStream outBuff=new BufferedOutputStream(output);  
		          
        //��������   
        byte[] b = new byte[1024 * 5];  
        int len;  
        while ((len =inBuff.read(b)) != -1) {  
            outBuff.write(b, 0, len);  
        }  
        // ˢ�´˻���������   
        outBuff.flush();  
          
        //�ر���   
        inBuff.close();  
        outBuff.close();  
        output.close();  
        input.close();  
	}
	
	/**
	 * �ļ�����
	 * @param sourceFile
	 * @param targetFile
	 * @throws Exception
	 */
	public static void copyFile(String sourceFile,String targetFile) throws Exception {
		copyFile(new File(sourceFile),new File(targetFile));
	}
	
	
     
	/**
	 * �����ļ��п���
	 * @param sourceDir
	 * @param targetDir
	 * @throws Exception
	 */
    public static void copyDirectiory(String sourceDir, String targetDir)  throws Exception {  
        // �½�Ŀ��Ŀ¼   
        (new File(targetDir)).mkdirs();  
        // ��ȡԴ�ļ��е�ǰ�µ��ļ���Ŀ¼   
        File[] file = (new File(sourceDir)).listFiles();  
        for (int i = 0; i < file.length; i++) {  
            if (file[i].isFile()) {  
                // Դ�ļ�   
                File sourceFile=file[i];  
                // Ŀ���ļ�   
                File targetFile=new File(new File(targetDir).getAbsolutePath()+File.separator+file[i].getName());  
                copyFile(sourceFile,targetFile);  
            }  
            if (file[i].isDirectory()) {  
                // ׼�����Ƶ�Դ�ļ���   
                String dir1=sourceDir + "/" + file[i].getName();  
                // ׼�����Ƶ�Ŀ���ļ���   
                String dir2=targetDir + "/"+ file[i].getName();  
                copyDirectiory(dir1, dir2);  
            }  
        }  
    }  

    
    /**
     * ɾ�������ļ����������
     * @param path
     * @throws Exception
     */
	public static void delAllFile(String path) throws Exception{ 
		File file = new File(path); 
		if (!file.exists()) { 
			return; 
		} 
		if (!file.isDirectory()) { 
			return; 
		} 
		String[] tempList = file.list(); 
		File temp = null; 
		for (int i = 0; i < tempList.length; i++) { 
			if (path.endsWith(File.separator)) { 
				temp = new File(path + tempList[i]); 
			} else { 
				temp = new File(path + File.separator + tempList[i]); 
			} 
			if (temp.isFile()) { 
				temp.delete(); 
			} 
			if (temp.isDirectory()) { 
				delAllFile(path + "/" + tempList[i]);// ��ɾ���ļ���������ļ� 
				File folderPath = new File(path + "/" + tempList[i]);  // ��ɾ�����ļ���
				folderPath.delete(); 
			} 
		}
		file.delete();  //ɾ�������ļ���
	} 
	
	

	/**
	 * ����·�����⣬���·������βû��"/",������"/"
	 * @param path
	 * @return
	 */
	public static String resolvePath(String path) {
		path = StringUtils.replace(path, "\\", "/");
		int idxLastSlash = path.lastIndexOf("/");
		if (path.length() != (idxLastSlash + 1)) {
			path = path + "/";
		}
		return path;
	}
	
	
	/**
	 * �޸ĳ���<br>
	 * �ڲ��ݹ���ã�������Ŀ¼�ĸ���
	 * 
	 * @param path
	 *            ·��
	 * @param from
	 *            ԭʼ�ĺ�׺���������Ǹ�(.��)
	 * @param to
	 *            �����ĺ�׺��Ҳ�����Ǹ�(.��)
	 */
	public static void reName(String path, String from, String to) {
		File f = new File(path);
		File[] fs = f.listFiles();
		for (int i = 0; i < fs.length; ++i) {
			File f2 = fs[i];
			if (f2.isDirectory()) {
				reName(f2.getPath(), from, to);
			} else {
				String name = f2.getName();
				if (name.endsWith(from)) {
					f2.renameTo(new File(f2.getParent() + "/"
							+ name.substring(0, name.indexOf(from)) + to));
				}
			}
		}
	}

	
	/**
	 * ȡ�ļ���С
	 * @param fileName �ļ���
	 * @param cal_Sign B K M G
	 * @return
	 */
	public static double getFileSize(String fileName,String cal_Sign){
		
		long sign = 1;
		if ("K".equals(cal_Sign)){
			sign = sign*1024;
		} else if ("M".equals(cal_Sign)){
			sign = sign*1024*1024;
		}else if ("G".equals(cal_Sign)){
			sign = sign*1024*1024*1024;
		}
		File file = new File(fileName);

		BigDecimal bd1 = new BigDecimal(new Long(file.length()).toString());
		BigDecimal bd2 = new BigDecimal(new Long(sign).toString());
		return bd1.divide(bd2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * ȡ�ļ���С
	 * @param size
	 * @return
	 */
	public static String getFileSize(long size){
		NumberFormat format=NumberFormat.getInstance();//���ָ�ʽ����
		format.setMaximumFractionDigits(2);//С��λ���2λ
		if(size/1024<1){
			return format.format(size)+sign_B;
		}else if(size/(1024*1024)<1){
			return format.format(size/1024d)+sign_K;
		}else if(size/(1024*1024*1024)<1){
			return format.format(size/(1024d*1024d))+sign_M;
		}
		return format.format(size/(1024d*1024d*1024d))+sign_G;
	}
	
    /**
     * ���ַ�Ϊ��λ��ȡ�ļ��������ڶ��ı������ֵ����͵��ļ�
     */
    @SuppressWarnings("unused")
	public static String readFileByChars(String fileName) {
        File file = new File(fileName);
        Reader reader = null;
/*        try {
            System.out.println("���ַ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���ֽڣ�");
            // һ�ζ�һ���ַ�
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // ����windows�£�\r\n�������ַ���һ��ʱ����ʾһ�����С�
                // ������������ַ��ֿ���ʾʱ���ỻ�����С�
                // ��ˣ����ε�\r����������\n�����򣬽������ܶ���С�
                if (((char) tempchar) != '\r') {
                    System.out.print((char) tempchar);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        try {
            //System.out.println("���ַ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�����ֽڣ�");
        	StringBuffer sb = new StringBuffer();
            // һ�ζ�����ַ�
            char[] tempchars = new char[30];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));
            // �������ַ����ַ������У�charreadΪһ�ζ�ȡ�ַ���
            while ((charread = reader.read(tempchars)) != -1) {
            	
                // ͬ�����ε�\r����ʾ
                if ((charread == tempchars.length) && (tempchars[tempchars.length - 1] != '\r')) {
                	sb.append(tempchars);
                    //System.out.print(tempchars);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempchars[i] == '\r') {
                            continue;
                        } else {
                            //System.out.print(tempchars[i]);
                            sb.append(tempchars[i]);
                        }
                    }
                }
            }
            return sb.toString();
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * ����Ϊ��λ��ȡ�ļ��������ڶ������еĸ�ʽ���ļ�
     */
    public static String readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            //System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
            reader = new BufferedReader(new FileReader(file));
            StringBuffer sb = new StringBuffer();
            String tempString = null;
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString);
            }
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
    
    
    /**
     * ����BufferedInputStream��ʽ��ȡ�ļ�����
     * 
     * @param filename
     * @return
     * @throws java.io.IOException
     */
    public static int count(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        byte[] c = new byte[1024];
        int count = 0;
        int readChars = 0;
        while ((readChars = is.read(c)) != -1) {
            for (int i = 0; i < readChars; ++i) {
                if (c[i] == '\n') {
					++count;
				}
            }
        }
        is.close();
        return count;
    }

    /***
     * ����·���������ļ��б�
     * @param filePath		�ļ�����·��
     * @param startname	�ļ���ʼ��
     * @param endname	�ļ���β��
     * @return					�ļ��б�
     * @throws Exception
     */
	@SuppressWarnings("rawtypes")
	public List getFileList(String filePath,String startname,String endname) throws Exception{
		File dir = new File(filePath);
		if (!(dir.isDirectory() && dir.exists())) {
			throw new Exception("�����ļ��У������ļ��в�����,·��Ϊ��"+filePath);
		}
		File[] files = dir.listFiles();  //�г������ļ�
		List filelist = new ArrayList();
		for (int i=0;i<files.length;i++){
			boolean flag = true;
			String filename = files[i].getName();	
			if(!"".equals(startname)){	
				if (!filename.startsWith(startname)) {  //�Դ�д��PACKTAB��ͷ
					flag = false;
				}
			}
			if(!"".equals(endname)){
				if (!filename.toLowerCase().endsWith(endname)) { //.xml��β��
					flag = false;
				}
			}
			if(flag){
				//FileInfoDTO fileinfo = new FileInfoDTO();
		/*		fileinfo.setFileDir(filePath);  //�ļ�·��
				fileinfo.setFileName(files[i].getName()); //�ļ���
				
				long size = files[i].length();
				BigDecimal bd1 = new BigDecimal(new Long(size).toString());
				Double dd = new Double(bd1.doubleValue() / 1024);// ��KΪ��λ����
				BigDecimal bd2 = new BigDecimal(dd.toString()).setScale(1,BigDecimal.ROUND_HALF_UP);// �����������
				fileinfo.setFileSize(String.valueOf(bd2.doubleValue())); // �ļ���С����
				
				*//**************ʱ������*****************//*
				long time = files[i].lastModified();
				Date d = new Date(time);
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
				sdf.setTimeZone(TimeZone.getTimeZone("ETC/GMT-8"));
				Date newdate = DateUtil.parseDateTime(sdf.format(d));
				fileinfo.setModtime(newdate);  //����޸�ʱ��
				*//**************************************//*
				
				filelist.add(fileinfo);  */
			}
		}		
		return filelist;  //���ط��ϸ�ʽ���ļ�
	}
    
	public static boolean checkFile(String filename){
		File file = new File(filename);
		if(file.exists()){
			return true;
		}else{
			return false;
		}
	}
	
	//���������ļ�(Ѱ���ļ��е�����)
	@SuppressWarnings("unchecked")
	public static void listAllFile(String sourceDir){
        File[] file = (new File(sourceDir)).listFiles();
        for (int i = 0; i < file.length; i++) {  
 
            if (file[i].isFile()) {
            	
          		String fileName=file[i].getName();
          		String pf = "";
          		try {
              		String[] token = fileName.split("\\.");
              		pf = token[1];
              		hm.put(pf, pf);
          		}catch(Exception e){
          			
          		}

                // Դ�ļ�   
                //File sourceFile=file[i];
              	if (!file[i].getName().toLowerCase().endsWith(".rar") && !file[i].getName().toLowerCase().endsWith(".dmp")
              			&& !file[i].getName().toLowerCase().endsWith(".exe")
              			&& !file[i].getName().toLowerCase().endsWith(".dll")
              			&& !file[i].getName().toLowerCase().endsWith(".msi")
              			&& !file[i].getName().toLowerCase().endsWith(".log")
              			) {
              		try{
	              		String context = readFileByChars(file[i].getAbsolutePath());
	              		//if (context.indexOf("http://www.xinxinbaidu.com.cn")>-1){
	              		if (context.indexOf("<iframe src= width=0 height=0></iframe>")>-1){
	              			System.out.println(file[i].getAbsolutePath());
	              			count=count+1;
	              		}
              		}catch(Exception e){
              			System.out.println(pf);
              		}
            	}
            	
            }  
            if (file[i].isDirectory()) {  
            	listAllFile(file[i].getAbsolutePath());
            }  
        } 
	}
	
	/**
	 * ����ftlģ������PDF�ļ�
	 * @param request
	 * @param response
	 * @param data     ����
	 * @param FileName �ļ�����
	 * @param ftlPath  ftlģ�������ļ���·��
	 * @param ftlName  ftlģ������
	 * @throws Exception
	 */
	public static File createPDFFile(HttpServletRequest request,HttpServletResponse response,Map<String, Object> data,
			String FileName,String ftlPath,String ftlName) throws Exception{
		String basePath = request.getSession().getServletContext().getRealPath("/");
		
		String imageFile = basePath+"/WEB-INF/ftl/img/watermark.jpg"; // ˮӡͼƬ·��
		String waterMarkName = "";//����ˮӡ
		File finalFile = File.createTempFile("contractTemplate", ".pdf");
		String pdfFile = finalFile.getPath(); // �ļ�����·��
		
		
		/* �������� */
		Configuration cfg = new Configuration();
		/* ָ��ģ���ŵ�·�� */
		//cfg.setDirectoryForTemplateLoading(new File(basePath + "/WEB-INF/ftl"));
		cfg.setDirectoryForTemplateLoading(new File(basePath + "/"+ftlPath));
		cfg.setDefaultEncoding("UTF-8");
		// cfg.setObjectWrapper(new DefaultObjectWrapper());
		
		/* ������ָ����ģ��Ŀ¼�м��ض�Ӧ��ģ���ļ� */
		// contractTemplate
		Template temp = cfg.getTemplate(ftlName);
		
		/* ��������ģ�� */
		//Map root = new HashMap();
		//root.put("user", "Big Joe");
		
		/* �����ɵ�����д����ʱ.html�� */
		File htmlfile = File.createTempFile("contractTemplate", ".html");
		if (!htmlfile.exists()) {
			htmlfile.createNewFile();
		}
		// Writer out = new FileWriter(file);
		Writer out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(htmlfile), "utf-8"));
		// Writer out = new OutputStreamWriter(System.out);
		temp.process(data, out);
		out.flush();
		
		//create file
		String url = htmlfile.toURI().toURL().toString();
		File pdffile = File.createTempFile("contractTemplate", ".pdf");
		String outputFile = pdffile.getPath();
		OutputStream os = new FileOutputStream(outputFile);
		
		ITextRenderer renderer = new ITextRenderer();
		// PDFEncryption pdfEncryption = new PDFEncryption(null,null,PdfWriter.ALLOW_PRINTING);
		// renderer.setPDFEncryption(pdfEncryption); //ֻ�д�ӡȨ�޵�
		renderer.setDocument(url);
		
		// �����������
		ITextFontResolver fontResolver = renderer.getFontResolver();
		try {
			fontResolver.addFont(basePath + "/WEB-INF/ftl/fonts/simsun.ttc",
					BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		renderer.layout();
		try {
			renderer.createPDF(os);
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		// ���ˮӡ
		waterMark(pdffile.getPath(), imageFile, pdfFile, waterMarkName,basePath); 
		
		//download file
		
		//�½�һ��2048�ֽڵĻ�����
		/*response.setHeader("Content-disposition","attachment; filename="+ new String(FileName.getBytes("GBK"),"iso-8859-1"));
	    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(finalFile));
	    BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
	    //�½�һ��2048�ֽڵĻ�����
	    byte[] buff = new byte[2048];
	    int bytesRead=0;
	    while ((bytesRead = bis.read(buff, 0, buff.length)) != -1) {
	         bos.write(buff,0,bytesRead);
	    }
	    if (bis != null)
	        bis.close();
	    if (bos != null)
	        bos.close();*/
		
		//System.out.println("ת���ɹ���");
		os.close();
		
		//ɾ����ʱ�ļ�
		htmlfile.delete();
		pdffile.delete();
		
		return finalFile;
		
		//finalFile.delete();
	}
	
	/**
	 * ����ftlģ�����ɲ�����PDF�ļ�
	 * @param request
	 * @param response
	 * @param data     ����
	 * @param FileName �ļ�����
	 * @param ftlPath  ftlģ�������ļ���·��
	 * @param ftlName  ftlģ������
	 * @throws Exception
	 */
	public static File downPDFFile(HttpServletRequest request,HttpServletResponse response,Map<String, Object> data,
			String FileName,String ftlPath,String ftlName) throws Exception{
		String basePath = request.getSession().getServletContext().getRealPath("/");
		
		String imageFile = basePath+"/WEB-INF/ftl/img/watermark.jpg"; // ˮӡͼƬ·��
		String waterMarkName = "";//����ˮӡ
		File finalFile = File.createTempFile("contractTemplate", ".pdf");
		String pdfFile = finalFile.getPath(); // �ļ�����·��
		
		
		/* �������� */
		Configuration cfg = new Configuration();
		/* ָ��ģ���ŵ�·�� */
		//cfg.setDirectoryForTemplateLoading(new File(basePath + "/WEB-INF/ftl"));
		cfg.setDirectoryForTemplateLoading(new File(basePath + ftlPath));
		cfg.setDefaultEncoding("UTF-8");
		// cfg.setObjectWrapper(new DefaultObjectWrapper());
		
		/* ������ָ����ģ��Ŀ¼�м��ض�Ӧ��ģ���ļ� */
		// contractTemplate
		Template temp = cfg.getTemplate(ftlName);
		
		/* ��������ģ�� */
		//Map root = new HashMap();
		//root.put("user", "Big Joe");
		
		/* �����ɵ�����д����ʱ.html�� */
		File htmlfile = File.createTempFile("contractTemplate", ".html");
		if (!htmlfile.exists()) {
			htmlfile.createNewFile();
		}
		// Writer out = new FileWriter(file);
		Writer out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(htmlfile), "utf-8"));
		// Writer out = new OutputStreamWriter(System.out);
		temp.process(data, out);
		out.flush();
		
		//create file
		String url = htmlfile.toURI().toURL().toString();
		File pdffile = File.createTempFile("contractTemplate", ".pdf");
		String outputFile = pdffile.getPath();
		OutputStream os = new FileOutputStream(outputFile);
		
		ITextRenderer renderer = new ITextRenderer();
		// PDFEncryption pdfEncryption = new PDFEncryption(null,null,PdfWriter.ALLOW_PRINTING);
		// renderer.setPDFEncryption(pdfEncryption); //ֻ�д�ӡȨ�޵�
		renderer.setDocument(url);
		
		// �����������
		ITextFontResolver fontResolver = renderer.getFontResolver();
		try {
			fontResolver.addFont(basePath + "/WEB-INF/ftl/fonts/simsun.ttc",
					BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		renderer.layout();
		try {
			renderer.createPDF(os);
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		// ���ˮӡ
		waterMark(pdffile.getPath(), imageFile, pdfFile, waterMarkName,basePath); 
		
		//download file
		
		//�½�һ��2048�ֽڵĻ�����
		response.setHeader("Content-disposition","attachment; filename="+ new String(FileName.getBytes("GBK"),"iso-8859-1"));
	    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(finalFile));
	    BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
	    //�½�һ��2048�ֽڵĻ�����
	    byte[] buff = new byte[2048];
	    int bytesRead=0;
	    while ((bytesRead = bis.read(buff, 0, buff.length)) != -1) {
	         bos.write(buff,0,bytesRead);
	    }
	    if (bis != null) {
			bis.close();
		}
	    if (bos != null) {
			bos.close();
		}
		
		//System.out.println("ת���ɹ���");
		os.close();
		
		//ɾ����ʱ�ļ�
		htmlfile.delete();
		pdffile.delete();
		
		return finalFile;
		
		//finalFile.delete();
	}
	
	
	
	/**
	 * 
	 * @param inputFile Ҫ����ˮӡ���ļ�·��
	 * @param imageFile ˮӡͼƬ��·��
	 * @param outputFile �ļ�����·��
	 * @param waterMarkName ����ˮӡ
	 * @param basePath  ��Ŀ·��
	 */
	public static void waterMark(String inputFile, String imageFile,
			String outputFile, String waterMarkName,String basePath) {
		try {
			PdfReader reader = new PdfReader(inputFile);

            OutputStream os = new FileOutputStream(outputFile);
            PdfStamper stamper = new PdfStamper(reader, os);
            
            com.itextpdf.text.pdf.BaseFont base = com.itextpdf.text.pdf.BaseFont.createFont(
					basePath+"/WEB-INF/ftl/fonts/simsun.ttc,1", "Identity-H", true);// ʹ��ϵͳ����
			//BaseFont base = BaseFont.createFont("C:/WINDOWS/Fonts/SIMSUN.TTC,1", "Identity-H", true);// ʹ��ϵͳ����

			int total = reader.getNumberOfPages() + 1;
			Image image = Image.getInstance(imageFile);

			// ͼƬλ��
			image.setAbsolutePosition(200, 500);
			PdfContentByte under;
			int j = waterMarkName.length();
			char c = 0;
			int rise = 0;
			for (int i = 1; i < total; i++) {
				rise = 400;
				under = stamper.getUnderContent(i);
				under.beginText();
				under.setFontAndSize(base, 30);

				if (j >= 15) {
					under.setTextMatrix(200, 120);
					for (int k = 0; k < j; k++) {
						under.setTextRise(rise);
						c = waterMarkName.charAt(k);
						under.showText(c + "");
					}
				} else {
					under.setTextMatrix(240, 100);
					for (int k = 0; k < j; k++) {
						under.setTextRise(rise);
						c = waterMarkName.charAt(k);
						under.showText(c + "");
						rise -= 18;

					}
				}

				// ���ˮӡ����
				under.endText();

				// ���ˮӡͼƬ
				under.addImage(image);

				// ����Ȧ
				//under.ellipse(250, 450, 350, 550);
				//under.setLineWidth(1f);
				//under.stroke();
			}
			stamper.close();
            os.flush();
            os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		/*try {
        PdfReader reader = new PdfReader(inputFile);
        OutputStream os = new FileOutputStream(outputFile);
        PdfStamper stamper = new PdfStamper(reader, os);
        //������������ñȽϹؼ������������֧�����ĵ�д��
        BaseFont base = BaseFont.createFont("C:/WINDOWS/Fonts/SIMSUN.TTC,1", "Identity-H", true);// ʹ��ϵͳ����
        int total = reader.getNumberOfPages() + 1;
        Image image = Image.getInstance(imageFile);
        // ͼƬλ��
        //image.setAbsolutePosition(110, 110);

		image.setAbsolutePosition(400, 480);
        PdfContentByte under;
        Rectangle pageRect = null;
        for (int i = 1; i < total; i++) {
            pageRect = stamper.getReader().
                       getPageSizeWithRotation(i);
            // ����ˮӡX,Y����
            float x = pageRect.getWidth()/10;
            float y = pageRect.getHeight()/10-10;
            // ���PDF���
            under = stamper.getOverContent(i);
            under.saveState();
            // set Transparency
            PdfGState gs = new PdfGState();
            // ����͸����Ϊ0.2
            gs.setFillOpacity(1.f);
            under.setGState(gs);
            // ע������������һ��restoreState ����������Ч
            under.restoreState();
            under.beginText();
            under.setFontAndSize(base, 30);
            under.setColorFill(new BaseColor(238, 209, 212));
           
            // ˮӡ���ֳ�45�Ƚ���б
            under.showTextAligned(Element.ALIGN_CENTER
                    , waterMarkName, x,
                    y, 55);
            // ���ˮӡ����
            under.endText();
         // ���ˮӡͼƬ
			under.addImage(image);
			
            under.setLineWidth(1f);
            under.stroke();
        }
        stamper.close();
        os.flush();
        os.close();
    } catch (Exception e) {
        e.printStackTrace();
    }*/
	}


	/**
	 * ����ftlģ�����ɲ�����WORD�ļ�
	 * @param request
	 * @param response
	 * @param dataMap     ����
	 * @param FileName �ļ�����
	 * @param ftlPath  ftlģ�������ļ���·��
	 * @param ftlName  ftlģ������
	 * @throws Exception
	 */
	public static void downWordFile(HttpServletRequest request,HttpServletResponse response,Map<String, Object> dataMap,
									String FileName,String ftlPath,String ftlName) throws Exception{
		String basePath = request.getSession().getServletContext().getRealPath("/");

		/* �������� */
		Configuration cfg = new Configuration();
		/* ָ��ģ���ŵ�·�� */
		//cfg.setDirectoryForTemplateLoading(new File(basePath + "/WEB-INF/ftl"));
		cfg.setDirectoryForTemplateLoading(new File(basePath + ftlPath));
		cfg.setDefaultEncoding("UTF-8");
		// cfg.setObjectWrapper(new DefaultObjectWrapper());

		/* ������ָ����ģ��Ŀ¼�м��ض�Ӧ��ģ���ļ� */
		// contractTemplate
		Template temp = cfg.getTemplate(ftlName);

		//����ĵ�·��������
		File finalFile = File.createTempFile("contractTemplate", ".doc");
		String wordFile = finalFile.getPath(); // �ļ�����·��

		File outFile = new File(wordFile);
		Writer out = null;
		FileOutputStream fos=null;
		fos = new FileOutputStream(outFile);
		OutputStreamWriter oWriter = new OutputStreamWriter(fos,"UTF-8");
		//����ط������ı��벻�ɻ�ȱ��ʹ��main������������ʱ��Ӧ�ÿ��ԣ����������web���󵼳�ʱ������word�ĵ��ͻ�򲻿������Ұ�XML�ļ�������Ҫ�Ǳ����ʽ����ȷ���޷�������
		//out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
		out = new BufferedWriter(oWriter);
		temp.process(dataMap, out);
		out.flush();
		out.close();
		fos.close();

		//download file

		//�½�һ��2048�ֽڵĻ�����
		response.setHeader("Content-disposition","attachment; filename="+ new String(FileName.getBytes("GBK"),"iso-8859-1"));
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(finalFile));
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		//�½�һ��2048�ֽڵĻ�����
		byte[] buff = new byte[2048];
		int bytesRead=0;
		while ((bytesRead = bis.read(buff, 0, buff.length)) != -1) {
			bos.write(buff,0,bytesRead);
		}
		if (bis != null) {
			bis.close();
		}
		if (bos != null) {
			bos.close();
		}

		//System.out.println("ת���ɹ���");
		//ɾ����ʱ�ļ�
		finalFile.delete();
	}

	/**
	 * ����ftlģ������WORD�ļ�
	 * @param dataMap     ����
	 * @param FileName �ļ�����
	 * @param temp  Template
	 * @throws Exception
	 */
	public File createWordFile(Map<String, Object> dataMap,
									String FileName,Template temp) throws Exception{

		//����ĵ�·��������
		File finalFile = File.createTempFile(FileName, ".doc");
		String wordFile = finalFile.getPath(); // �ļ�����·��

		File outFile = new File(wordFile);
		Writer out = null;
		FileOutputStream fos=null;
		fos = new FileOutputStream(outFile);
		OutputStreamWriter oWriter = new OutputStreamWriter(fos,"UTF-8");
		//����ط������ı��벻�ɻ�ȱ��ʹ��main������������ʱ��Ӧ�ÿ��ԣ����������web���󵼳�ʱ������word�ĵ��ͻ�򲻿������Ұ�XML�ļ�������Ҫ�Ǳ����ʽ����ȷ���޷�������
		//out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
		out = new BufferedWriter(oWriter);
		temp.process(dataMap, out);
		out.flush();
		out.close();
		fos.close();
		return finalFile;
	}


	/** �ж��ļ�����  */
	public static FileType getFileType(String filePath) throws IOException {
		// ��ȡ�ļ�ͷ
		String fileHead = getFileHeader(filePath);

		if (fileHead != null && fileHead.length() > 0) {
			fileHead = fileHead.toUpperCase();
			FileType[] fileTypes = FileType.values();

			for (FileType type : fileTypes) {
				if (fileHead.startsWith(type.getValue())) {
					return type;
				}
			}
		}

		return null;
	}

	/**
	 * �����ļ�����������ȡ�ļ�ͷ��Ϣ
	 *
	 * @param is
	 * @return �ļ�ͷ��Ϣ
	 */
	public static FileType getFileType(InputStream  is) {
		byte[] b = new byte[4];
		if(is!=null){
			try {
				is.read(b, 0, b.length);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		FileType[] fileTypes = FileType.values();

		String fileHead=getFileHeader(b);
		for (FileType type : fileTypes) {
			if (fileHead.startsWith(type.getValue())) {
				return type;
			}
		}
		return null;
	}


	/** ��ȡ�ļ�ͷ */
	private static String getFileHeader(String filePath) throws IOException {
		byte[] b = new byte[28];
		InputStream inputStream = null;

		try {
			inputStream = new FileInputStream(filePath);
			inputStream.read(b, 0, 28);
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}

		return bytesToHex(b);
	}

	/** ���ֽ�����ת����16�����ַ��� */
	public static String bytesToHex(byte[] src){
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}



	/**
	 * �����ļ�ת���ɵ��ֽ������ȡ�ļ�ͷ��Ϣ
	 *
	 * @param filePath
	 *            �ļ�·��
	 * @return �ļ�ͷ��Ϣ
	 */
	public static String getFileHeader(byte[] b) {
		String value = bytesToHexString(b);
		return value;
	}

	/**
	 * ��Ҫ��ȡ�ļ�ͷ��Ϣ���ļ���byte����ת����string���ͱ�ʾ
	 * ������δ�������������ļ���������֤�ķ�����
	 * ���ֽ������ǰ��λת����16�����ַ���������ת����ʱ��Ҫ�Ⱥ�0xFF��һ�������㡣
	 * ������Ϊ�������ļ������ֽ������У��кܶ��Ǹ�����������������󣬿��Խ�ǰ��ķ���λ��ȥ����
	 * ����ת���ɵ�16�����ַ�����ౣ����λ�������������С��10����ôת����ֻ��һλ��
	 * ��Ҫ��ǰ�油0����������Ŀ���Ƿ���Ƚϣ�ȡ��ǰ��λ���ѭ���Ϳ�����ֹ��
	 * @param srcҪ��ȡ�ļ�ͷ��Ϣ���ļ���byte����
	 * @return �ļ�ͷ��Ϣ
	 */
	private static String bytesToHexString(byte[] src) {
		StringBuilder builder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		String hv;
		for (int i = 0; i < src.length; i++) {
			// ��ʮ�����ƣ����� 16���޷���������ʽ����һ�������������ַ�����ʾ��ʽ����ת��Ϊ��д
			hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
			if (hv.length() < 2) {
				builder.append(0);
			}
			builder.append(hv);
		}

		System.out.println("��ȡ�ļ�ͷ��Ϣ:"+builder.toString());

		return builder.toString();
	}




	/**
     * ����
     * @param args
     * @throws Exception
     */
	public static void main(String[] args) throws Exception {
		//System.out.println(getFileSize("D:/tuxedoprj/HRSIND/bak/JYTJK_all_20110424_204826.dmp","M"));
		//System.out.println(readFileByChars("D:/tuxedoprj/HRSIND/bak/20110424/JYTJK_all_20110424_204457.log"));
		//System.out.println(readFileByLines("D:/tuxedoprj/HRSIND/bak/20110424/JYTJK_all_20110424_204457.bat"));
		//System.out.println(readFileByChars("D:/tuxedoprj/HRSIND/bak/20110424/JYTJK_all_20110424_204457.bat"));
		
/*		StringBuffer sb = new StringBuffer();
		sb.append("fasdfasd\rkezp adf");
		System.out.println(sb.toString());
		String str= sb.toString();
		if (str.length()>4){
			str=str.substring(0,4);
		}
		System.out.println(str);*/
		
		//System.out.println(count("d:/JYJHK119900DY012011040120110403.xml"));
		//System.out.println(count("d:/01JHK201102330100IY01.xml"));
		
		//OracleExp exp = new OracleExp();
		//System.out.println(exp.isErrorXML("d:/test_lwjc.xml"));
		//reNameFile("d:/test_lwjc2.xml","d:/test_lwjc2_rename.xml");
		
		//����ļ�
		 //String sourceDir="C:/Դ����";
		 //String sourceDir="C:/Documents and Settings/kezp2/����/Դ����";
		 //String sourceDir="E:/�Ż���/Դ����";
		 //String sourceDir="E:/�Ż���/��װ�ֲ�";
		 //String sourceDir="E:/�Ż���/����";
		 String sourceDir="C:/Users/Thinkpad/Desktop/���ݲ�����ʾ��������/��Ƭ";
		 
		   File[] file = (new File(sourceDir)).listFiles();
	        for (int i = 0; i < file.length; i++) {  
	            if (file[i].isFile()) {
	            	System.out.println(file[i].getName());
	            }
	        }
		 //String sourceDir="E:/����";
		 //String sourceDir="E:/Apache2";
		 //String sourceDir="E:/Oracle92";
		/* listAllFile(sourceDir);
		 System.out.println(count);
		 
		 for (Object o : hm.keySet()) {
			System.out.println("����Ϊ��"+o.toString());
		 }*/
		 
		
		
	}

}
