package com.jiesz.writinghome.handle;


import com.jiesz.writinghome.common.bean.Result;
import com.jiesz.writinghome.common.enums.ResultCode;
import com.jiesz.writinghome.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.util.List;
import java.util.Set;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理参数校验异常，dto内注解校验的字段
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<?> argExceptionHandler(MethodArgumentNotValidException e) {
        logger.error("发生参数校验异常！原因是:", e);
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        if (!fieldErrorList.isEmpty()) {
            return Result.fail(ResultCode.PARAM_VALID_ERROR.getCode(), fieldErrorList.get(0).getDefaultMessage());
        }
        return Result.fail(ResultCode.PARAM_VALID_ERROR);
    }

    /**
     * 处理参数校验异常，dto内注解校验的字段
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(value = UnexpectedTypeException.class)
    public Result<?> argExceptionHandler(UnexpectedTypeException e) {
        logger.error("发生参数校验异常！原因是:", e);
        return Result.fail(ResultCode.PARAM_VALID_ERROR);
    }

    /**
     * 处理参数校验异常，get请求参数注解校验的字段
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result<?> validationExceptionHandler(ConstraintViolationException e) {
        logger.error("发生参数校验异常！原因是:", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        if (!violations.isEmpty()) {
            return Result.fail(ResultCode.PARAM_VALID_ERROR.getCode(), violations.iterator().next().getMessage());
        }
        return Result.fail(ResultCode.PARAM_VALID_ERROR);
    }

    /**
     * 处理参数校验异常, json转换错误
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public Result<?> validationExceptionHandler(HttpMessageNotReadableException e) {
        logger.error("发生参数校验异常！原因是:json转换错误", e);
        return Result.fail(ResultCode.PARAM_VALID_ERROR);
    }

    /**
     * 缺失参数异常
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Result<?> missArgExceptionHandler(MissingServletRequestParameterException e) {
        logger.error("发生参数缺失异常！原因是:", e);
        return Result.fail(ResultCode.PARAM_NOT_COMPLETE_ERROR);
    }

    /**
     * 请求方法不支持
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Result<?> methodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        logger.error("请求方法不支持！原因是:", e);
        return Result.fail(ResultCode.REQUEST_METHOD_NOT_SUPPORT);
    }

    /**
     * 处理自定义的业务异常
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(value = BizException.class)
    public Result<?> bizExceptionHandler(BizException e) {
        logger.error("发生业务异常！原因是：{}", e.getErrorMsg());
        return Result.fail(Integer.parseInt(e.getErrorCode()), e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(value = NullPointerException.class)
    public Result<?> exceptionHandler(NullPointerException e) {
        logger.error("发生空指针异常！原因是:", e);
        return Result.fail(ResultCode.SYSTEM_INNER_ERROR);
    }

    /**
     * 处理其他异常
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(value = Exception.class)
    public Result<?> exceptionHandler(Exception e) {
        logger.error("未知异常！原因是:", e);
        return Result.fail(ResultCode.SYSTEM_INNER_ERROR);
    }
}