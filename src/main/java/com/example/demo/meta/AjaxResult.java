/**
 *
 */
package com.example.demo.meta;

import com.example.demo.meta.constent.RetCode;
import lombok.Data;

/**
 * ajax请求返回结果封装
 *
 * @author dhf
 */
@Data
public class AjaxResult {

    private int code;

    private String msg = "";

    private Object content = null;

    public AjaxResult() {
    }


    public AjaxResult(int code) {
        this.code = code;
        RetCode rc = RetCode.valueOf(code);
        if (rc != null)
            this.msg = rc.msg;
    }

    public AjaxResult(int code, Object content) {
        this(code);
        this.content = content;
    }

    public AjaxResult(int code, String msg, Object content) {
        this(code);
        this.msg = msg;
        this.content = content;
    }

    public AjaxResult(RetCode retCode, Object... contentArray) {
        this.code = retCode.code;
        this.msg = retCode.msg;
        if (contentArray.length > 0) {
            this.content = contentArray[0];
        }
    }
}
