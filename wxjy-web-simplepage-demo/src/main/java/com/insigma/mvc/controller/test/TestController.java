package com.insigma.mvc.controller.test;

import com.insigma.http.HttpRequestUtils;
import com.insigma.mvc.ApiUriContraints;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.Ac11;
import com.insigma.resolver.AppException;
import com.insigma.resolver.TokenException;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 首页contoller
 *
 * @author xxx
 */
@Controller
public class TestController extends MvcHelper {

    //http工具类
    @Resource
    private HttpRequestUtils httpRequestUtils;
    
    /**
     * 测试
     * @throws AppException
     */
    @RequestMapping("/test")
    public String  test() throws AppException,TokenException{
    	JSONObject jsonobject = httpRequestUtils.httpPostReturnObject(ApiUriContraints.API_SXJSY_AC11S, new Ac11());
        System.out.println(jsonobject.toString());
        return jsonobject.toString();
    }
}
