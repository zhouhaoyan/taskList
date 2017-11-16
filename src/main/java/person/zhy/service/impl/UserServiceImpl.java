package person.zhy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import person.zhy.dao.UserDao;
import person.zhy.model.User;
import person.zhy.service.UserService;
import person.zhy.utils.MD5;
import person.zhy.utils.base.BaseDao;
import person.zhy.utils.base.IsExistException;
import person.zhy.utils.base.NoRecordException;
import person.zhy.utils.base.impl.BaseServiceImpl;
import person.zhy.utils.jwt.Jwt;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "UserService")
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {


    @Autowired
    private UserDao userDao;

    @Override
    protected BaseDao<User, Long> getBaseDao() {
        return userDao;
    }

    @Override
    public User singIn(@NotNull String account, @NotNull String password) {
        if (this.isExist(account)) throw new IsExistException("当前账户已经被使用");
        password= MD5.crypt(password);
        User entity = User.builder().account(account).password(password).createTime(new Date()).status(User.USER_VALID).build();
        this.getBaseDao().save(entity);
        entity.setPassword(null);
        return entity;
    }

    @Override
    public String singUp(@NotNull String account, @NotNull String password) {

        if (!isExist(account)) throw new NoRecordException("该用户不存在");
        password=MD5.crypt(password);
        Map<String, Object> param = new HashMap<>();
        param.putIfAbsent("account", account);
        param.putIfAbsent("password", password);
        User u = userDao.findUserByAccAndPwd(param);

        if (u == null) {
            throw new NoRecordException("账号或密码错误.");
        }
        u.setPassword(null);
        //生成 TOKEN
        String token=tokenBuild(u);
        super.logger.info("token:"+token);
        return token;
    }

    @Override
    public boolean safeCheck(String account) {
        return false;
    }

    @Override
    public String tokenBuild(User user) {
        String token = null;
        Map<String, Object> payload = new HashMap<String, Object>();
        Date date = new Date();
        payload.put("uid", user.getId());// 用户id
        payload.put("account", user.getAccount()); // 用户名称
        payload.put("iat", date.getTime());// 生成时间:当前
        payload.put("ext", date.getTime() + 2000 * 60 * 60);// 过期时间2小时
        token = Jwt.createToken(payload);
        return token;
    }

    @Override
    public boolean isExist(String account) {
        Map<String, Object> param = new HashMap<>();
        param.putIfAbsent("account", account);
        List<User> users = this.getBaseDao().findList(param);
        return users.size() > 0;
    }


}
