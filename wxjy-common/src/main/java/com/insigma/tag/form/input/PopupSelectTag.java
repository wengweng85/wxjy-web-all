package com.insigma.tag.form.input;

import com.insigma.tag.form.constraint.TagConstraint;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import java.io.IOException;

/**
 * �Զ����ǩ֮����ѡ���
 *
 * @author liuds
 *
 */
public class PopupSelectTag implements Tag {
    private PageContext pageContext;

    //��Ϣ������
    private String type;

    //�Ƿ��ѡ
    private String multiple;
    //���ѡ����
    private String size;
    //�Ƿ�ֻ��ѡ����С�㼶
    private String isminlevel;


    // property
    private String property;

    //label
    private String label;

    //ռλ����,����label��һ��
    private String cols;

    //�Ƿ����
    private String required;

    // ֵ
    private String value;
    private String name_value;

    // У�����
    private String validate;

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

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMultiple() {
        return multiple;
    }

    public void setMultiple(String multiple) {
        this.multiple = multiple;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getIsminlevel() {
        return isminlevel;
    }

    public void setIsminlevel(String isminlevel) {
        this.isminlevel = isminlevel;
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

    public void setValue(String value) {
        this.value = value;
    }

    public String getName_value() {
        return name_value;
    }

    public void setName_value(String name_value) {
        this.name_value = name_value;
    }

    @Override
    public void setPageContext(PageContext arg0) {
        this.pageContext = arg0;
    }

    @Override
    public void setParent(Tag tag) {

    }

    @Override
    public Tag getParent() {
        return null;
    }

    @Override
    public int doStartTag() throws JspException {
        // ��ֵ���
        validate = (validate == null) ? "" : validate;
        value = (value == null) ? "" : value;
        name_value = (name_value == null) ? "" : name_value;
        isminlevel=(isminlevel==null)?"false":isminlevel;
        required=(required==null)?"fasle":required;
        cols=(cols==null)? TagConstraint.COLS:cols;
        multiple=(multiple==null)?"false":multiple;
        size=(size==null)?"3":size;
        if(!Boolean.parseBoolean(multiple)){
            size = "1";
        }

        String [] col=cols.split(",");
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
        sb.append("<div class=\" col-xs-"+inputcol+"  col-sm-"+inputcol+"  \">");

        sb.append("<div class=\"input-group\">");
        sb.append("<input type=\"hidden\" id=\""+property+"\" name=\""+property+"\"  value=\""+value+"\" >");
        sb.append("<input type=\"text\" id=\""+property+"_name\" name=\""+property+"_name\"  placeholder=\"�����Ҳ�Ŵ�ѡ��\"  value=\""+name_value+"\"  validate=\"" + validate + "\" readonly=\"readonly\" class=\"form-control\"> ");
        sb.append("<span class=\"input-group-btn\"> ");
        sb.append("<a type=\"button\" ");
        if(type.equals("area")){
            sb.append(" onclick=\"openArea('"+property+"','"+property+"_name',"+size+","+isminlevel+")\"");
        }else{
            throw new JspException("�����ڸ�type����");
        }
        sb.append(" class=\"btn btn-primary\"><i class=\"fa fa-search\"></i></a>");
        sb.append("</span>");
        sb.append("</div> </div>");
        try {
            out.write(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    @Override
    public int doEndTag() throws JspException {
        return 0;
    }

    @Override
    public void release() {

    }
}
