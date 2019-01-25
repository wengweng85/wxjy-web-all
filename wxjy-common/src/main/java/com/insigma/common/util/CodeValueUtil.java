package com.insigma.common.util;

import com.insigma.mvc.model.CodeValue;

import java.util.HashMap;
import java.util.List;

/**
 * ����������
 */
public class CodeValueUtil {

    /**
     * ���ݲ������ͺͲ������ƻ�ȡ��������
     *
     * @param code_name
     * @param code_type
     * @return
     */
    public static String getCodeValueByName(String code_name, String code_type) {
        Object object=EhCacheUtil.getParamFromCache(code_type);
        if (object != null) {
            List<CodeValue> list = (List<CodeValue>) object;
            for (CodeValue codevalue : list) {
                if (codevalue.getCode_name().equals(code_name)) {
                    return codevalue.getCode_value();
                }
            }
        }
        return null;
    }

    public static List<CodeValue> getCodeListByCodeType(String code_type) {
        Object object=EhCacheUtil.getParamFromCache(code_type);
        if (object != null) {
            List<CodeValue> list = (List<CodeValue>) object;
            return list;
        }
        return null;
    }

    public static String getAaa005Byaaa001(String aaa001){
        Object object=EhCacheUtil.getParamFromCache("aa01");
        HashMap<String,String> hm = (HashMap)object;
        return hm.get(aaa001);
    }
}
