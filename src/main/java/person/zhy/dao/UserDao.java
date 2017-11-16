package person.zhy.dao;

import person.zhy.model.User;
import person.zhy.utils.base.BaseDao;

import java.util.Map;

public interface UserDao  extends BaseDao<User,Long> {

    User findUserByAccAndPwd(Map<String,Object> param);

}