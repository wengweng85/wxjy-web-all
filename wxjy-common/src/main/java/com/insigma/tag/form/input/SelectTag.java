package com.insigma.tag.form.input;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import com.insigma.common.util.EhCacheUtil;
import com.insigma.mvc.model.CodeValue;
import com.insigma.tag.form.constraint.TagConstraint;

/**
 * �Զ����ǩ֮��ѡ����ǩ
 *
 * @author admin
 */
public class SelectTag implements Tag {

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

    //�Ƿ�̬��ȡ
    private String dynamic;

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
    
    //placeholder
  	private String placeholder;
  	
  	//�Ƿ����ؿ�ѡ��
  	private String hiddennull;


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


    public String getDynamic() {
        return dynamic;
    }

    public void setDynamic(String dynamic) {
        this.dynamic = dynamic;
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
    
	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getHiddennull() {
		return hiddennull;
	}

	public void setHiddennull(String hiddennull) {
		this.hiddennull = hiddennull;
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
        multiple = (multiple == null) ? "false" : multiple;
        dynamic = (dynamic == null) ? "false" : dynamic;
        required = (required == null) ? "" : required;
        readonly = (readonly == null) ? "" : readonly;
        codetype = (codetype == null) ? "" : codetype;
        filter = (filter == null) ? "" : filter;
        cols = (cols == null) ? TagConstraint.COLS : cols;
        size = (size == null) ? "8" : size;
        hiddennull = (hiddennull == null) ? "false" : hiddennull;

        String[] col = cols.split(",");
        int labelcol = Integer.parseInt(col[0]);
        int inputcol = Integer.parseInt(col[1]);
        //�Ƿ�ֻ��
        boolean isreadonly = Boolean.parseBoolean(readonly);
        //�Ƿ����
        boolean isrequired = Boolean.parseBoolean(required);
        boolean isdynamic = Boolean.parseBoolean(dynamic);
        String placeholders = placeholder;
		if(placeholder == null){
			placeholders = "" + label;
		}
        JspWriter out = pageContext.getOut();
        StringBuffer sb = new StringBuffer();
        sb.append("<label class=\" col-xs-" + labelcol + "  col-sm-" + labelcol + "   control-label\">" + label);
        if (isrequired) {
            sb.append("<span class=\"require\">*</span>");
        }
        sb.append("</label>");
        sb.append("<div class=\" col-xs-" + inputcol + "  col-sm-" + inputcol + "  \">");
        sb.append("<select class=\"form-control selectpicker \" id=\"" + property + "\" name=\"" + property + "\"  title=\"" + placeholders + "\" value=\"" + value + "\"  selectOnTab=\"true\" data-actions-box=\"true\" data-size=\"" + size + "\"  data-live-search=\"true\" validate=\"" + validate + "\"   data-selected-text-format=\"count > 2\"");

        if (isreadonly) {
            sb.append(" disabled ");
        }

        //onclick�¼�
        if (onclick != null) {
            sb.append(" onclick=\"" + onclick + "\" ");
        }
        //onblur
        if (onblur != null) {
            sb.append(" onblur=\"" + onblur + "\" ");
        }
        //onkeypress
        if (onkeypress != null) {
            sb.append(" onkeypress=\"" + onkeypress + "\" ");
        }
        //onkeydown
        if (onkeydown != null) {
            sb.append(" onkeydown=\"" + onkeydown + "\" ");
        }
        //onkeyup
        if (onkeyup != null) {
            sb.append(" onkeyup=\"" + onkeyup + "\" ");
        }
        //onchange
        if (onchange != null) {
            sb.append(" onchange=\"" + onchange + "\" ");
        }
        if (Boolean.parseBoolean(multiple)) {
            sb.append(" multiple ");
        }
        sb.append(">");

        //���codetype��Ϊ���ҹ�������Ϊ��
        if (!"".equals(codetype) && "".equals(filter)) {
            // ��EhCache��ȡ����
            Object object = EhCacheUtil.getParamFromCache(codetype.toUpperCase());
            if (object != null) {
                List<CodeValue> list = (List<CodeValue>) object;
                if (!Boolean.parseBoolean(multiple) && !Boolean.parseBoolean(hiddennull)) {
                    sb.append("<option value=\"\">" + placeholders + "</option> ");
                }
                for (CodeValue codevalue : list) {
                    sb.append("<option ");
                    if (value != null && !"".equals(value)) {
                        if (value.equals(codevalue.getCode_value())) {
                            sb.append(" selected ");
                        }
                    }
                    sb.append("  value=\"" + codevalue.getCode_value() + "\">" + codevalue.getCode_name() + "</option>");
                }
            }
        }
        sb.append("</select>");


        sb.append("</div>");
        sb.append("<script type=\"text/javascript\">");

        //�������������Ϊ�ա�˵����������Ҫͨ����̬���˷�ʽ��ȡ
        if (!"".equals(filter)) {
            sb.append(" $(function() { rc_tag.dynamic_get_codevalue_by_codetype_and_filter(\"#" + property + "\",\"" + codetype + "\",\"" + filter + "\",\"" + value + "\");})");
        } else {
            if (isdynamic) {
                sb.append(" $(function() { rc_tag.dynamic_get_codevalue_by_codetype(\"#" + property + "\",\"" + codetype + "\",\"" + value + "\");})");
            }
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
