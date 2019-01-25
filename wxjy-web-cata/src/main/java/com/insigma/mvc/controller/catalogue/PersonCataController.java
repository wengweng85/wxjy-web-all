package com.insigma.mvc.controller.catalogue;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.insigma.shiro.realm.SUserUtil;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.http.HttpRequestUtils;
import com.insigma.mvc.ApiUriContraints;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.SUser;
import com.insigma.mvc.model.catalogue.ServiceCollection;

@Controller
@RequestMapping("/person/catalogue")
public class PersonCataController extends MvcHelper {

    @Resource
    private HttpRequestUtils httpRequestUtils;

    /**
     * �ղ�����
     * @param session
     * @param collection
     * @return
     */
    @ResponseBody
    @RequestMapping("/toggleCollect")
    public AjaxReturnMsg toggleCollect(HttpSession session, ServiceCollection collection) throws Exception {
        SUser suser = (SUser) SUserUtil.getCurrentUser();
        if (suser == null) {
            return this.error("δ��¼");
        }
        if (!"1".equals(suser.getUsertype())) {
            return this.error("ֻ�и����û����ܲ���");
        }
        JSONObject object = httpRequestUtils.httpPost(ApiUriContraints.API_CATA_PERSON_COLLECT, collection);
        return (AjaxReturnMsg) JSONObject.toBean(object, AjaxReturnMsg.class);
    }
   
}
