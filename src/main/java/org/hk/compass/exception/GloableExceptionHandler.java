package org.hk.compass.exception;

import lombok.extern.slf4j.Slf4j;
import org.hk.compass.util.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zengry
 * @description 全局异常处理
 * @since 2019/12/27
 */

@Slf4j
@RestControllerAdvice
public class GloableExceptionHandler {
    /**
     * 处理自定义异常
     */
    @ExceptionHandler(CommonException.class)
    public R handleRRException(CommonException e){
        R r = new R();
        r.put("code", e.getCode());
        r.put("msg", e.getMessage());
        return r;
    }
}
