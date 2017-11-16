package person.zhy.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import person.zhy.model.ErrorLog;
import person.zhy.service.ErrorLogService;
import person.zhy.utils.base.IsExistException;
import person.zhy.utils.base.NoRecordException;
import person.zhy.utils.base.Result;
import person.zhy.utils.base.TokenException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Set;


@RestControllerAdvice
public class ExceptionHandlerController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @Autowired
    private ErrorLogService errorLogService;

    /**
     * 异常处理,记录异常日志
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exception(HttpServletRequest req, Exception e) {


        //用 getLocalHost() 方法创建的InetAddress的对象
        String serverIp = null;
        try {
            //获取IP地址
            InetAddress address = InetAddress.getLocalHost();
            serverIp = address.getHostAddress();
            logger.error("服务器Ip:" + serverIp);//获取IP地址
        } catch (UnknownHostException unHostEx) {
            logger.error("获取服务器Ip失败");
        }
        logger.error("---捕获 Exception---Host {} 请求mapping : {} Exception: {}", req.getRemoteHost(), req.getRequestURI(), e.toString());
        logger.error("User-Agent:" + req.getHeader("User-Agent"));
        ErrorLog entity=  ErrorLog.builder().ip(req.getRemoteHost()).uri(req.getRequestURI())
                                            .des(e.toString()).browser( req.getHeader("User-Agent"))
                                            .serverIp(serverIp).createTime(new Date()).build();
        errorLogService.save(entity);
        e.printStackTrace();

        Result result = new Result(e);
        result.setMessage("系统出错,请联系管理员");
        return result;
    }

    /**
     * 唯一性约束冲突 异常处理
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result DuplicateKey(HttpServletRequest req, Exception e) {

        logger.error("---唯一性约束 冲突 请求mapping : {} Exception: {}", req.getRemoteHost(), req.getRequestURI(), e.toString());
        logger.error("User-Agent:" + req.getHeader("User-Agent"));
        ErrorLog entity=  ErrorLog.builder().ip(req.getRemoteHost()).uri(req.getRequestURI()).des(e.toString()).browser( req.getHeader("User-Agent")).build();
        errorLogService.save(entity);

        Result result = new Result(e);
        result.setMessage("该记录已存在!");
        return result;
    }


    /**
     * 处理实体字段校验不通过异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleResourceNotFoundException(ConstraintViolationException e) {
        logger.debug("捕获参数校验异常");
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            strBuilder.append(violation.getMessage() + "\n");
        }
        logger.debug("参数错误:" + strBuilder.toString());
        return strBuilder.toString();
    }


    /**
     * 自定义 IsExistException-异常处理
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = IsExistException.class)
    public Result IsExistExceptionHandler(HttpServletRequest req, Exception e) {

        logger.error("---自定义异常 IsExistException 请求mapping : {} Exception: {}", req.getRemoteHost(), req.getRequestURI(), e.toString());
        logger.error("User-Agent:" + req.getHeader("User-Agent"));
       // ErrorLog entity=  ErrorLog.builder().ip(req.getRemoteHost()).uri(req.getRequestURI()).des(e.toString()).browser( req.getHeader("User-Agent")).build();
        //errorLogService.save(entity);

        Result result = new Result(e);
        result.setMessage(e.getMessage());
        return result;
    }
    /**
     * 自定义 IsExistException-异常处理
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NoRecordException.class)
    public Result NoRecordExceptionHandler(HttpServletRequest req, Exception e) {

        logger.error("---自定义异常 NoRecordException 请求mapping : {} Exception: {}", req.getRemoteHost(), req.getRequestURI(), e.toString());
        logger.error("User-Agent:" + req.getHeader("User-Agent"));
       // ErrorLog entity=  ErrorLog.builder().ip(req.getRemoteHost()).uri(req.getRequestURI()).des(e.toString()).browser( req.getHeader("User-Agent")).build();
        //errorLogService.save(entity);

        Result result = new Result(e);
        result.setMessage(e.getMessage());
        return result;
    }
    /**
     * 自定义 TokenException-异常处理
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = TokenException.class)
    public Result TokenExceptionHandler(HttpServletRequest req, Exception e) {

        logger.error("---自定义异常 TokenException 请求mapping : {} Exception: {}", req.getRemoteHost(), req.getRequestURI(), e.toString());
        logger.error("User-Agent:" + req.getHeader("User-Agent"));
       // ErrorLog entity=  ErrorLog.builder().ip(req.getRemoteHost()).uri(req.getRequestURI()).des(e.toString()).browser( req.getHeader("User-Agent")).build();
        //errorLogService.save(entity);

        Result result = new Result(e);
        result.setMessage(e.getMessage());
        return result;
    }

}
