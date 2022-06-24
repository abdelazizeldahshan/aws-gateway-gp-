package eg.gov.iti.jets.service.aop;

import eg.gov.iti.jets.persistence.dao.InstanceDao;
import eg.gov.iti.jets.persistence.dao.InstanceLogsDao;
import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.persistence.entity.aws.InstanceLogs;
import eg.gov.iti.jets.persistence.entity.enums.UserAction;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class InstanceLogsAspect {

    private final InstanceLogsDao instanceLogsDao;
    private final UserDao userDao;
    private final InstanceDao instanceDao;

    public InstanceLogsAspect(InstanceLogsDao instanceLogsDao, UserDao userDao, InstanceDao instanceDao) {
        this.instanceLogsDao = instanceLogsDao;
        this.userDao = userDao;
        this.instanceDao = instanceDao;
    }

    @AfterReturning(
            pointcut = "execution(* eg.gov.iti.jets.service.management.InstanceManagement.createInstance(eg.gov.iti.jets.persistence.entity.aws.Instance))",
            returning = "returnedInstance")
    public void afterCreateInstance(JoinPoint joinPoint, Object returnedInstance) throws Throwable {
        User user = getCurrUser();
        Instance instance = (Instance) returnedInstance;
        InstanceLogs instanceLogs = getCurrentLog(instance, user, UserAction.CREATE_INSTANCE);
        instanceLogsDao.save(instanceLogs);
    }


    @AfterReturning("execution(* eg.gov.iti.jets.service.management.InstanceManagement.startInstance(java.lang.String))")
    public void afterStartInstance(JoinPoint joinPoint) throws Throwable {
        User user = getCurrUser();
        Instance example = new Instance();
        example.setInstanceId((String) joinPoint.getArgs()[0]);
        Instance instance = instanceDao.findAllByExample(example).get(0);
        InstanceLogs instanceLogs = getCurrentLog(instance, user, UserAction.START_INSTANCE);
        instanceLogsDao.save(instanceLogs);
    }


    @AfterReturning("execution(* eg.gov.iti.jets.service.management.InstanceManagement.stopInstance(java.lang.String))")
    public void afterStopInstance(JoinPoint joinPoint) throws Throwable {
        User user = getCurrUser();
        Instance example = new Instance();
        example.setInstanceId((String) joinPoint.getArgs()[0]);
        Instance instance = instanceDao.findAllByExample(example).get(0);
        InstanceLogs instanceLogs = getCurrentLog(instance, user, UserAction.STOP_INSTANCE);
        instanceLogsDao.save(instanceLogs);
    }


    @AfterReturning("execution(* eg.gov.iti.jets.service.management.InstanceManagement.deleteInstance(java.lang.String))")
    public void afterDeleteInstance(JoinPoint joinPoint) throws Throwable {
        User user = getCurrUser();
        Instance example = new Instance();
        example.setInstanceId((String) joinPoint.getArgs()[0]);
        Instance instance = instanceDao.findAllByExample(example).get(0);
        InstanceLogs instanceLogs = getCurrentLog(instance, user, UserAction.TERMINATE_INSTANCE);
        instanceLogsDao.save(instanceLogs);
    }

    private User getCurrUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails userDetails = (UserDetails) securityContext.getAuthentication().getPrincipal();
        String userName = userDetails.getUsername();
        return userDao.findByUsername(userName).orElse(null);
    }

    private InstanceLogs getCurrentLog(Instance instance, User user, UserAction userAction) {
        InstanceLogs instanceLogs = new InstanceLogs();
        instanceLogs.setInstance(instance);
        instanceLogs.setDateTime(LocalDateTime.now());
        instanceLogs.setActionMaker(user);
        instanceLogs.setAction(userAction);
        return instanceLogs;
    }
}