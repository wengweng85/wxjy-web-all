package com.groupwork.dto;

import com.alibaba.druid.support.json.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;

/**
 * 接口通用返回类
 *
 * @author admin
 */

public class R {

    private static final String DefaultSuccessMessage="成功";

    private static final String DefaultFailMessage="失败";

    private static final Logger logger = LoggerFactory.getLogger(R.class);

    private static final long serialVersionUID = 1L;

    private int syscode; // 系统返回状态码,默认200
    private boolean success; // 业务状态码，默认为true
    private String message = ""; // 业务状态中文说明
    private int code;
    private Object obj; //对象

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
        this.syscode = SysCode.SYS_CODE_200.getCode(); // 系统返回状态码,默认200
        this.success = true; // 业务状态码，默认为true
    }

    /**
     * 校验并返回对象
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
     * 成功返回
     *
     * @return
     */
    public static R success() {
        return success(DefaultSuccessMessage);
    }

    /**
     * 成功返回
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
     * 成功返回
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
     * 成功返回
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
     * 成功返回
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
     * 失败返回
     *
     * @return
     */
    public static R fail() {
       return fail(DefaultFailMessage);
    }
    /**
     * 失败返回
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
     * 失败返回
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
     * 失败返回
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
     * 异常返回
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
     * 异常返回
     * @param sysCode
     * @return
     */
    public static R error(SysCode sysCode){
        return error(sysCode,sysCode.getName());
    }

    /**
     * 异常返回
     * @return
     */
    public static R error500(){
        return error(SysCode.SYS_API_EXCEPTION);
    }

    /**
     * 异常返回
     * @return
     */
    public static R error500(String message){
        return error(SysCode.SYS_API_EXCEPTION,message);
    }

    /**
     * 异常返回
     * @return
     */
    public static R error401(){
        return error(SysCode.SYS_APPKEY_EMPTY);
    }

    /**
     * 异常返回
     * @return
     */
    public static R error402(){
        return error(SysCode.SYS_APPKEY_ERROR);
    }
    /**
     * 异常返回
     * @return
     */
    public static R error403(){
        return error(SysCode.SYS_TOKEN_EMPTY);
    }

    /**
     * 异常返回
     * @return
     */
    public static R error404(){
        return error(SysCode.SYS_TOKEN_ERROR);
    }

    /**
     * 异常返回
     * @return
     */
    public static R error408(){
        return error(SysCode.SYS_APPKEY_NO_PERM);
    }
    /**
     * 异常返回
     *
     * @param e
     * @return
     */
    public static R error(Exception e) {
        return error(SysCode.SYS_API_EXCEPTION,e.getLocalizedMessage());
    }



}
