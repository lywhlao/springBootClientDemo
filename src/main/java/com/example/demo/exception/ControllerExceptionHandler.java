package com.example.demo.exception;

import com.example.demo.meta.AjaxResult;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    @SuppressWarnings("unchecked")
    public Object runtimeException(HttpServletRequest request,
                                   HttpServletResponse response, Exception t) {

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


    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    @SuppressWarnings("unchecked")
    public Object nohandler(HttpServletRequest request,
                                   HttpServletResponse response, NoHandlerFoundException t) {

        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(404);
        return ajaxResult;
    }

}