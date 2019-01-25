package com.insigma.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskForMultiCompute {

    public static void main(String[] args) {

        FutureTaskForMultiCompute inst=new FutureTaskForMultiCompute();
        // �������񼯺�
        List<FutureTask<Integer>> taskList = new ArrayList<FutureTask<Integer>>();
        // �����̳߳�
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            // ����Callable���󴴽�FutureTask����
            FutureTask<Integer> ft = new FutureTask<Integer>(inst.new ComputeTask(i, ""+i));
            taskList.add(ft);
            // �ύ���̳߳�ִ������Ҳ����ͨ��exec.invokeAll(taskList)һ�����ύ��������;
            exec.submit(ft);
        }

        System.out.println("���м��������ύ���, ���߳̽��Ÿ��������飡");

        // ��ʼͳ�Ƹ������̼߳�����
        Integer totalResult = 0;
        for (FutureTask<Integer> ft : taskList) {
            try {
                //FutureTask��get�������Զ�����,ֱ����ȡ������Ϊֹ
                totalResult = totalResult + ft.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        // �ر��̳߳�
        exec.shutdown();
        System.out.println("������������ܽ����:" + totalResult);

    }

    private class ComputeTask implements Callable<Integer> {

        private Integer result = 0;
        private String taskName = "";

        public ComputeTask(Integer iniResult, String taskName){
            result = iniResult;
            this.taskName = taskName;
            System.out.println("�������̼߳�������: "+taskName);
        }

        public String getTaskName(){
            return this.taskName;
        }

        @Override
        public Integer call() throws Exception {
            // TODO Auto-generated method stub

            for (int i = 0; i < 100; i++) {
                result =+ i;
            }
            // ����5���ӣ��۲����߳���Ϊ��Ԥ�ڵĽ�������̻߳����ִ�У���Ҫȡ��FutureTask�Ľ���ǵȴ�ֱ����ɡ�
            Thread.sleep(5000);
            System.out.println("���̼߳�������: "+taskName+" ִ�����!");
            return result;
        }
    }
}