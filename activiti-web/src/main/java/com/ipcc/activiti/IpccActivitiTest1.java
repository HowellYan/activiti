package com.ipcc.activiti;

import org.activiti.engine.*;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IpccActivitiTest1 {


    public static void main(String[] args) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        // 存储服务
        RepositoryService rs = engine.getRepositoryService();
        // 运行时服务
        RuntimeService runService = engine.getRuntimeService();
        // 任务服务
        TaskService taskService = engine.getTaskService();

        FormService formService = engine.getFormService();
        // 部署流程定义
        Deployment dep = rs.createDeployment().addClasspathResource("ipcc_audit_1.bpmn20.xml").deploy();

        System.out.println(dep.getId());

        //开始流程实例
        ProcessDefinition pd = rs.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();

        Map<String, Object> processInstVar = new HashMap<String, Object>();
        processInstVar.put("type1",1);
        ProcessInstance pi = runService.startProcessInstanceById(pd.getId(), processInstVar); // 实例id
        System.out.println(pi.getId());

        //105005

        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        List<FormProperty> list = formService.getTaskFormData(task.getId()).getFormProperties();

        for (FormProperty formProperty : list) {
            System.out.println(formProperty.getName());
        }
        // https://www.jianshu.com/p/68c0034f8e56?hmsr=toutiao.io
        taskService.setVariableLocal(task.getId(), "type1", 0);
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
