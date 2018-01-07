import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;

public class IpccActivitiTest3 {
    public static void main(String[] args) throws Exception {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        engine.getTaskService().createTaskQuery().taskCandidateOrAssigned("").list();

        engine.close();
        System.exit(0);
    }
}
