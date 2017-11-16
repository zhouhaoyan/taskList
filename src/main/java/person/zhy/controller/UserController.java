package person.zhy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import person.zhy.service.UserService;
import person.zhy.utils.base.Result;

@RestController
@Api(description = "用户接口")
@Validated
public class UserController {

    @Autowired
    private UserService userService;
    @ApiOperation(value = "用户注册", notes = "创建创建用户")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "account", value = "账户", paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", dataType = "String", required = true)
    })
    @PostMapping("singin")
    public Result singIn(@RequestParam(value = "account") String account,
                         @RequestParam(value = "password") String password
                         ){

        return  new Result(userService.singIn(account,password));
    }

    @ApiOperation(value = "用户登录", notes = "登陆")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "account", value = "账户", paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", dataType = "String", required = true)
    })
    @PostMapping(value = "singup")
    public Result singUp(@RequestParam(value = "account") String account,
                         @RequestParam(value = "password") String password){


        return new Result(userService.singUp(account,password));
    }
}
