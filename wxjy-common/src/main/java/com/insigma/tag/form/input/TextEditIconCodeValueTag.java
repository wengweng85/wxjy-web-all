package com.insigma.tag.form.input;

import com.insigma.tag.form.constraint.TagConstraint;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

/**
 * �Զ����ǩ֮����ѡ���Ϊ����ѡ��
 * 
 * @author admin
 *
 */
public class TextEditIconCodeValueTag implements Tag {

	private PageContext pageContext;
	
	
	// property
	private String property;
	
	//label
	private String label;
	
	private String area;
	
	//ռλ����,����label��һ��
	private String cols;

	// ֵ
	private String value;
	
	//ֵ����
	private String name_value;

    //ҳ�浯����url
	private String url;
	
	private String codetype;
	
	//�Զ���ص�����
	private String callback;
	
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCols() {
		return cols;
	}

	public String getCodetype() {
		return codetype;
	}

	public void setCodetype(String codetype) {
		this.codetype = codetype;
	}

	public void setCols(String cols) {
		this.cols = cols;
	}

	public String getname_value() {
		return name_value;
	}

	public void setname_value(String name_value) {
		this.name_value = name_value;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	private String title;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public PageContext getPageContext() {
		return pageContext;
	}
 

	public String getValue() {
		return value;
	}


	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doStartTag() throws JspException {
		 HttpServletRequest requet=(HttpServletRequest)pageContext.getRequest();
		 String contextPath=requet.getContextPath();
		//��ֵ���
	     value=(value==null)?"":value;
	     name_value=(name_value==null)?"":name_value;
	     area=(area==null)?"['50%', '80%']":area;
	     url=(url==null)? contextPath+"/sys/codetype/toCodeValuesuggest"  :url;//���url��������һ��Ĭ�ϵĵ�ַ
	     title=(title==null)?label+"":title;
	     cols=(cols==null)? TagConstraint.COLS:cols;
	     
	     String [] col=cols.split(",");
	     int labelcol=Integer.parseInt(col[0]);
	     int inputcol=Integer.parseInt(col[1]);
	    
	     JspWriter out = pageContext.getOut();
	     StringBuffer sb=new StringBuffer();
	     
	     sb.append("<label class=\" col-xs-"+labelcol+"  col-sm-"+labelcol+"   control-label\">"+label+"</label>");
	     sb.append("<div class=\" col-xs-"+inputcol+"  col-sm-"+inputcol+"  \">");
	     
	     sb.append("<div class=\"input-group\">");
	     sb.append("<input type=\"hidden\" id=\""+property+"\" name=\""+property+"\"  value=\""+value+"\" >");
	     sb.append("<input type=\"text\" id=\""+property+"_name\" name=\""+property+"_name\"  placeholder=\"�����Ҳ�Ŵ�ѡ��\"  value=\""+name_value+"\"  readonly=\"readonly\" class=\"form-control\"> ");
	     sb.append("<span onclick=\""+property+"_open_select()\"  class=\"input-group-addon\"><i class=\"fa fa-search\"></i></span>");
	     sb.append("</div> </div>");
	     
	     //��Ӧjavascript
	     String url=getUrl()+"?callback_fun_name="+property+"_callback";
	     if(codetype!=null){
	    	 url+="&codetype="+codetype;
	     }
	     sb.append("<script type=\"text/javascript\">");
	     sb.append(" function "+property+"_open_select(){");
	     sb.append(" var url='"+url+"';");
	     sb.append(" var aab301_name_value=$('#"+property+"_name').val();");
	     sb.append(" var select_val_name=rc.encodeURITwice(aab301_name_value);");
	     sb.append(" url+='&select_val_name='+select_val_name;");
	     sb.append(" layer.open({ type: 2,title: '"+title+"',shadeClose: false, shade: 0.8, area: "+area+",content: url})}");
	     sb.append(" function "+property+"_callback(code,value){");
	     sb.append(" $('#"+property+"').val(code);");
	     sb.append(" $('#"+property+"_name').val(value);");
	     if(callback!=null){
			  sb.append(callback+"(code)");
		 }
	     sb.append("}");
	     sb.append("</script>");
		
		 try {  
			   out.write(sb.toString());
	     } catch (IOException e) {  
	           throw new RuntimeException(e);  
	     }     
		 return 0;
	}

	@Override
	public Tag getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPageContext(PageContext arg0) {
		// TODO Auto-generated method stub
		this.pageContext = arg0;
	}

	@Override
	public void setParent(Tag arg0) {
		// TODO Auto-generated method stub
	}

}
