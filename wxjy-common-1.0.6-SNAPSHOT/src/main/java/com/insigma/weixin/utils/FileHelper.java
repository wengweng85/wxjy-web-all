package com.insigma.weixin.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
    /**
     * �ļ�����������
     * creatTxtFile------>1.�����ļ�
     * writeTxtFile------>2.��������ӵ��ļ���
     * readTxtFile------->3.��ȡ�ļ�����
     * replace----------->4.�ļ�������(�ȸ�����ɾ��)
     * copyFile---------->5.�����ļ�����(��һ���ļ����ݸ��Ƶ���һ���ļ���)
     * getAllFileName---->6.�õ�ָ���ļ�����������ļ����ļ���
     * fileCopy---------->7.���Ƶ����ļ�(����Դ�ļ���Ŀ���ļ�·����·��+�ļ�����ָ���Ƿ�������)
     * copyDirectory----->8.��������Ŀ¼(����ԴĿ¼��Ŀ��Ŀ¼·����·��+Ŀ¼����ָ���Ƿ�������)
     * deleteFile-------->9.ɾ���ļ�(����Ҫɾ�����ļ�����·��+�ļ���)
     * deleteDirectory--->10.ɾ���ļ����Լ�Ŀ¼�µ��ļ�     ��ɾ���ļ�����������ļ����ļ��У���ɾ��Ŀ¼
     * moveFolder-------->11.�ƶ��ļ�/�ļ���(�ȸ�����ɾ��Դ�ļ�/Դ�ļ���)
     */
    /**
     * 1
     * �����ļ�
     * path�������ļ��ı���Ŀ¼·��
     * filename:�ļ�����
     */
    public static boolean creatTxtFile(String path , String filename) throws IOException {
        boolean flag = false; //�����ļ�ʧ��
        String filenameTemp = path + "/" + filename + ".txt";
        File file = new File(filenameTemp);
        if (!file.exists()) {
            file.createNewFile();
            flag = true; //�����ļ��ɹ�
        }
        return flag;
    }

    /**
     * 2
     * ������д���ļ�
     * content:����д���ļ�������
     * filenameTemp:Ҫд����ļ�·��      �ļ�·��+�ļ�����
     */
    public static boolean writeTxtFile (String content,String filenameTemp) throws IOException {
        boolean flag = false; //д��ʧ��
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            File file = new File(filenameTemp); // �ļ�·��
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));//���ļ�����������

            StringBuffer buf = new StringBuffer();
            buf.append(content);

            //������д�뵽�ļ���
            pw = new PrintWriter(new FileOutputStream(file));
            pw.write(buf.toString());
            pw.flush();
            flag = true; //д��ɹ�
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }if (br != null) {
                br.close();
            }
        }
        return flag;
    }

    /**
     * 3
     * ��ȡ�ļ�����
     * filePath��Ҫ��ȡ���ļ�·��
     */
    public static String readTxtFile(String filePath){
        StringBuffer buff = new StringBuffer("");
        try {
            String encoding="UTF-8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //�ж��ļ��Ƿ����
                InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);//���ǵ������ʽ
                BufferedReader bufferedReader = new BufferedReader(read);
                String str = null;
                while((str = bufferedReader.readLine()) != null){
                    buff.append(str);
                }
                read.close();
            }else{
                System.err.println("�Ҳ���ָ�����ļ�");
            }
        } catch (Exception e) {
            System.out.println("��ȡ�ļ����ݳ���");
            e.printStackTrace();
        }

        return buff.toString();
    }

    /**
     * 4
     * ���ļ�������(�ȸ�����ɾ��)
     * oldpath:�ɵ��ļ�·��
     * newfilename:���ļ�����
     */
    public static void replace(String oldpath , String newfilename) throws IOException{
        File file = new File(oldpath);
        if(oldpath.contains("\\")){
            oldpath = oldpath.replace("\\", "/");
        }
        int index = oldpath.lastIndexOf("/");
        String qianzhui = oldpath.substring(0, index);
        String path = qianzhui + "/" + newfilename + ".txt";
        File ff = new File(path);
        if (!ff.exists()) {
            ff.createNewFile();
        }
        copyFile(file,ff);
        if(file.exists()){
            file.delete();
        }
    }

    /**
     * 5
     * �����ļ�����(��һ���ļ����ݸ��Ƶ���һ���ļ���)
     * sourceFile:Դ�ļ�
     * targetFile:Ŀ���ļ�
     */
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));// �½��ļ����������������л���
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));// �½��ļ���������������л���

            // ��������
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }

            outBuff.flush();// ˢ�´˻���������
        } catch (Exception e) {
            e.printStackTrace();
        }finally {// �ر���
            if (inBuff != null) inBuff.close();
            if (outBuff != null) outBuff.close();
        }
    }

    /**
     * 6
     * �õ�ָ���ļ�����������ļ����ļ���
     * path:�ļ���·��
     */
    public static List<String> getAllFileName(String path){
        List<String> ss= new ArrayList<String>();
        File file = new File(path);//�õ�ָ���ļ���
        File[] files = file.listFiles();//ָ��Ŀ¼�µ������ļ����ļ���
        if(null != files && files.length > 0){
            for(int i = 0 ; i < files.length ; i++){
                if(files[i].isFile()){//�ж��Ƿ����ļ�
                    ss.add(files[i].getName());
                }
            }
        }
        return ss;
    }

    /**
     * 7
     * ���Ƶ����ļ�
     * sourceFileName:Դ�ļ���(·�� +�ļ���)
     * targetFileName��Ŀ���ļ���(·��+�ļ���)
     * overlay: ���Ŀ���ļ����ڣ��Ƿ񸲸�          true:������           false:��������
     * ������Ƴɹ�����true�����򷵻�false
     */
    public static boolean fileCopy(String sourceFileName, String targetFileName,boolean overlay) {
        File sourceFile = new File(sourceFileName);
        //1.�ж�Դ�ļ��Ƿ����
        if (!sourceFile.exists()) {
            System.err.println("�����ļ�ʧ�ܣ�Դ�ļ�������");
            return false;
        } else if (!sourceFile.isFile()) {//�Ƿ����ļ�
            System.err.println("�����ļ�ʧ��,Դ�ļ���" + sourceFileName + "����һ���ļ�");
            return false;
        }

        //2.�ж�Ŀ���ļ��Ƿ����
        File targetFile = new File(targetFileName);
        if (targetFile.exists()) {
            //���Ŀ���ļ����ڲ�������
            if (overlay) {//������
                new File(targetFileName).delete();//ɾ���Ѿ����ڵ�Ŀ���ļ�������Ŀ���ļ���Ŀ¼���ǵ����ļ�
            }else{
                System.err.println("Ŀ���ļ����ڣ���ѡ�������ǣ��ļ������и���");
                return true;
            }
        } else {
            // ���Ŀ���ļ�����Ŀ¼�����ڣ��򴴽�Ŀ¼
            if (!targetFile.getParentFile().exists()) {// Ŀ���ļ�����Ŀ¼������
                if (!targetFile.getParentFile().mkdirs()) {
                    System.err.println("�����ļ�ʧ�ܣ�����Ŀ���ļ�����Ŀ¼ʧ��");
                    return false;
                }
            }
        }

        //3.�����ļ�
        int byteread = 0; // ��ȡ���ֽ���
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(sourceFile);
            out = new FileOutputStream(targetFile);
            byte[] buffer = new byte[1024];

            while ((byteread = in.read(buffer)) != -1) {
                out.write(buffer, 0, byteread);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (out != null)
                    out.close();
                if (in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 8
     * ��������Ŀ¼
     * srcDirName��ԴĿ¼��Ŀ¼��(·��+Ŀ¼��)
     * destDirName��Ŀ��Ŀ¼��Ŀ¼��(·��+Ŀ¼��)
     * overlay�����Ŀ��Ŀ¼���ڣ��Ƿ񸲸�         true:������           false:��������
     * ������Ƴɹ�����true�����򷵻�false
     */
    public static boolean copyDirectory(String srcDirName, String destDirName, boolean overlay) {
        //1.�ж�ԴĿ¼�Ƿ����
        File srcDir = new File(srcDirName);
        if (!srcDir.exists()) {
            System.err.println("����Ŀ¼ʧ��,ԴĿ¼" + srcDirName + "������");
            return false;
        } else if (!srcDir.isDirectory()) {//�Ƿ�ΪĿ¼�ļ���
            System.err.println("����Ŀ¼ʧ��,ԴĿ¼" + srcDirName + "����Ŀ¼");
            return false;
        }

        //2.���Ŀ��Ŀ¼���������ļ��ָ�����β��������ļ��ָ���:\
        if (!destDirName.endsWith(File.separator)) {//if(a.endsWith(b))�ж��ַ���a�ǲ������ַ���b��β
            destDirName = destDirName + File.separator;
        }

        //3.�ж�Ŀ���ļ����Ƿ����
        File destDir = new File(destDirName);
        if (destDir.exists()) {
            if (overlay) {// �����������ɾ���Ѵ��ڵ�Ŀ��Ŀ¼
                new File(destDirName).delete();
            } else {
                System.err.println("Ŀ���ļ����ڣ���ѡ�������ǣ��ļ��в����и���");
                return true;
            }
        } else {// ����Ŀ��Ŀ¼
            if (!destDir.mkdirs()) {
                System.out.println("����Ŀ¼ʧ�ܣ�����Ŀ��Ŀ¼ʧ��");
                return false;
            }
        }

        //4.�����ļ����µ������ļ����ļ���
        boolean flag = true;
        File[] files = srcDir.listFiles();//�õ�ԴĿ¼�µ������ļ����ļ���
        for (int i = 0; i < files.length; i++) {
            // �����ļ�
            if (files[i].isFile()) {//�ļ�
                flag = fileCopy(files[i].getAbsolutePath(),destDirName + files[i].getName(), overlay);
                if (!flag)
                    break;
            } else if (files[i].isDirectory()) {//�ļ���
                flag = copyDirectory(files[i].getAbsolutePath(),destDirName + files[i].getName(), overlay);
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            System.err.println("����Ŀ¼" + srcDirName + "��" + destDirName + "ʧ��");
            return false;
        } else {
            return true;
        }
    }

    /**
     * 9
     * ɾ���ļ�
     * path:ɾ���ļ����ļ�·�����ļ�·��+�ļ���
     */
    public static boolean deleteFile(String path){
        File ff = new File(path);
        if(ff.exists() && ff.isFile()){
            ff.delete();
            return true;
        }else{
            System.err.println(path+"--->û���ҵ���·�����ļ�");
            return false;
        }
    }

    /**
     * 10
     * ɾ���ļ����Լ�Ŀ¼�µ��ļ�     ��ɾ���ļ�����������ļ����ļ��У���ɾ��Ŀ¼
     * path:��ɾ��Ŀ¼���ļ�·��
     * �ļ���ɾ���ɹ�:true������:false
     */
    public static boolean deleteDirectory(String path) {
        boolean flag;
        //���sPath�����ļ��ָ�����β���Զ�����ļ��ָ���
        if (!path.endsWith(File.separator)) {
            path = path + File.separator;
        }
        File dirFile = new File(path);
        //���dir��Ӧ���ļ������ڣ����߲���һ��Ŀ¼�����˳�
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            System.err.println(path + "������/����һ���ļ���");
            return false;
        }
        flag = true;
        //ɾ���ļ����µ������ļ�(������Ŀ¼)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {//ɾ�����ļ�
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            }else {//ɾ����Ŀ¼
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        if (dirFile.delete()) {//ɾ����ǰĿ¼
            return true;
        } else {
            return false;
        }
    }

    /**
     * 11
     * �ƶ��ļ�/�ļ���(�ȸ�����ɾ��Դ�ļ�/Դ�ļ���)
     * sourcepath:Ҫ�ƶ���Դ�ļ�/Դ�ļ���·��
     * tagerpath:�ƶ����·��
     * overlay�����Ŀ��Ŀ¼���ڣ��Ƿ񸲸�         true:������           false:��������
     */
    public static boolean moveFolder(String sourcepath,String tagerpath,boolean overlay){
        boolean bb = false;
        File file = new File(sourcepath);
        if(!file.exists()){
            System.err.println("Ҫ�ƶ����ļ�/�ļ���·��������");
            return bb;
        }else{
            if(file.isFile()){//�ļ�
                if( fileCopy(sourcepath,tagerpath,overlay) ){
                    if( deleteFile(sourcepath) ){
                        System.err.println(sourcepath+"�ļ��ƶ��ɹ�");
                        bb = true;
                    }
                }
            }else {
                if( copyDirectory(sourcepath,tagerpath,overlay) ){
                    if( deleteDirectory(sourcepath) ){
                        System.err.println(sourcepath+"�ļ����ƶ��ɹ�");
                        bb = true;
                    }
                }
            }
            return bb;
        }
    }
}