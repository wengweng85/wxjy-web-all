package com.groupwork.dto;

import com.alibaba.druid.support.json.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;

/**
 * �ӿ�ͨ�÷�����
 *
 * @author admin
 */

public class R {

    private static final String DefaultSuccessMessage="�ɹ�";

    private static final String DefaultFailMessage="ʧ��";

    private static final Logger logger = LoggerFactory.getLogger(R.class);

    private static final long serialVersionUID = 1L;

    private int syscode; // ϵͳ����״̬��,Ĭ��200
    private boolean success; // ҵ��״̬�룬Ĭ��Ϊtrue
    private String message = ""; // ҵ��״̬����˵��
    private int code;
    private Object obj; //����

    public int getSyscode() {
        return syscode;
    }

    public void setSyscode(int syscode) {
        this.syscode = syscode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }


    private R() {
        this.syscode = SysCode.SYS_CODE_200.getCode(); // ϵͳ����״̬��,Ĭ��200
        this.success = true; // ҵ��״̬�룬Ĭ��Ϊtrue
    }

    /**
     * У�鲢���ض���
     *
     * @param result
     * @return
     */
    public static R validate(BindingResult result) {
        FieldError fielderror = result.getFieldErrors().get(result.getErrorCount() - 1);
        R dto = new R();
        dto.setSuccess(false);
        dto.setMessage(fielderror.getDefaultMessage());
        dto.setObj(fielderror.getField());
        logger.debug(JSONUtils.toJSONString(dto));
        return dto;
    }

    /**
     * �ɹ�����
     *
     * @return
     */
    public static R success() {
        return success(DefaultSuccessMessage);
    }

    /**
     * �ɹ�����
     *
     * @param message
     * @return
     */
    public static R success(String message) {
        R dto = new R();
        dto.setSuccess(true);
        dto.setMessage(message);
        logger.debug(JSONUtils.toJSONString(dto));
        return dto;
    }

    /**
     * �ɹ�����
     *
     * @param
     * @return
     */
    public static R success(String message, Object obj) {
        R dto = new R();
        dto.setSuccess(true);
        dto.setMessage(message);
        dto.setObj(obj);
        logger.debug(JSONUtils.toJSONString(dto));
        return dto;
    }

    /**
     * �ɹ�����
     *
     * @param map
     * @return
     */
    public static R success(HashMap map) {
        R dto = new R();
        dto.setObj(map);
        return dto;
    }

    /**
     * �ɹ�����
     *
     * @param o
     * @return
     */
    public static R  success(Object o) {
        R dto = new R();
        dto.setSuccess(true);
        dto.setObj(o);
        logger.debug(JSONUtils.toJSONString(dto));
        return dto;
    }

    /**
     * ʧ�ܷ���
     *
     * @return
     */
    public static R fail() {
       return fail(DefaultFailMessage);
    }
    /**
     * ʧ�ܷ���
     *
     * @param message
     * @return
     */
    public static R fail(String message) {
        R dto = new R();
        dto.setSuccess(false);
        dto.setMessage(message);
        logger.debug(JSONUtils.toJSONString(dto));
        return dto;
    }

    /**
     * ʧ�ܷ���
     *
     * @param message
     * @param obj
     * @return
     */
    public static R fail(String message, Object obj) {
        R dto = new R();
        dto.setSuccess(false);
        dto.setMessage(message);
        dto.setObj(obj);
        logger.debug(JSONUtils.toJSONString(dto));
        return dto;
    }

    /**
     * ʧ�ܷ���
     *
     * @param obj
     * @return
     */
    public static R fail(Object obj) {
        R dto = new R();
        dto.setSuccess(false);
        dto.setObj(obj);
        logger.debug(JSONUtils.toJSONString(dto));
        return dto;
    }

    /**
     * �쳣����
     * @param syscode
     * @param msg
     * @return
     */
    public static R error(SysCode syscode,String msg){
        R dto=new R();
        dto.setSuccess(false);
        dto.setSyscode(syscode.getCode());
        dto.setMessage(msg);
        logger.debug(JSONUtils.toJSONString(dto));
        return dto;
    }

    /**
     * �쳣����
     * @param sysCode
     * @return
     */
    public static R error(SysCode sysCode){
        return error(sysCode,sysCode.getName());
    }

    /**
     * �쳣����
     * @return
     */
    public static R error500(){
        return error(SysCode.SYS_API_EXCEPTION);
    }

    /**
     * �쳣����
     * @return
     */
    public static R error500(String message){
        return error(SysCode.SYS_API_EXCEPTION,message);
    }

    /**
     * �쳣����
     * @return
     */
    public static R error401(){
        return error(SysCode.SYS_APPKEY_EMPTY);
    }

    /**
     * �쳣����
     * @return
     */
    public static R error402(){
        return error(SysCode.SYS_APPKEY_ERROR);
    }
    /**
     * �쳣����
     * @return
     */
    public static R error403(){
        return error(SysCode.SYS_TOKEN_EMPTY);
    }

    /**
     * �쳣����
     * @return
     */
    public static R error404(){
        return error(SysCode.SYS_TOKEN_ERROR);
    }

    /**
     * �쳣����
     * @return
     */
    public static R error408(){
        return error(SysCode.SYS_APPKEY_NO_PERM);
    }
    /**
     * �쳣����
     *
     * @param e
     * @return
     */
    public static R error(Exception e) {
        return error(SysCode.SYS_API_EXCEPTION,e.getLocalizedMessage());
    }



}
