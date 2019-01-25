package com.insigma.tag.form.input;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import com.insigma.common.util.EhCacheUtil;
import com.insigma.mvc.model.CodeValue;
import com.insigma.tag.form.constraint.TagConstraint;

/**
 * �Զ����ǩ֮checkbox jenkins����
 * 
 * @author admin
 *
 */
public class CheckboxEditTag implements Tag {

	private PageContext pageContext;

	// property
	private String property;

	private String codetype;
	
	//label
   private String label;
			
	//ռλ����,����label��һ��
   private String cols;

	//�Ƿ����
	private String required;
	
	// ֵ
	private String value;

	

	public String getCodetype() {
		return codetype;
	}

	public void setCodetype(String codetype) {
		this.codetype = codetype;
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
	     value=(value==null)?"":value;
	     required=(required==null)?"":required;
	     cols=(cols==null)? TagConstraint.COLS:cols;
	     
	     String [] col=cols.split(",");
	     String [] vals=value.split(",");
	     int labelcol=Integer.parseInt(col[0]);
	     int inputcol=Integer.parseInt(col[1]);
	     //�Ƿ����
	     boolean isrequired=Boolean.parseBoolean(required);
	     JspWriter out = pageContext.getOut();
	     StringBuffer sb=new StringBuffer();
	     sb.append("<label class=\" col-xs-"+labelcol+"  col-sm-"+labelcol+"   control-label\">"+label);
	     if(isrequired){
	    	 sb.append("<span class=\"require\">*<span>");
	     }
	     sb.append("</label>");
	     sb.append("<div class=\"  col-xs-"+inputcol+"  col-sm-"+inputcol+"  \">");
	    
		 
		 // ��EhCache��ȡ����
		 Object object=EhCacheUtil.getParamFromCache(codetype.toUpperCase());
			if (object != null) {
				List<CodeValue> list = (List<CodeValue>) object;
				for (CodeValue codevalue : list) {
					 String id="inlineCheckbox1_"+UUID.randomUUID();
					 sb.append("<div class=\"checkbox checkbox-info checkbox-inline\">");
					 sb.append("<input name=\""+property+"\" type=\"checkbox\" id=\""+id+"\" value=\""+codevalue.getCode_value()+"\"");
					 
					 for(int j=0;j<vals.length;j++){
						 if (vals[j] != null && !"".equals(vals[j])) {
								if (vals[j].equals(codevalue.getCode_value())) {
									sb.append(" checked=\"checked\" ");
								}
						 }
					 }
					
					sb.append(">");
					sb.append("<label for=\""+id+"\"> "+codevalue.getCode_name() +" </label>");
					sb.append("</div>");
				}
		 }	
	  
	     sb.append("</div>");
		
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
