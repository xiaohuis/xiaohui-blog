package com.xiaohuis.controller.Exception;

import com.xiaohuis.constant.Code;
import com.xiaohuis.constant.Message;
import com.xiaohuis.constant.OtherCommonConstants;
import com.xiaohuis.exception.BusinessException;
import com.xiaohuis.exception.SystemException;
import com.xiaohuis.util.Result;
import com.xiaohuis.util.SendMailUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <p> 统一异常处理 </p>
 *
 * @author xiaohui
 * @description
 * @date 2022/12/1 23:05
 */

@ControllerAdvice
public class ProjectExceptionAdvice {

    /**
     * @ExceptionHandler用于设置当前处理器类对应的异常类型
     * @param ex
     * @return
     */
    @ExceptionHandler(SystemException.class)
    @ResponseBody
    public Result doSystemException(SystemException ex){
        //记录日志
        //发送消息给运维
        //发送邮件给开发人员,ex对象发送给开发人员
        SendMailUtil sendMailUtil = new SendMailUtil();
        sendMailUtil.sendMail("发送邮箱", OtherCommonConstants.MAIL_THEME_EXCEPTION, OtherCommonConstants.MAIL_CONTENT_EXCEPTION + ex);
        return new Result(ex.getCode(),null,ex.getMessage());
    }

    /**
     * BusinessException 业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result doBusinessException(BusinessException ex){
        return new Result(ex.getCode(),null,ex.getMessage());
    }


    /**
     * 处理AuthenticationException登录异常
     * @param ex
     * @return
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public Result authenticationEntryPoint(AuthenticationException ex) throws AuthenticationException {
        return new Result(Code.LOGIN_ERR_CODE,null, Message.Login_INFORMATION_ERR_MSG);
    }

    /**
     * 处理AccessDeineHandler无权限异常
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseBody
    public Result accessDeniedHandler(HttpServletRequest req, AccessDeniedException ex){
        return new Result(Code.SYSTEM_TIMEOUT_ERR,null,ex.getMessage());
    }


    /**
     * 除了自定义的异常处理器，保留对Exception类型的异常处理，用于处理非预期的异常
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String doOtherException(Exception ex){
        //记录日志
        //发送消息给运维
        //发送邮件给开发人员,ex对象发送给开发人员
        SendMailUtil sendMailUtil = new SendMailUtil();
        sendMailUtil.sendMail("发送邮箱", OtherCommonConstants.MAIL_THEME_EXCEPTION, OtherCommonConstants.MAIL_CONTENT_EXCEPTION + ex);
        return "error/500";
    }

}
