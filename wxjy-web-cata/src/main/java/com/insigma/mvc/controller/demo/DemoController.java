package com.insigma.mvc.controller.demo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.DemoAc01;


/**
 * demo���Գ���
 * @author admin
 *
 */
@Controller
@RequestMapping("/demo")
public class DemoController extends MvcHelper<DemoAc01> {
	
	/**
	 * ��ת����ѯҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/toquery")
	public ModelAndView index(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("demo/demoQuery");
        return modelAndView;
	}
	

	
	/**
	 * ��ת���༭ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/toedit/{id}")
	public ModelAndView toedit(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		ModelAndView modelAndView=new ModelAndView("demo/demoEdit");
		modelAndView.addObject("id",id);  
        return modelAndView;
	}
	
	
	/**
	 * ��ת���鿴ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/toview/{id}")
	public ModelAndView toview(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		ModelAndView modelAndView=new ModelAndView("demo/demoView");
		modelAndView.addObject("id",id);  
        return modelAndView;
	}
	
	
	
	/**
	 * ��ת���༭(����)ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/toadd")
	public ModelAndView toadd(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("demo/demoAdd");
        return modelAndView;
	}


	/**
	 * ��ת���༭(����)ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/toaddjob")
	public ModelAndView toaddjob(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("train/demoAddJob");
		return modelAndView;
	}

	/**
	 * ��ת���༭(����)ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/toaddjobs")
	public ModelAndView toaddjobs(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("train/demoAddJobs");
		return modelAndView;
	}
	
	/**
	 * ��Աѡ���
	 * @param request
	 * @return
	 */
	@RequestMapping("/toselect")
	public ModelAndView selectindex(HttpServletRequest request,Model model) throws Exception {
		String callback_fun_name=request.getParameter("callback_fun_name");
		ModelAndView modelAndView=new ModelAndView("demo/demoSelect");
        modelAndView.addObject("callback_fun_name", callback_fun_name);
        return modelAndView;
	}
	
	
	
	/**
	 * ��ת��wizard step����ҳ
	 * @param request
	 * @return
	 */
	@RequestMapping("/open_wizard_step")
	public ModelAndView open_wizard_step(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("demo/demoWizardStep");
        return modelAndView;
	}
	
	/**
	 * ��ת��wizard step����ҳ
	 * @param request
	 * @return
	 */
	@RequestMapping("/open_wizard_form_step")
	public ModelAndView open_wizard_form_step(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("demo/demoWizardStep_form");
        return modelAndView;
	}
	
	
	/**
	 * thymeleaf ҳ����� 
	 * @param request
	 * @return
	 */
	@RequestMapping("/thymeleaf")
	public ModelAndView thymeleaf(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("demo/thymeleaf");
		model.addAttribute("name", "���ܻ�");
        return modelAndView;
	}

}
