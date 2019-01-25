package com.insigma.mvc.controller.index;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.insigma.mvc.MvcHelper;
import com.insigma.shiro.realm.SUserUtil;

/**
 * 首页contoller
 *
 * @author xxx
 */
@Controller
public class IndexController extends MvcHelper {


    /**
     * 管理功能主页面
     * @param request
     * @return
     */
    @RequestMapping("/")
    public ModelAndView gotoAdminIndex(HttpServletRequest request, Model model) throws Exception {
        ModelAndView modelAndView=new ModelAndView("index/admin_index");
        modelAndView.addObject("SYS_TITLE", "公共服务平台");
        modelAndView.addObject("suser", SUserUtil.getCurrentUser());
        modelAndView.addObject("permlist",request.getSession().getAttribute(SUserUtil.SHIRO_CURRENT_PERM_LIST_INFO));
        return modelAndView;
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
