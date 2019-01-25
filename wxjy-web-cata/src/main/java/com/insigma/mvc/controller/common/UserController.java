package com.insigma.mvc.controller.common;

import com.insigma.mvc.MvcHelper;
import com.insigma.shiro.realm.SUserUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 用户管理
 */
@Controller
public class UserController extends MvcHelper {

    Log log = LogFactory.getLog(UserController.class);


    /**
     * 退出登录
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/logout")
    public String outlogin(HttpSession session) throws Exception {
        session.setAttribute(SUserUtil.SHIRO_CURRENT_USER_INFO, null);
        SUserUtil.setCurrentUser(null);
        Subject user = SecurityUtils.getSubject();
        user.logout();
        return "redirect:/";
    }
}
