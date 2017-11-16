package person.zhy.service;

import person.zhy.model.Task;

import java.util.List;

public interface TaskService {

    /**
     * 查看用户 待完成的任务
     * @param userId 用户Id
     * @return
     */
   List<Task>  findTaskListByUserId(Long userId);

    /**
     * 创建任务
     * @param userId 用户Id
     * @param name 任务名称
     * @param des 任务内容
     * @return
     */
    Task taskCreate(Long userId, String name, String des);

    /**
     * 更新任务
     * @param id 任务Id
     * @param name 任务名称
     * @param des 任务内容
     * @return
     */
    Task update(Long id,String name,String des);

    /**
     * 完成任务
     * @param id 任务ID
     * @return
     */
    Task finish(Long id);



}
