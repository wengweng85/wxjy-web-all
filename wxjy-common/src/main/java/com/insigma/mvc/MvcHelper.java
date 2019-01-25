package com.insigma.mvc;

import java.util.HashMap;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;


/**
 * mvc�����࣬��Ҫ���ڰ�װcontroller�Լ�serviceimp�㷵�ص�����
 * �����װ���෵�س�json��ʽ
 *
 * @author admin
 */
public class MvcHelper<T> {

    /**
     * У�鲢���ض���
     *
     * @param result
     * @return
     */
    public AjaxReturnMsg<String> validate(BindingResult result) {
        FieldError fielderror = result.getFieldErrors().get(result.getErrorCount() - 1);
        AjaxReturnMsg<String> dto = new AjaxReturnMsg<String>();
        dto.setSuccess(false);
        dto.setMessage(fielderror.getDefaultMessage());
        dto.setObj(fielderror.getField());
        return dto;
    }

    /**
     * �ɹ�����
     *
     * @param message
     * @return
     */
    public AjaxReturnMsg<String> success(String message) {
        AjaxReturnMsg<String> dto = new AjaxReturnMsg<String>();
        dto.setSuccess(true);
        dto.setMessage(message);
        return dto;
    }

    /**
     * �ɹ�����
     *
     * @param o
     * @return
     */
    public AjaxReturnMsg<T> success(String message, T o) {
        AjaxReturnMsg<T> dto = new AjaxReturnMsg<T>();
        dto.setSuccess(true);
        dto.setMessage(message);
        dto.setObj(o);
        return dto;
    }

    @SuppressWarnings("rawtypes")
    public AjaxReturnMsg success_obj(String message, Object o) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(true);
        dto.setMessage(message);
        dto.setObj(o);
        return dto;
    }

    /**
     * �ɹ�����
     *
     * @param o
     * @return
     */
    public AjaxReturnMsg<T> success(T o) {
        AjaxReturnMsg<T> dto = new AjaxReturnMsg<T>();
        dto.setSuccess(true);
        dto.setObj(o);
        return dto;
    }

    /**
     * �ɹ����� ���ط�ҳ����Ϣ ���ڽӿڷ���
     *
     * @param pageinfo
     * @return
     */
    public AjaxReturnMsg success(PageInfo<T> pageinfo) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(true);
        dto.setObj(pageinfo);
        dto.setTotal(pageinfo.getTotal());
        return dto;
    }

    /**
     * �ɹ����ط�ҳ����manager����ƽ̨
     *
     * @param pageinfo
     * @return
     */
    public HashMap<String, Object> success_hashmap_response(PageInfo<T> pageinfo) {
        HashMap<String, Object> hashmap = new HashMap<String, Object>();
        hashmap.put("total", pageinfo.getTotal());
        hashmap.put("rows", pageinfo.getList());
        return hashmap;
    }


    /**
     * �ɹ�����
     *
     * @param pageinfo
     * @return
     */
    public HashMap<String, Object> success_hashmap_response(PageInfo<T> pageinfo, Object o) {
        HashMap<String, Object> hashmap = new HashMap<String, Object>();
        hashmap.put("pageinfo", pageinfo);
        hashmap.put("obj", o);
        return hashmap;
    }

    /**
     * ���󷵻�
     *
     * @param message
     * @return
     */
    public AjaxReturnMsg<String> error(String message) {
        AjaxReturnMsg<String> dto = new AjaxReturnMsg<String>();
        dto.setSuccess(false);
        dto.setMessage(message);
        return dto;
    }

    /**
     * ���󷵻�
     *
     * @param message
     * @param obj
     * @return
     */
    public AjaxReturnMsg<T> error(String message, T obj) {
        AjaxReturnMsg<T> dto = new AjaxReturnMsg<T>();
        dto.setSuccess(false);
        dto.setMessage(message);
        dto.setObj(obj);
        return dto;
    }

    /**
     * ���󷵻�
     *
     * @param o
     * @return
     */
    public AjaxReturnMsg<T> error(T o) {
        AjaxReturnMsg<T> dto = new AjaxReturnMsg<T>();
        dto.setSuccess(false);
        dto.setObj(o);
        return dto;
    }

    /**
     * ���󷵻�
     *
     * @param e
     * @return
     */
    public AjaxReturnMsg<String> error(Exception e) {
        AjaxReturnMsg<String> dto = new AjaxReturnMsg<String>();
        dto.setSuccess(false);
        dto.setMessage(e.getLocalizedMessage());
        return dto;
    }

}