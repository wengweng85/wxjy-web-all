package com.insigma.tag.form.input;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

/**
 * �Զ����ǩ֮�ı����
 * 
 * @author admin
 *
 */
public class TextareaEditTag implements Tag {

	private PageContext pageContext;

	// property
	private String property;
	
	//label
   private String label;

	//ռλ����,����label��һ��
	private String cols;
			
	//ռλ����,����label��һ��
   private String rows;

	//�Ƿ����
	private String required;
	
	// ֵ
	private String value;

	// У�����
	private String validate;
	
	//�Ƿ�ֻ��
	private String readonly;
	
	//onclick�¼�
	private String onclick;
	
	//onchange�¼�
	private String onchange;
	
	//onblur�¼�
	private String onblur;
	
	//onkeydown�¼�
	private String onkeydown;
	
	//onkeypress�¼�
	private String onkeypress;
	
	//onkeyup�¼�
	private String onkeyup;

	//��ʾ��
	private String placeholder;
	

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getOnblur() {
		return onblur;
	}

	public void setOnblur(String onblur) {
		this.onblur = onblur;
	}

	public String getOnkeydown() {
		return onkeydown;
	}

	public void setOnkeydown(String onkeydown) {
		this.onkeydown = onkeydown;
	}

	public String getOnkeypress() {
		return onkeypress;
	}

	public void setOnkeypress(String onkeypress) {
		this.onkeypress = onkeypress;
	}

	public String getOnkeyup() {
		return onkeyup;
	}

	public void setOnkeyup(String onkeyup) {
		this.onkeyup = onkeyup;
	}

	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	public String getReadonly() {
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getValue() {
		return value;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}
	
	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getCols() {
		return cols;
	}

	public void setCols(String cols) {
		this.cols = cols;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doStartTag() throws JspException {
		//��ֵ���
		validate=(validate==null)?"":validate;
		value=(value==null)?"":value;
		readonly=(readonly==null)?"":readonly;
		required=(required==null)?"":required;
		cols=(cols==null)?"1,11":cols;
		rows=(rows==null)?"1":rows;


		String [] col=cols.split(",");
		int labelcol=Integer.parseInt(col[0]);
		int inputcol=Integer.parseInt(col[1]);

		//�Ƿ�ֻ��
		boolean isreadonly=Boolean.parseBoolean(readonly);
		//�Ƿ����
		boolean isrequired=Boolean.parseBoolean(required);

		String placeholders = placeholder;
		if(placeholder == null){
			placeholders = ""+label;
		}
		JspWriter out = pageContext.getOut();
		StringBuffer sb=new StringBuffer();
		sb.append("<label class=\"control-label col-xs-"+labelcol+"  col-sm-"+labelcol+"\">"+label);
		if(isrequired){
		 sb.append("<span class=\"require\">*<span>");
		}
		sb.append("</label>");

		sb.append("<div class=\" col-xs-"+inputcol+"  col-sm-"+inputcol+"  \">");
		sb.append("<textarea class=\"form-control\"  rows=\""+rows+"\" id=\""+property+"\" name=\""+property+"\"  placeholder=\""+placeholders+"\"  validate=\""+validate+"\" ");
		if(isreadonly){
		 sb.append(" readonly=\"readonly\" ");
		}
		//onclick�¼�
		if(onclick!=null){
		  sb.append(" onclick=\""+onclick+"\" ");
		}
		//onblur
		if(onblur!=null){
		  sb.append(" onblur=\""+onblur+"\" ");
		}
		//onkeypress
		if(onkeypress!=null){
		  sb.append(" onkeypress=\""+onkeypress+"\" ");
		}
		//onkeydown
		if(onkeydown!=null){
		  sb.append(" onkeydown=\""+onkeydown+"\" ");
		}
		//onkeyup
		if(onkeyup!=null){
		  sb.append(" onkeyup=\""+onkeyup+"\" ");
		}
		//onchange
		if(onchange!=null){
		  sb.append(" onchange=\""+onchange+"\" ");
		}
		sb.append(">");
		sb.append(value);
		sb.append("</textarea></div>");

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
