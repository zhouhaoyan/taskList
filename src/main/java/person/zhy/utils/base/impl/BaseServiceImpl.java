package person.zhy.utils.base.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import person.zhy.utils.base.PageBean;
import person.zhy.utils.base.BaseDao;
import person.zhy.utils.base.BaseService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Transactional
public abstract class BaseServiceImpl<T, P extends Serializable>
        implements BaseService<T, P>
{

    public Logger logger= LoggerFactory.getLogger(this.getClass());

    protected abstract BaseDao<T, P> getBaseDao();

    @Transactional(readOnly=true)
    public T getById(P id)
            throws DataAccessException
    {
        if (id == null) {
            return null;
        }
        return getBaseDao().getById(id);
    }

    @Transactional(readOnly=true)
    public List<T> findAll() throws DataAccessException {
        return getBaseDao().findAll();
    }

    public List<T> findList(Map<String, Object> map) throws DataAccessException {
        if (map == null) {
            return null;
        }
        return getBaseDao().findList(map);
    }

    public PageBean<T> findPage(Map<String, Object> map) throws DataAccessException {
        if (map == null) {
            return null;
        }
        //当前页
//        int pageNum = Integer.valueOf(map.get("pageNum"));
        //每页的数量
//        int pageSize = (int) map.get("pageSize");
//        PageHelper.startPage(pageNum, pageSize);
        List<T> list = getBaseDao().findList(map);
        PageBean<T> page = new PageBean<T>(list);
        return page;
    }

    public int findCount(Map<String, Object> map) throws DataAccessException {
        if (map == null) {
            return 0;
        }
        return getBaseDao().findCount(map);
    }

    public void save(T entity) throws DataAccessException {
    	getBaseDao().save(entity);
    }

    public void saveBatch(List<T> list) throws DataAccessException {
    	getBaseDao().saveBatch(list);
    }

    public void deleteById(P id) throws DataAccessException {
        if (id == null) {
            return;
        }
        getBaseDao().deleteById(id);
    }

    public void deleteAllByIds(List<P> ids) throws DataAccessException {
        if (ids == null) {
            return;
        }
        getBaseDao().deleteAllByIds(ids);
    }

    public void updateById(T entity) throws DataAccessException {
        if (entity == null) {
            return;
        }
        getBaseDao().updateById(entity);
    }
}