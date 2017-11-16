package person.zhy.service;

import person.zhy.model.User;
import person.zhy.utils.base.BaseService;

public interface UserService extends BaseService<User,Long> {

   /**
    * 用户注册
    * @param account 帐户
    * @param password 密码
    * @return
    */
   User singIn(String account,String password);

   /**
    * 用户登陆
    * @param account 账户
    * @param password 密码
    * @return
    */
   String singUp(String account,String password);

   boolean safeCheck(String account);

    String tokenBuild(User user);

   /**
    * 校验帐户名是否存在
    * @param account 帐户名
    * @return true-存在 false-不存在
    */
   boolean isExist(String account);

}
