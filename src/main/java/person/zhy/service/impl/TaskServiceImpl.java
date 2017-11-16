package person.zhy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import person.zhy.dao.TaskDao;
import person.zhy.model.Task;
import person.zhy.service.TaskService;
import person.zhy.utils.base.BaseDao;
import person.zhy.utils.base.impl.BaseServiceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "TaskService")
@Transactional
public class TaskServiceImpl extends BaseServiceImpl<Task, Long> implements TaskService {

    @Autowired
    private TaskDao taskDao;

    @Override
    protected BaseDao<Task, Long> getBaseDao() {
        return taskDao;
    }

    @Override
    public List<Task> findTaskListByUserId(Long userId) {

        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        param.put("status", Task.TASK_VALID);
        return this.getBaseDao().findList(param);
    }

    @Override
    public Task taskCreate(Long userId, String name, String des) {
        logger.debug("插入字段 userId:{},name:{},des:{}",userId,name,des);

        Date date = new Date();
        Task entity = Task.builder()
                .userId(userId)
                .name(name)
                .des(des)
                .status(Task.TASK_VALID)
                .createTime(date).updateTime(date)
                .build();

        this.getBaseDao().save(entity);
        return entity;
    }

    @Override
    public Task update(Long id, String name, String des) {
        logger.debug("更新记录 id:{},name:{},des:{}",id,name,des);
        Date date = new Date();
        Task entity = Task.builder()
                .id(id)
                .name(name)
                .des(des)
                .status(Task.TASK_VALID)
                .updateTime(date)
                .build();

        this.updateById(entity);
        return  entity;
    }

    @Override
    public Task finish(Long id) {
        logger.debug("结束记录 id:{}",id);
        Date date = new Date();
        Task entity = Task.builder()
                .id(id)
                .status(Task.TASK_INVALID)
                .updateTime(date)
                .build();

        this.updateById(entity);
        return  entity;
    }
}
