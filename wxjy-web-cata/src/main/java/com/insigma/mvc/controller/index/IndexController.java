package com.insigma.mvc.controller.index;

import com.insigma.http.HttpRequestUtils;
import com.insigma.mvc.ApiUriContraints;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.SUser;
import com.insigma.shiro.realm.SUserUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 首页contoller
 *
 * @author xxx
 */
@Controller
public class IndexController extends MvcHelper {

    //http工具类
    @Resource
    private HttpRequestUtils httpRequestUtils;

    /**
     * 主页
     *
     * @return
     */
    @RequestMapping("/")
    public String gotoIndex(HttpSession session, ModelMap modelMap) throws Exception {
        SUser suser = SUserUtil.getCurrentUser();
        modelMap.put("suser", suser);
        JSONObject jsonObject = httpRequestUtils.httpPost(ApiUriContraints.API_AUTH_INDEX).getJSONObject("obj");
        modelMap.put("busTypeList", jsonObject.getJSONArray("busTypeList"));
        modelMap.put("hotCataList", jsonObject.getJSONArray("hotCataList"));
        return "index";
    }


    /**
     * http 404 错误
     *
     * @return
     */
    @RequestMapping("/404")
    public String error404() {
        return "error/404";
    }

    /**
     * http 500 错误
     *
     * @return
     */
    @RequestMapping("/500")
    public String error500() {
        return "error/500";
    }

    /**
     * 未授权
     *
     * @return
     */
    @RequestMapping("/unrecognized")
    public String unrecognized() {
        return "error/unrecognized";
    }
}
