package person.zhy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import person.zhy.dao.ErrorLogDao;
import person.zhy.model.ErrorLog;
import person.zhy.service.ErrorLogService;
import person.zhy.utils.base.BaseDao;
import person.zhy.utils.base.impl.BaseServiceImpl;

@Service(value ="ErrorLogService" )
@Transactional
public class ErrorLogServiceImpl extends BaseServiceImpl<ErrorLog,Long> implements ErrorLogService {

    @Autowired
    private ErrorLogDao errorLogDao;

    @Override
    protected BaseDao<ErrorLog, Long> getBaseDao() {
        return errorLogDao;
    }



}
