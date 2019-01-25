package com.insigma.freemarker.form;

import freemarker.core.Environment;
import freemarker.template.*;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * freemarker�Զ����ǩ����
 *
 */
public class RepeatDirective implements TemplateDirectiveModel  {

    /**
     * @param environment
     * @param map
     *            <@repeat count=3;
     *            cnt>����count=3���Լ�ֵ�Ե���ʽ����map�У�ֻ��3����java���ͣ�����TemplateModel����
     *            (����:TemplateNumberModel
     *            ,�ַ���:TemplateScalarModel,boolean:TemplateBooleanModel ��)��
     *            ��������map.getValue(......)���ص���һ��TemplateModel���͵����ݣ���ת����int�������ݡ�
     * @param templatemodel
     *            ��������cnt��atemplatemodel��һ��TemplateModel���͵����飬���ǿ����ڳ����и�����ֵ
     * @param templatedirectivebody
     */
    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templatemodel, TemplateDirectiveBody templatedirectivebody) throws TemplateException, IOException {
        Writer out=null;
        try{
            if(templatedirectivebody==null){
                throw new TemplateModelException("null body");
            }else{
                out = environment.getOut();
                TemplateNumberModel numberModel = (TemplateNumberModel) map.get("count");
                int count = numberModel.getAsNumber().intValue();
                for (int i = 0; i < count; i++) {
                    if (templatemodel.length > 0) {
                        templatemodel[0] = new SimpleNumber(i + 1);
                    }
                    templatedirectivebody.render(out);
                }
            }
        }finally {
            if(out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
