package com.insigma.mvc.controller.index;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.insigma.mvc.MvcHelper;

/**
 * ��ҳcontoller
 *
 * @author xxx
 */
@Controller
public class IndexController extends MvcHelper {


    /**
     * ��������ҳ��
     * @param request
     * @return
     */
    @RequestMapping("/")
    public ModelAndView gotoAdminIndex(HttpServletRequest request, Model model) throws Exception {
        ModelAndView modelAndView=new ModelAndView("index/admin_index");
        modelAndView.addObject("SYS_TITLE", "��������ƽ̨");
        return modelAndView;
    }


    /**
     * http 404 ����
     *
     * @return
     */
    @RequestMapping("/404")
    public String error404() {
        return "error/404";
    }

    /**
     * http 500 ����
     *
     * @return
     */
    @RequestMapping("/500")
    public String error500() {
        return "error/500";
    }

    /**
     * δ��Ȩ
     *
     * @return
     */
    @RequestMapping("/unrecognized")
    public String unrecognized() {
        return "error/unrecognized";
    }
    
}
