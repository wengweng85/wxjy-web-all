package com.insigma.tag.form.head;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import com.insigma.common.listener.AppConfig;


/**
 * 自定义标签之css头加载
 *
 * @author admin
 */
public class CommonCssHeaderTag implements Tag {

    private PageContext pageContext;

    @Override
    public int doEndTag() throws JspException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int doStartTag() throws JspException {
        // TODO Auto-generated method stub
        JspWriter out = pageContext.getOut();
        HttpServletRequest request=((HttpServletRequest) this.pageContext.getRequest());
        String contextpath = request.getContextPath();
        String staticPath=contextpath;
        //静态地址nginx
        String website_static_resource_nginx_url = AppConfig.getProperties("website_static_resource_nginx_url");
        if (null != website_static_resource_nginx_url&& !website_static_resource_nginx_url.equals("")) {
            staticPath = website_static_resource_nginx_url;
        }
        StringBuffer sb = new StringBuffer();
        String gateway_base_url = AppConfig.getProperties("gateway_base_url");
        if (null == gateway_base_url || gateway_base_url.equals("")) {
            gateway_base_url = contextpath;
        }

        //<!--css-->
        sb.append("<link href='" + staticPath + "/webjars/css/bootstrap.min.css' rel='stylesheet'>");
        sb.append("<link href='" + staticPath + "/webjars/css/font-awesome.min.css' rel='stylesheet'>");
        sb.append("<link href='" + staticPath + "/webjars/css/animate.min.css' rel='stylesheet'>");
        sb.append("<link href='" + staticPath + "/webjars/css/style.min.css' rel='stylesheet'>");
        //<!-- bootstrap-table -->
        sb.append("<link href='" + staticPath + "/webjars/css/plugins/bootstrap-table/bootstrap-table.min.css' rel='stylesheet'>");
        //<!-- ztree -->
        sb.append("<link href='" + staticPath + "/webjars/js/jQuery/ztree/css/metroStyle/metroStyle.css'  rel='stylesheet' >");
        //<!-- select -->
        sb.append("<link href='" + staticPath + "/webjars/js/plugins/bootstrap-select/css/bootstrap-select.css' rel='stylesheet'>");

        //<!--select2-->
        //sb.append("<link href='"+staticPath+"/webjars/js/plugins/select2/css/select2.css' rel='stylesheet'>");
        //<!-- datapicker -->
        sb.append("<link href='" + staticPath + "/webjars/js/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css' rel='stylesheet'>");

        sb.append("<link href='" + staticPath + "/webjars/css/plugins/jasny/jasny-bootstrap.min.css' rel='stylesheet'>");

        sb.append("<link href='" + staticPath + "/webjars/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css' rel='stylesheet'>");

        sb.append("<link href='" + staticPath + "/webjars/js/plugins/bootstrap3-editable/css/bootstrap-editable.css' rel='stylesheet'>");

        sb.append("<link href='" + staticPath + "/webjars/css/plugins/steps/jquery.steps.css' rel='stylesheet'>");

        //bootstrap-switch
        sb.append("<link href='" + staticPath + "/webjars/css/plugins/bootstrap-switch-master/dist/css/bootstrap3/bootstrap-switch.min.css' rel='stylesheet'>");

        //drag
        sb.append("<link href='" + staticPath + "/webjars/js/plugins/bootstrap-table/extensions/reorder-rows/bootstrap-table-reorder-rows.css' rel='stylesheet'>");

        //<!-- rc.css -->
        sb.append("<link href='" + staticPath + "/webjars/css/rc.css' rel='stylesheet'>");

        sb.append("<link href='" + staticPath + "/webjars/js/selector/zyzn_1.css' rel='stylesheet'>");

        //以下js要在页面加载前加载
        sb.append("<script type=\"text/javascript\">");
        sb.append(" var contextPath='" + contextpath + "';");
        sb.append(" var gateway_base_url='" + gateway_base_url + "';");
        sb.append("</script>");

        sb.append("<script src='" + staticPath + "/webjars/js/jQuery/all/jquery.js' charset ='utf-8'></script>");
        sb.append("<script src='" + staticPath + "/webjars/js/plugins/suggest/bootstrap-suggest.js'></script>");

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
