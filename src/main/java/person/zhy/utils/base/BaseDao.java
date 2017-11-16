package person.zhy.utils.base;

import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<T, P extends Serializable>
{
  T getById(P paramP)
          throws DataAccessException;

  void deleteById(P paramP)
          throws DataAccessException;

  void deleteAllByIds(List<P> paramList)
          throws DataAccessException;

  void save(T paramT)
          throws DataAccessException;

  void saveBatch(List<T> paramList)
          throws DataAccessException;

  int updateById(T paramT)
          throws DataAccessException;

  int findCount(Map<String, Object> paramMap)
          throws DataAccessException;

  List<T> findAll()
          throws DataAccessException;

  List<T> findList(Map<String, Object> paramMap);
  
}