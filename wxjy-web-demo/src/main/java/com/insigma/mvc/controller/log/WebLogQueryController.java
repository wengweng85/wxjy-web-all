package com.insigma.mvc.controller.log;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.insigma.http.HttpRequestUtils;
import com.insigma.mvc.ApiUriContraints;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.SErrorLog;


/**
 * api  ��־��ѯ controller
 * @author admin
 *
 */
@Controller
@RequestMapping("/log")
public class WebLogQueryController extends MvcHelper {
	
	Log log=LogFactory.getLog(WebLogQueryController.class);
	
	 //http������
    @Resource
    private HttpRequestUtils httpRequestUtils;
	
	/**
	 * ��ת��������־��ѯҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/errorlogindex")
	public ModelAndView errorlogindex(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("api/API_LOG_001_001/apiErrorLogList");
        return modelAndView;
	}
	
	/**
	 * ��ȡ��־�б�
	 * @param request
	 * @return
	 */
	@RequestMapping("/getErrogLogList")
	@ResponseBody
	public HashMap<String,Object> getLogList(HttpServletRequest request,Model model,  SErrorLog serroglog ) throws Exception {
		PageInfo<SErrorLog> pageinfo = httpRequestUtils.httpPostReturnPage(ApiUriContraints.API_BASE_ERRORLOGS, serroglog);
		return this.success_hashmap_response(pageinfo);
	}
	
	/**
	 * ͨ����־��Ż�ȡ�쳣��־��ϸ��Ϣ
	 * @param request
	 * @return
	 */
	@RequestMapping("/getErrorLogById/{logid}")
	public ModelAndView getErrorLogById(HttpServletRequest request,Model model,@PathVariable String logid) throws Exception {
		ModelAndView modelAndView=new ModelAndView("api/API_LOG_001_001/apiErrorlogview");
		HashMap map=new HashMap();
		map.put("logid", logid);
		SErrorLog serrorlog=(SErrorLog) httpRequestUtils.httpPostObject(ApiUriContraints.API_BASE_ERRORLOG, map,SErrorLog.class);
		modelAndView.addObject("slog",serrorlog); 
        return modelAndView;
	}
}
