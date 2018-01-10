package c10;

import java.util.UUID;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

public class VarTest {

    public static void main(String[] args) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        TaskService ts = engine.getTaskService();
        
        Task task = ts.newTask(UUID.randomUUID().toString());
        task.setName("测试任务");
        ts.saveTask(task);
        
        ts.setVariable(task.getId(), "var1", "hello");
        
    }

}
