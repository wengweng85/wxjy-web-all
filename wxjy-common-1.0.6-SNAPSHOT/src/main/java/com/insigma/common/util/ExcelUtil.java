package com.insigma.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
  
/** 
 * excel��ȡ 
 *  
 */  
  
public class ExcelUtil  
{  
    /** ������ */  
    private int totalRows = 0;  
    /** ������ */  
    private int totalCells = 0;  
    /** ������Ϣ */  
    private String errorInfo;  

    /***/
    private static HSSFCellStyle style = null ; 
    
    /** ���췽�� */  
    public ExcelUtil()  
    {  
    }  
  
    /** 
     *  
     * @�������õ������� 
     * @������@return 
     *  
     * @����ֵ��int 
     */  
  
    public int getTotalRows()  
    {  
        return totalRows;  
  
    }  
  
    /** 
     *  
     * @�������õ������� 
     * @������@return 
     * @����ֵ��int 
     */  
  
    public int getTotalCells(){  
        return totalCells;  
    }  
  
    /** 
     *  
     * @�������õ�������Ϣ 
     * @������@return 
     * @����ֵ��String 
     */  
  
    public String getErrorInfo()  
    {  
        return errorInfo;  
    }  
  
    /** 
     *  
     * @��������֤excel�ļ� 
     * @������@param filePath���ļ�����·�� 
     * @������@return 
     * @����ֵ��boolean 
     */  
  
    public boolean validateExcel(String filePath){  
        /** ����ļ����Ƿ�Ϊ�ջ����Ƿ���Excel��ʽ���ļ� */  
        if (filePath == null || !(WDWUtil.isExcel2003(filePath) || WDWUtil.isExcel2007(filePath)))  
        {  
            errorInfo = "�ļ�������excel��ʽ";  
            return false;  
        }  
  
        /** ����ļ��Ƿ���� */  
  
        /*File file = new File(filePath);  
        if (file == null || !file.exists())  
        {  
            errorInfo = "�ļ�������";  
            return false;  
        }*/  
        return true;  
  
    }  
  
    /** 
     *  
     * @�����������ļ�����ȡexcel�ļ� 
     * @������@param �ļ��� 
     * @������@param filePath �ļ��� 
     * @������@return 
     * @����ֵ��List 
     */  
  
    public List<List<String>> read(InputStream is, String filePath){  
        List<List<String>> dataLst = new ArrayList<List<String>>();  
        try  
        {  
            /** ��֤�ļ��Ƿ�Ϸ� */  
            if (!validateExcel(filePath))  
            {  
                System.out.println(errorInfo);  
                return null;  
            }  
  
            /** �ж��ļ������ͣ���2003����2007 */  
 
            boolean isExcel2003 = true;  
            if (WDWUtil.isExcel2007(filePath))  
            {  
                isExcel2003 = false;  
            }  
            /** ���ñ����ṩ�ĸ�������ȡ�ķ��� */  
            dataLst = read(is, isExcel2003);  
            is.close();  
        }  
        catch (Exception ex)  
        {  
            ex.printStackTrace();  
        }  
        finally  
        {  
            if (is != null)  
            {  
                try  
                {  
                    is.close();  
                }  
                catch (IOException e)  
                {  
                    is = null;  
                    e.printStackTrace();  
                }  
            }  
        }  
  
        /** ��������ȡ�Ľ�� */  
        return dataLst;  
  
    }  
  
    /** 
     *  
     * @��������������ȡExcel�ļ� 
     * @������@param inputStream 
     * @������@param isExcel2003 
     * @������@return 
     * @����ֵ��List 
     */  
  
