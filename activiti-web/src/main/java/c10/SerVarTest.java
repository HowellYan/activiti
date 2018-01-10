package c10;

import java.util.UUID;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

public class SerVarTest {

    public static void main(String[] args) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        TaskService ts = engine.getTaskService();
        
        Task task = ts.newTask(UUID.randomUUID().toString());
        task.setName("测试任务");
        ts.saveTask(task);
        
        Person p = new Person();
        p.setId(1);
        p.setName("angus");
        ts.setVariable(task.getId(), "person1", p);
        
        Person pr = ts.getVariable(task.getId(), "person1", Person.class);
        System.out.println(pr.getId() + "---" + pr.getName());
    }

}
