package c10;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class VarLocalTest {

    public static void main(String[] args) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        // 存储服务
        RepositoryService rs = engine.getRepositoryService();
        // 运行时服务
        RuntimeService runService = engine.getRuntimeService();
        // 任务服务
        TaskService taskService = engine.getTaskService();
        
        //Deployment dep = rs.createDeployment().addClasspathResource("var_local.bpmn").deploy();
        ProcessDefinition pd = rs.createProcessDefinitionQuery().deploymentId("7501").singleResult();
        
        ProcessInstance pi = runService.startProcessInstanceById(pd.getId());
        
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        taskService.setVariableLocal(task.getId(), "days", 3);
        System.out.println("当前任务：" + task.getName() + ", days参数：" + taskService.getVariableLocal(task.getId(), "days"));
        
        taskService.complete(task.getId());
        
        task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        taskService.setVariableLocal(task.getId(), "days", 6);
        System.out.println("当前任务：" + task.getName() + ", days参数：" + taskService.getVariableLocal(task.getId(), "days"));




    }

}
