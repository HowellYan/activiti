import org.activiti.engine.*;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

public class IpccActivitiTest5 {

    public static void main(String[] args) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        // 存储服务
        RepositoryService rs = engine.getRepositoryService();
        // 运行时服务
        RuntimeService runService = engine.getRuntimeService();
        // 任务服务
        TaskService taskService = engine.getTaskService();

        // 部署流程定义
        //Deployment dep = rs.createDeployment().addClasspathResource("var_local.bpmn").deploy();

        //开始流程实例
        ProcessDefinition pd = rs.createProcessDefinitionQuery().deploymentId("7501").singleResult();

        ProcessInstance pi = runService.startProcessInstanceById(pd.getId());

        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        taskService.setVariableLocal(task.getId(), "days_no1", 30);
        System.out.println("当前任务：" + task.getName() + ", days参数：" + taskService.getVariableLocal(task.getId(), "days_no1"));

        taskService.complete(task.getId());
        System.out.println(pi.getId());

        task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        taskService.setVariableLocal(task.getId(), "days_no2", 60);
        System.out.println("当前任务：" + task.getName() + ", days参数：" + taskService.getVariableLocal(task.getId(), "days_no2"));
        taskService.complete(task.getId());
        System.out.println(pi.getId());

        // 获取流程变量
        List<HistoricVariableInstance> list = engine.getHistoryService()
                .createHistoricVariableInstanceQuery().processInstanceId(pi.getId()).list();//创建一个历史的流程变量查询

        if(list != null && list.size()>0){
            for(HistoricVariableInstance hiv : list){
                System.out.println(hiv.getTaskId()+"  "+hiv.getVariableName()+"     "+hiv.getValue()+"      "+hiv.getVariableTypeName());
            }
        }
    }
}
