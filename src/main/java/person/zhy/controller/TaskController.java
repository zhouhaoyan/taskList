package person.zhy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import person.zhy.service.TaskService;
import person.zhy.utils.base.Result;
import person.zhy.utils.base.TokenException;
import person.zhy.utils.jwt.Jwt;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Map;

@RestController
@RequestMapping(value = "login")
@Api(description = "任务清单管理接口")
@Validated
public class TaskController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskService taskService;

    /**
     * 创建任务
     * @param token
     * @param des
     * @return
     */
    @ApiOperation(value = "创建任务", notes = "创建任务条目")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "token", value = "用户token", paramType = "query", dataType = "String",
                    defaultValue = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOjE1MTA2NTI3MjIwNDEsInVpZCI6IjEiLCJpYXQiOjE1MTA2NDU1MjIwNDF9.idr41i5YPbsQ7qk7BW46PKsjvwrPXihyK0hoVeiu9aM",
                    required = true),
            @ApiImplicitParam(name = "des", value = "任务内容", paramType = "query", dataType = "String", required = true)
    })
    @PostMapping(value = "task")
    public Result createTask(@RequestParam(value = "token") @NotNull(message = "token为必输") String token,
                             @RequestParam(value = "des") @NotNull(message = "任务内容为必输") String des) {
        logger.info("接收参数:token={},des={}",token,des);
        Long userId=getUserIdByToken(token);
        return new Result(taskService.taskCreate(userId,null,des));
    }


    /**
     * 更新任务
     * @param token
     * @param id
     * @param des
     * @return
     */
    @ApiOperation(value = "更新任务", notes = "更新对应ID 的任务内容")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "token", value = "用户token", paramType = "query", dataType = "String",
                    defaultValue = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOjE1MTA2NTI3MjIwNDEsInVpZCI6IjEiLCJpYXQiOjE1MTA2NDU1MjIwNDF9.idr41i5YPbsQ7qk7BW46PKsjvwrPXihyK0hoVeiu9aM",
                    required = true),
            @ApiImplicitParam(name = "id", value = "任务ID", paramType = "query", dataType = "Long", required = true),
            @ApiImplicitParam(name = "des", value = "任务内容", paramType = "query", dataType = "String", required = true)
    })
    @PutMapping(value = "task")
    public Result updateTask(@RequestParam(value = "token") @NotNull(message = "token为必输") String token,
                             @RequestParam(value = "id") @NotNull(message = "任务ID不能为空")
                                     @Min(value = 1,message = "任务ID 必须为正整数") Long id,
                             @RequestParam(value = "des") @NotNull(message = "任务内容为必输") String des) {
        logger.info("接收参数:token={},id={},des={}",token,id,des);

        return new Result(taskService.update(id,null,des));
    }

    /**
     * 删除任务
     * @param token
     * @param id
     * @return
     */
    @ApiOperation(value = "删除任务", notes = "删除对应ID 的任务")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "token", value = "用户token", paramType = "query", dataType = "String",
                    defaultValue = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOjE1MTA2NTI3MjIwNDEsInVpZCI6IjEiLCJpYXQiOjE1MTA2NDU1MjIwNDF9.idr41i5YPbsQ7qk7BW46PKsjvwrPXihyK0hoVeiu9aM",
                    required = true),
            @ApiImplicitParam(name = "id", value = "任务ID", paramType = "query", dataType = "Long", required = true),
    })
    @DeleteMapping(value = "task")
    public Result createTask(@RequestParam(value = "token") @NotNull(message = "token为必输") String token,
                             @RequestParam(value = "id") @NotNull(message = "任务ID不能为空")
                             @Min(value = 1,message = "任务ID 必须为正整数") Long id) {
        logger.info("接收参数:token={},={}",token,id);

        return new Result(taskService.finish(id));
    }
    @ApiOperation(value = "获取用户任务", notes = "查询用户待完成任务")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "token", value = "用户token", paramType = "query", dataType = "String",
                    defaultValue = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOjE1MTA2NTI3MjIwNDEsInVpZCI6IjEiLCJpYXQiOjE1MTA2NDU1MjIwNDF9.idr41i5YPbsQ7qk7BW46PKsjvwrPXihyK0hoVeiu9aM",
                    required = true)
    })
    @GetMapping(value = "task")
    public Result findTask(@RequestParam(value = "token") @NotNull(message = "token为必输") String token
    ) {
        logger.info("接收参数:token={},={}",token);
        Long userId=this.getUserIdByToken(token);
        return new Result(taskService.findTaskListByUserId(userId));
    }


    /**
     * 通过token 获取用户ID
     * @param token
     * @return
     */
    private Long getUserIdByToken(String token){
        if(token==null){
            throw new TokenException("错误内容:token == null");
        }
        logger.info("TOKEN内容:{}",Jwt.validToken(token).toString());
        Map map= (Map) Jwt.validToken(token).get("data");
        return (Long) map.get("uid");
    }

}
