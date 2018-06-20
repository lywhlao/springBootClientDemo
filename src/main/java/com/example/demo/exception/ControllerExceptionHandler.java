package com.example.demo.exception;

import com.example.demo.meta.AjaxResult;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(Throwable.class)
    @ResponseBody
    @SuppressWarnings("unchecked")
    public Object runtimeException(HttpServletRequest request,
                                   HttpServletResponse response, Throwable t) {
        String requestURI = request.getRequestURI();
        Map parameters = request.getParameterMap();

        // 删除passwd值
        Map parametersWithoutPasswd = new HashMap();
        parametersWithoutPasswd.putAll(parameters);
        parametersWithoutPasswd.remove("passwd");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uri", requestURI);
        map.put("parameters", parametersWithoutPasswd);


        return new AjaxResult();
    }


}