package com.insigma.mvc.controller.log;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.SErrorLog;


/**
 * api  日志查询 controller
 * @author admin
 *
 */
@Controller
@RequestMapping("/log")
public class WebLogQueryController extends MvcHelper {
	
	Log log=LogFactory.getLog(WebLogQueryController.class);
	
	/**
	 * 跳转至错误日志查询页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/errorlogindex")
	public ModelAndView errorlogindex(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("api/API_LOG_001_001/apiErrorLogList");
        return modelAndView;
	}
	
	/**
	 * 通过日志编号获取异常日志明细信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/getErrorLogById/{logid}")
	public ModelAndView getErrorLogById(HttpServletRequest request,Model model,@PathVariable String logid) throws Exception {
		ModelAndView modelAndView=new ModelAndView("api/API_LOG_001_001/apiErrorlogview");
		modelAndView.addObject("logid",logid);  
        return modelAndView;
	}


}
