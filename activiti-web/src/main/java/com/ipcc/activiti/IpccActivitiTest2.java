package com.ipcc.activiti;

import org.activiti.engine.*;
import org.activiti.engine.task.Task;

public class IpccActivitiTest2 {


    public static void main(String[] args) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = engine.getTaskService();
        RuntimeService  runtimeService = engine.getRuntimeService();
        FormService formService = engine.getFormService();

        System.out.println( runtimeService.getVariable("102505", "type1")); //105005
        //流程变量： 对整个流程有效，流程判断
        runtimeService.setVariable("102505", "type2", 2);

        Task task = taskService.createTaskQuery().processInstanceId("102505").taskCandidateGroup("second_instance_1").singleResult();
        System.out.println(task.getProcessInstanceId());

        //流程变量： 对当前的task有效
        taskService.setVariableLocal(task.getId(), "type1", 0);
        formService.getTaskFormData(task.getId()).getFormProperties();

        System.out.println("当前任务：" + task.getName() + ", type1：" + taskService.getVariableLocal(task.getId(), "type1"));
        taskService.complete(task.getId());


//
//        taskService.complete(task.getId());
//        System.out.println(pi.getId());
//
//        task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
//        taskService.setVariableLocal(task.getId(), "days_no2", 60);
//        System.out.println("当前任务：" + task.getName() + ", days参数：" + taskService.getVariableLocal(task.getId(), "days_no2"));
//        taskService.complete(task.getId());
//        System.out.println(pi.getId());
//
//        // 获取流程变量
//        List<HistoricVariableInstance> list = engine.getHistoryService()
//                .createHistoricVariableInstanceQuery().processInstanceId(pi.getId()).list();//创建一个历史的流程变量查询
//
//        if(list != null && list.size()>0){
//            for(HistoricVariableInstance hiv : list){
//                System.out.println(hiv.getTaskId()+"  "+hiv.getVariableName()+"     "+hiv.getValue()+"      "+hiv.getVariableTypeName());
//            }
//        }
    }
}
