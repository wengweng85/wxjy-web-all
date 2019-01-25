package com.insigma.common.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.csvreader.CsvWriter;
import com.insigma.common.util.RandomNumUtil;


/**
 * excel�ļ�������ع�����
 * @author admin
 * 
 *
 */
public class ExcelExport<T> {
	
	private static Log log=LogFactory.getLog(ExcelExport.class);
	
	public String sqlFileTranserToCvs(Connection connection,String sql) throws SQLException,IOException{
		// �ж��ļ��Ƿ����,������ɾ��,Ȼ�󴴽��±��
		String prefix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ RandomNumUtil.getRandomString(6);
		File tmp =File.createTempFile(prefix,"cvs");
		// ����CSVд����
		CsvWriter csvWriter = new CsvWriter(tmp.getPath(),',', Charset.forName("GBK"));

		// ���ݲ�ѯ��ʼ
		PreparedStatement	preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		// ��ȡ�������ͷ
		ResultSetMetaData md = resultSet.getMetaData();
		int columnCount = md.getColumnCount();

		String[] columnNameList=new String[columnCount];
		List<String[]> list = new ArrayList<>();
		for (int i = 0; i < columnCount; i++) {
			columnNameList[i]=md.getColumnName(i);
		}
		list.add(columnNameList);

		csvWriter.writeRecord(columnNameList);

		// ���ݼ�¼��
		int i = 0;
		// ��ʱ���ݴ洢
		StringBuffer stringBuffer = new StringBuffer();

		while (resultSet.next()) {

		    // ��¼��
		    i++;
		    // ����������ȡ����ֵ
		    for (int j = 1; j<=columnCount; j++){

		        String value = resultSet.getString(j);
		        //������
		        stringBuffer.append(value);
		        if (j != columnCount){
		            stringBuffer.append(',');
		        }
		    }

		    String buffer_string = stringBuffer.toString();
		    String[] content = buffer_string.split(",");
		    csvWriter.writeRecord(content);
		    stringBuffer.setLength(0);
		}

		// �ļ����
		csvWriter.flush();
		csvWriter.close();
		return tmp.getPath();
	}
	

	/**
	 * exportExcel
	 * @param headers
	 * @param dataset
	 * @param fileName
	 */
	public String  exportCvs(String[] headers,Collection<T> dataset, String fileName)  throws SQLException,IOException{  
		//��ѯexcel�������ݿ�ʼ
		log.info("����csv��ʽ�ļ���ʼ"+new Date().toLocaleString());
		Date start=new Date();
		// �ж��ļ��Ƿ����,������ɾ��,Ȼ�󴴽��±��
		File cvsfilepath =File.createTempFile("excel_error_",".csv");
		// ����CSVд����
		CsvWriter csvWriter = new CsvWriter(cvsfilepath.getPath(),',', Charset.forName("GBK"));
		csvWriter.writeRecord(headers);
		 // �����������ݣ�����������  
        Iterator<T> it = dataset.iterator();  
        while (it.hasNext()) {  
        	ExcelExportModel t = (ExcelExportModel) it.next();  
        	csvWriter.writeRecord(t.getExcel_info().split(","));
        }
		csvWriter.close();  
		Date end=new Date();
		Long cost=end.getTime()-start.getTime();
		log.info("����csv��ʽ�ļ�����"+new Date().toLocaleString()+"����="+cost);
		return cvsfilepath.getPath();
    }  
	
	/**
	 * exportExcel
	 * @param headers
	 * @param dataset
	 * @param fileName
	 */
	public String  exportExcel(String[] headers,Collection<T> dataset, String fileName) {  
        // ����һ��������  
        XSSFWorkbook workbook = new XSSFWorkbook();  
        // ����һ�����  
        XSSFSheet sheet = workbook.createSheet("Sheet1");  
        // ���ñ��Ĭ���п��Ϊ15���ֽ�  
        sheet.setDefaultColumnWidth((short) 20);  
        // ������������  
        XSSFRow row = sheet.createRow(0);  
        for (short i = 0; i < headers.length; i++) {  
            XSSFCell cell = row.createCell(i);  
            XSSFRichTextString text = new XSSFRichTextString(headers[i].split(",")[0]);  
            cell.setCellValue(text);  
        }  
        try {  
            // �����������ݣ�����������  
            Iterator<T> it = dataset.iterator();  
            int index = 0;  
            while (it.hasNext()) {  
                index++;  
                row = sheet.createRow(index);  
                T t = (T) it.next();  
                // ���÷��䣬����javabean���Ե��Ⱥ�˳�򣬶�̬����getXxx()�����õ�����ֵ  
                Field[] fields = t.getClass().getDeclaredFields();  
                for (short i = 0; i < headers.length; i++) {  
                    XSSFCell cell = row.createCell(i);  
                    String fieldName = headers[i].split(",")[1];
                    String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);  
                    Class tCls = t.getClass();  
                    Method getMethod = tCls.getMethod(getMethodName, new Class[] {});  
                    Object value = getMethod.invoke(t, new Object[] {});  
                    // �ж�ֵ�����ͺ����ǿ������ת��  
                    String textValue ="" ;  
                    // �����������Ͷ������ַ����򵥴���  
                    if(value !=null  && value != ""){  
                        textValue = value.toString();  
                    }  
                    if (textValue !=null ) {  
                        XSSFRichTextString richString = new XSSFRichTextString(textValue);  
                        cell.setCellValue(richString);  
                    }  
                }  
            }  
            return getExportedFile(workbook);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return  null;
        }   
    }  
      
    /** 
     *  getExportedFile
     * ����˵��: ָ��·��������EXCEL�ļ� 
     * @return 
     */  
    public String getExportedFile(XSSFWorkbook workbook) throws Exception {  
    	FileOutputStream fos =null ;  
        try {  
            File file=File.createTempFile("excel_export_", ".xlsx");
            fos=new FileOutputStream(file);
            workbook.write(fos); 
            return file.getPath();
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;
        } finally {  
            if (fos !=null ) {  
                fos.close();  
            }  
        }  
    }  
}
