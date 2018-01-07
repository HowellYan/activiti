import org.activiti.engine.*;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class IpccActivitiTest2 {
    public static void main(String[] args) throws Exception {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        User user = engine.getIdentityService().newUser("yang");
        user.setFirstName("yang");
        user.setLastName("yanghaihua");
        engine.getIdentityService().saveUser(user);

        Group group = engine.getIdentityService().newGroup("my");
        group.setId("ipcc");
        group.setName("ipcc");
        engine.getIdentityService().saveGroup(group);


        engine.getIdentityService().createMembership("yang","ipcc");

        engine.close();
        System.exit(0);
    }
}