    public List<List<String>> read(InputStream inputStream, boolean isExcel2003){  
        List<List<String>> dataLst = null;  
        try  
        {  
            /** ���ݰ汾ѡ�񴴽�Workbook�ķ�ʽ */  
            Workbook wb = null;  
            if (isExcel2003){  
                wb = new HSSFWorkbook(inputStream);  
            }  
            else  
            {  
                wb = new XSSFWorkbook(inputStream);  
            }  
            dataLst = read(wb);  
  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
        return dataLst;  
  
    }  
  
    /** 
     *  
     * @��������ȡ���� 
     * @������@param Workbook 
     * @������@return 
     * @����ֵ��List<List<String>> 
     */  
  
    private List<List<String>> read(Workbook wb){  
        List<List<String>> dataLst = new ArrayList<List<String>>();  
        /** �õ���һ��shell */  
        Sheet sheet = wb.getSheetAt(0);  
        /** �õ�Excel������ */  
        this.totalRows = sheet.getPhysicalNumberOfRows();  
        /** �õ�Excel������ */  
        if (this.totalRows >= 1 && sheet.getRow(0) != null)  
        {  
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();  
        }  
        /** ѭ��Excel���� */  
  
        for (int r = 0; r <= this.totalRows; r++){  
            Row row = sheet.getRow(r);  
            if (row == null)  
            {  
                continue;  
            }  
            List<String> rowLst = new ArrayList<String>();  
  
            /** ѭ��Excel���� */  
  
            for (int c = 0; c < this.getTotalCells(); c++)  
            {  
                Cell cell = row.getCell(c);  
                String cellValue = "";  
                if (null != cell)  
                {  
                    // �������ж����ݵ�����  
                    switch (cell.getCellType())  
                    {  
                    case HSSFCell.CELL_TYPE_NUMERIC: // ����  
                    	DecimalFormat df = new DecimalFormat("0");//��ֹ���ֱ�ɿ�ѧ������
                    	cellValue = df.format(cell.getNumericCellValue()); 
                        break;  
                    case HSSFCell.CELL_TYPE_STRING: // �ַ���  
                        cellValue = cell.getStringCellValue();  
                        break;  
                    case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean  
                        cellValue = cell.getBooleanCellValue() + "";  
                        break;  
                    case HSSFCell.CELL_TYPE_FORMULA: // ��ʽ  
                        cellValue = cell.getCellFormula() + "";  
                        break;  
                    case HSSFCell.CELL_TYPE_BLANK: // ��ֵ  
                        cellValue = "";  
                        break;  
                    case HSSFCell.CELL_TYPE_ERROR: // ����  
                        cellValue = "�Ƿ��ַ�";  
                        break;  
                    default:  
                        cellValue = "δ֪����";  
                        break;  
                    }  
                }  
                rowLst.add(cellValue);  
            }  
  
            /** �����r�еĵ�c�� */  
            dataLst.add(rowLst);  
        }  
        return dataLst;  
    }  
    
    /** 
     * ͨ��������Sql����Excel�ļ���Response���������Ҫָ��Connection 
     * @param response HttpServletResponse Response 
     * @param conn Connection ָ�������ݿ����� 
     * @param sql String ��ѯ��Sql��� 
     * @param sheetName String ������Excel Sheet���� 
     * @throws SQLException 
     */  
    @SuppressWarnings("deprecation")
	public static void export( HttpServletResponse response , Connection conn ,  String sql , String sheetName  )  throws SQLException {  
        PreparedStatement ps = null ;  
        ResultSet rs = null ;  
        ps = conn.prepareStatement( sql ) ;  
        rs = ps.executeQuery() ;  
  
        ResultSetMetaData rsmd = rs.getMetaData() ;  
        
        String [] cNames = getColumnNames( rsmd ) ;  
  
        HSSFWorkbook wb = new HSSFWorkbook() ;  
        style = wb.createCellStyle() ;  
        style.setAlignment( HSSFCellStyle.ALIGN_CENTER ) ;  
  
        HSSFSheet sheet = wb.createSheet(sheetName);
        int rowCnt = 0 ;  
        createColumnRow(rowCnt,sheet,cNames); 
        while ( rs.next() ) {  
            HSSFRow row = sheet.createRow( rowCnt + 1 ) ;  
            for ( int i = 0 ; i < cNames.length ; i++ ) {  
  
                HSSFCell cell = row.createCell( ( short ) i ) ;  
                String val = rs.getString( cNames[ i ] ) ;  
                if ( null == val ) {  
                    val = "" ;  
                }  
                //cell.setCellValue( val.toUpperCase() ) ;  
                cell.setCellValue( val ) ;  
            }  
            rowCnt++ ;  
        }  
        try {  
            OutputStream os = response.getOutputStream() ;  
            response.reset() ;  
            response.setContentType( "application/vnd.ms-excel" ) ;  
            response.setHeader( "Content-disposition" , "attachment; filename=" + new String(getFileName(sheetName).getBytes("GBK"),"iso-8859-1")) ;  
            wb.write( os ) ;  
            if ( conn != null ) {  
                conn.close() ;  
            }  
        }  
        catch ( IOException ex ) {  
            ex.printStackTrace();
        }  
    }  
  
    /** 
     * ����excel������ 
     * @param rsmd ResultSetMetaData 
     * @return String[] 
     */  
    @SuppressWarnings("deprecation")
	private static void createColumnRow(int rowCnt, HSSFSheet sheet,String [] cNames ) {  
    	 HSSFRow row = sheet.createRow( rowCnt ) ;  
         for ( int i = 0 ; i < cNames.length ; i++ ) {  
             HSSFCell cell = row.createCell( ( short ) i ) ;  
             cell.setCellValue( cNames[i]) ;  
         }  
         rowCnt++;
    }  
  
    /** 
     * ���Դ�����е������� 
     * @param rsmd ResultSetMetaData 
     * @return String[] 
     */  
    private static String[] getColumnNames( ResultSetMetaData rsmd ) {  
        try {  
            StringBuffer result = new StringBuffer("") ;  
            for ( int i = 1 ; i <= rsmd.getColumnCount() ; i++ ) {  
                result.append(rsmd.getColumnLabel( i )).append(",");  
            }  
            if ( result.length()>0 ) {  
                return result.substring( 0 , result.length() - 1 ).toString().split( "," ) ;  
            }  
        }  
        catch ( Exception e ) {  
            return null ;  
        }  
        return null ;  
    }  
  
    /** 
     * ��õ������ļ�ȫ�� 
     * @param tableName String 
     * @return String 
     */  
    private static String getFileName( String tableName ) {  
        return tableName + System.currentTimeMillis() + ".xls" ;
    }  
    
    
    /** 
     * ͨ��������List<HashMap>����Excel�ļ���Response������� 
     * @param response HttpServletResponse Response 
     * @param conn Connection ָ�������ݿ����� 
     * @param sql String ��ѯ��Sql��� 
     * @param sheetName String ������Excel Sheet���� 
     * @throws SQLException 
     */
	@SuppressWarnings("deprecation")
	public static void exportByList( HttpServletResponse response , List<LinkedHashMap<String, Object>> list , String sheetName  )  throws SQLException { 
    	String [] cNames = new String[0];
    	if(list.size()>0){
    		LinkedHashMap<String, Object> map = list.get(0);
    		Set<String> keys = map.keySet();
    		cNames = new String[keys.size()];
    		StringBuffer result = new StringBuffer("") ;  
    		Iterator<String> it = keys.iterator();
    		while (it.hasNext()){
				result.append(it.next()).append(",");  
    		}
    		if ( result.length()>0 ) {  
    			cNames = result.substring( 0 , result.length() - 1 ).toString().split( "," ) ;  
    		}
    	}
  
        HSSFWorkbook wb = new HSSFWorkbook() ;  
        style = wb.createCellStyle() ;  
        style.setAlignment( HSSFCellStyle.ALIGN_CENTER ) ;  
  
        HSSFSheet sheet = wb.createSheet(sheetName);
        int rowCnt = 0 ;  
        createColumnRow(rowCnt,sheet,cNames); 
        
        for(LinkedHashMap<String, Object> map : list){
        	HSSFRow row = sheet.createRow( rowCnt + 1 );
        	for ( int i = 0 ; i < cNames.length ; i++ ) {  
                HSSFCell cell = row.createCell( ( short ) i ) ;
                String val = "";
                if(map.get(cNames[i])!=null){
                	val = map.get(cNames[i]).toString();
                }
                cell.setCellValue( val ) ;  
            }  
            rowCnt++ ;  
        }
        try {  
            OutputStream os = response.getOutputStream() ;  
            response.reset() ;  
            response.setContentType( "application/vnd.ms-excel" ) ;  
            response.setHeader( "Content-disposition" , "attachment; filename=" + new String(getFileName(sheetName).getBytes("GBK"),"iso-8859-1")) ;  
            wb.write( os ) ;  
        }  
        catch ( IOException ex ) {  
            ex.printStackTrace();
        }  
    }  
    
  
    /** 
     *  
     * @������main���Է��� 
     * @������@param args 
     * @������@throws Exception 
     * @����ֵ��void 
     */  
    public static void main(String[] args) throws Exception{  
        /*ExcelUtil poi = new ExcelUtil();  
        // List<List<String>> list = poi.read("d:/aaa.xls");  
        File file=new File("c:/book.xlsx");
        List<List<String>> list = poi.read(new FileInputStream(file),file.getName());  
        if (list != null)  
        {  
            for (int i = 0; i < list.size(); i++)  
            {  
                System.out.print("��" + (i) + "��");  
                List<String> cellList = list.get(i);  
                for (int j = 0; j < cellList.size(); j++)  
                {  
                    // System.out.print("    ��" + (j + 1) + "��ֵ��");  
                    System.out.print("    " + cellList.get(j));  
                }  
                System.out.println();  
            }  
        }  */
    	Map<String, Object> map = new HashMap<String, Object>(4);
	    map.put("a", "a");
	    map.put("b", "b");
	    map.put("c", "c");
	    map.put("d", "d");
	    Set<String> keys = map.keySet();
	    String [] str = new String[keys.size()];
	    StringBuffer result = new StringBuffer("") ;  
	    Iterator<String> it = keys.iterator();
	    while (it.hasNext()){
		   System.out.println(it.next());
		   result.append(it.next()).append(",");  
	    }
	    if ( result.length()>0 ) {  
		   str = result.substring( 0 , result.length() - 1 ).toString().split( "," ) ;  
	    }  
	    for(int i=0;i<str.length;i++){
		   System.out.println(str[i]);
	    }
    }  
}  
  
class WDWUtil  
{  
    /** 
     *  
     * @�������Ƿ���2003��excel������true��2003 
     * @������@param filePath���ļ�����·�� 
     * @������@return 
     * @����ֵ��boolean 
     */  
  
    public static boolean isExcel2003(String filePath){  
        return filePath.matches("^.+\\.(?i)(xls)$");  
    }  
  
    /** 
     *  
     * @�������Ƿ���2007��excel������true��2007 
     * @������@param filePath���ļ�����·�� 
     * @������@return 
     * @����ֵ��boolean 
     */  
  
    public static boolean isExcel2007(String filePath){  
        return filePath.matches("^.+\\.(?i)(xlsx)$");  
  
    }  
  
}  
