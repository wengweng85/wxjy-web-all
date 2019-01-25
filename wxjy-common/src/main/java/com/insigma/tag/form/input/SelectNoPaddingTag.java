package com.insigma.tag.form.input;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import net.sf.ehcache.Element;

import com.insigma.common.util.EhCacheUtil;
import com.insigma.mvc.model.CodeValue;

/**
 * �Զ����ǩ֮����ѡ����ǩ
 * 
 * @author wengsh
 *
 */
public class SelectNoPaddingTag implements Tag {

	private PageContext pageContext;

	// ������������
	private String codetype;

	// property
	private String property;
	
	
	//label
	private String label;
				
	//ռλ����,����label��һ��
	private String cols;
	
	//��ʾ����
	private String size;

    //�Ƿ����
    private String required;
    
    private String readonly;
    
    private String filter;

	// ֵ
	private String value;

	// У�����
	private String validate;
	
	//�Ƿ��ѡ
	private String multiple;
	
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
	
	
	

	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getReadonly() {
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}
	

	public String getCols() {
		return cols;
	}

	public void setCols(String cols) {
		this.cols = cols;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
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

	public void setCodetype(String codetype) {
		this.codetype = codetype;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public PageContext getPageContext() {
		return pageContext;
	}

	
	
	public String getCodetype() {
		return codetype;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
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
	
	
	public String getMultiple() {
		return multiple;
	}

	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}


	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doStartTag() throws JspException {
		// ��ֵ���
		 validate = (validate == null) ? "" : validate;
		 value = (value == null) ? "" : value;
		 multiple=(multiple==null)?"false":multiple;
		 required=(required==null)?"":required;
		 readonly=(readonly==null)?"":readonly;
		 codetype=(codetype==null)?"":codetype;
		 filter=(filter==null)?"":filter;
		 cols=(cols==null)?"1,2":cols;
		 size=(size==null)?"8":size;
		 
		 String [] col=cols.split(",");
	     int labelcol=Integer.parseInt(col[0]);
	     int inputcol=Integer.parseInt(col[1]);
	   //�Ƿ�ֻ��
	     boolean isreadonly=Boolean.parseBoolean(readonly);
	     //�Ƿ����
	     boolean isrequired=Boolean.parseBoolean(required);
	     JspWriter out = pageContext.getOut();
	     StringBuffer sb=new StringBuffer();
	     sb.append("<label style=\"padding:10px 0px\" class=\"col-sm-"+labelcol+"  col-xs-"+labelcol+" control-label\">"+label);
	     if(isrequired){
	    	 sb.append("<span class=\"require\">*<span>");
	     }
	     sb.append("</label>");
	     sb.append("<div style=\"padding:0px 0px\"  class=\"col-sm-"+inputcol+" col-xs-"+inputcol+" \">");
		 sb.append("<select class=\"form-control selectpicker \" id=\"" + property+ "\" name=\"" + property + "\"  title=\"��ѡ��"+label+"\" value=\"" + value+ "\"  selectOnTab=\"true\" data-actions-box=\"true\" data-size=\""+size+"\"  data-live-search=\"true\" validate=\"" + validate+ "\"   data-selected-text-format=\"count > 2\"");
		 
		 if(isreadonly){
			 sb.append(" disabled ");
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
		 if(Boolean.parseBoolean(multiple)){
			  sb.append(" multiple ");
		 }
		sb.append(">");
		
		//���codetype��Ϊ���ҹ�������Ϊ��
		if(!codetype.equals("")&&filter.equals("")){
			// ��EhCache��ȡ����
			Element element = EhCacheUtil.getManager().getCache("webcache").get(codetype.toUpperCase());
			if (element != null) {
				List<CodeValue> list = (List<CodeValue>) element.getValue();
				//sb.append("<option value=\"\"></option> ");
				for (CodeValue codevalue : list) {
					sb.append("<option ");
					if (value != null && !value.equals("")) {
						if (value.equals(codevalue.getCode_value())) {
							sb.append(" selected ");
						}
					}
					sb.append("  value=\"" + codevalue.getCode_value() + "\">"+ codevalue.getCode_name() + "</option>");
				}
			}	
		}
		sb.append("</select>");
		
		
		sb.append("</div>");
		sb.append("<script type=\"text/javascript\">");
		
		//�������������Ϊ�ա�˵����������Ҫͨ����̬���˷�ʽ��ȡ
		if(!filter.equals("")){
			  sb.append(" $(function() { rc.dynamic_get_codevalue_by_codetype_and_filter(\"#"+property+"\",\""+codetype+"\",\""+filter+"\",\""+value+"\");})");
		}
	    sb.append("</script>");

		// ��redis��ȡ����
		/*
		 * try{ RedisManager redismanager=
		 * MyApplicationContextUtil.getContext().getBean(RedisManager.class);
		 * List<CodeValue> list=(List<CodeValue>)redismanager.get(codetype);
		 * if(list!=null){ for(CodeValue codevalue:list){ sb.append("<option ");
		 * if(value!=null&&!value.equals("")){
		 * if(value.equals(codevalue.getCode_value())){ sb.append(" selected ");
		 * } } sb.append("  value=\""+codevalue.getCode_value()+"\">"+codevalue.
		 * getCode_name()+"</option>"); } } sb.append("</select>");
		 * }catch(Exception e){ e.printStackTrace(); }
		 */
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