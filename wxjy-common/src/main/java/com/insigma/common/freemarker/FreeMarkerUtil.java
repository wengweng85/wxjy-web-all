package com.insigma.common.freemarker;

import com.insigma.common.util.FileUtil;
import com.insigma.resolver.AppException;
import freemarker.template.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.io.*;
import java.util.Map;

/**
 * Created by admin on 2015-06-14.
 */
public class FreeMarkerUtil {

    Log log= LogFactory.getLog(FreeMarkerUtil.class);
    private Configuration freemarker_cfg = null;



    /**
     * ��ȡfreemarker������. freemarker����֧��classpath,Ŀ¼�ʹ�ServletContext��ȡ.
     */
    protected Configuration getFreeMarkerCFG() {
        if (null == freemarker_cfg) {
            // Initialize the FreeMarker configuration;
            // - Create a configuration instance
            freemarker_cfg = new Configuration();
            // - FreeMarker֧�ֶ���ģ��װ�ط�ʽ,���Բ鿴API�ĵ�,���ܼ�:·��,����Servlet������,classpath�ȵ�
            //htmlskin�Ƿ���classpath�µ�һ��Ŀ¼
            freemarker_cfg.setClassForTemplateLoading(this.getClass(), "/ftl");
            freemarker_cfg.setDefaultEncoding("UTF-8");
            // ���ö���İ�װ��
            freemarker_cfg.setObjectWrapper(new DefaultObjectWrapper());
            // �����쳣������//�����Ļ��Ϳ���${a.b.c.d}��ʹû������Ҳ�������
            freemarker_cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        }

        return freemarker_cfg;
    }

    /**
     * ���ɾ�̬�ļ�.
     *
     * @param sRootDir ��������ȡ��Ҫ��̬�ļ���ŵĸ�·��:��Ҫ��Ϊ�Լ������������
     * @param templateFileName ģ���ļ���,���htmlskin·��,����"/tpxw/view.ftl"
     * @param propMap ���ڴ���ģ�������Objectӳ��
     * @param htmlfilename �ɵ��ļ���,���� "1.htm"
     * @param newhtmlFileName �µ��ļ���,���� "1.htm"
     */
    @SuppressWarnings("rawtypes")
	public void geneHtmlFile(String sRootDir,String templateFileName,Map propMap,String htmlfilename, String newhtmlFileName )  throws AppException{
        try {
            Template t = getFreeMarkerCFG().getTemplate(templateFileName);
            t.setEncoding("UTF-8");
            //�����·������,��ݹ鴴����Ŀ¼
            FileUtil.createFolder(sRootDir);
            //html�ļ�
            File indexfile = new File(sRootDir + File.separator + htmlfilename);
            //����Ѿ�����index.html,����������һ���µģ�������ɹ�����û�г��ִ����򸲸ǵ�
            if(indexfile.exists()){
                //�µ�html�ļ�
                File newindexfile = new File(sRootDir + File.separator + newhtmlFileName);
                Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newindexfile),"utf-8"));
                t.process(propMap, out);
                //����µ��ļ��ɹ����ɣ����ϵ�ɾ������������������Ϊindex.html����ɾ��֮
                if(newindexfile.exists()){
                    //System.out.println("�µ��ļ��ɹ����ɣ����ϵ�ɾ������������������Ϊindex.html����ɾ��֮");
                    FileUtil.copyFile(newindexfile,indexfile);
                }
            }
            //���û�����ɹ�,��������һ��index.html
            else{
                Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(indexfile),"utf-8"));
                t.process(propMap, out);
            }
        } catch (TemplateException e) {
            log.error("Error while processing FreeMarker template " + templateFileName,e);
            throw new AppException(e);
        } catch (IOException e) {
            log.error("Error while generate Static Html File " + newhtmlFileName,e);
            throw new AppException(e);
        } catch (Exception e){
            log.error("Error while generate Static Html File " ,e);
            throw new AppException(e);
        }
    }



    /**
     * �����༶Ŀ¼
     *
     * @param aParentDir String
     * @param aSubDir  �� / ��ͷ
     * @return boolean �Ƿ�ɹ�
     */
    public static boolean creatDirs(String aParentDir, String aSubDir) {
        File aFile = new File(aParentDir);
        if (aFile.exists()) {
            File aSubFile = new File(aParentDir + aSubDir);
            if (!aSubFile.exists()) {
                return aSubFile.mkdirs();
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }




}
