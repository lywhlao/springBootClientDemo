/**
 * 
 */
package com.example.demo.meta;

import lombok.Data;

/**
 * ajax请求返回结果封装
 * 
 * @author dhf
 */
@Data
public class AjaxResult  {

    private int code;

    private String msg = "";

    private Object content = null;


}
