package com.ght.learn.ffs.service.task;

import java.util.List;

import com.ght.learn.ffs.entity.sys.Company;
import com.ght.learn.ffs.entity.task.Task;
import com.ght.learn.ffs.enums.task.TaskType;
import com.ght.learn.ffs.service.FfsService;
import com.ght.learn.ffs.tool.context.SessionUser;

import framework.service.ServiceException;

public interface TaskService extends FfsService {
    
    /**
     * 查询我的任务里相关taskType对应的客户或者订单Id
     * @param taskType
     * @param me
     * @return
     */
    List<Long> queryMyCorrelatedIdsByTaskType(TaskType taskType, SessionUser me);
    
    /**
     * 查询我的任务
     * @param taskType
     * @param correlatedId
     * @param me
     * @return
     * @throws ServiceException
     */
    Task queryMyTaskByTaskTypeCorrelatedId(TaskType taskType, Long correlatedId, SessionUser me) throws ServiceException;
    /**
     * 删除任务
     * @param taskType
     * @param correlatedId
     * @param operator
     * @throws ServiceException
     */
    void doDeleteTasks(TaskType taskType, Long correlatedId, SessionUser operator) throws ServiceException;
    Long doCreateTask(Task task, Company belongToCompany) throws ServiceException;
    Long doCreateTask(Task task, SessionUser operator) throws ServiceException;
    void doChangeMyTaskToOther(TaskType taskType, Long correlatedId, Long toUserId, Long toRoleId, SessionUser me) throws ServiceException;
    
//    /**
//     * 提交任务
//     * @param taskType
//     * @param correlatedId
//     * @param operator
//     * @param variables
//     * @return
//     * @throws ServiceException
//     */
//    Long doSubmitTask(TaskType taskType, Long correlatedId, SessionUser operator, Map<String, Object> variables) throws ServiceException;
//    
//    /**
//     * 对任务进行action操作
//     * @param action
//     * @param taskType
//     * @param correlatedId
//     * @param operator
//     * @param variables
//     * @throws ServiceException
//     * @return 该任务是否执行完毕。 true-任务结束
//     */
//    boolean doActionTask(String action, TaskType taskType, Long correlatedId, SessionUser operator, Map<String, Object> variables) throws ServiceException;
}