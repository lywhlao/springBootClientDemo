/**
 * 
 */
package com.example.demo.meta.constent;

/**
 * 返回的系统错误码定义
 * 
 * @author dhf
 */
public enum RetCode {

    /**
     * 成功.
     */
    SUCCESS(200, "SUCCESS"),
    
    /**
     * 未开始. 
     */
    NOT_START(202, "NOT START"),
    
    /**
     * 已下线.
     */
    ALREADY_OFFLINE(203, "ALREADY OFFILNE"),
    
    /**
     * 非法参数.
     */
    BAD_REQUEST(400, "BAD REQUEST"),
    
    /**
     * 未登录或uid为空.
     */
    NO_UID(401, "NO UID"),
    
    /**
     * 非网易邮箱账号.
     */
    NO_NTES_EMAIL(402, "NO NTES EMAIL"),
    
    /**
     * 禁用.
     */
    FORBIDDEN(403, "FORBIDDEN"),
    
    /**
     * 404.
     */
    NOT_FOUND(404, "NOT FOUND"),
    
    /**
     * 非内域账号.
     */
    NO_URS_EMAIL(405, "NO URS EMAIL"),

    /**
     * 验证吗错误.
     */
    ERROR_VERIFY_CODE(406, "ERROR_VERIFY_CODE"),
    /**
     * 服务器错误.
     */
    SERVER_ERROR(500, "SERVER ERROR"),
    
    /**
     * 服务器繁忙.
     */
    SERVER_BUSY(501, "SERVER BUSY"),
    
    /**
     * 邮箱大师新用户.
     */
    NEW_MAILMASER_USER(600, "NEW MAILMASTER USER"),
    
    /**
     * 非邮箱大师用户.
     */
    NOT_MAILMASTER_USER(601, "NOT MAILMASTER USER"),
    
    /**
     * 邮箱大师老用户.
     */
    OLD_MAILMASTER_USER(602, "OLD MAILMASTER USER"),
    
    /**
     * 网易有钱新用户.
     */
    NEW_YOUQIAN_USER(603, "NEW YOUQIAN USER"),
    
    /**
     * 非网易有钱用户.
     */
    NOT_YOUQIAN_USER(604, "NOT YOUQIAN USER"),
    
    /**
     * 网易有钱老用户.
     */
    OLD_YOUQIAN_USER(605, "OLD YOUQIAN USER"),

    /**
     * 已领取该商品优惠券
     */
    ALREADY_GET_THIS_COUPON(610,"ALREADY_GET_THIS_COUPON"),

    /**
     * 获取券码失败
     */
    GET_COUPON_ERROR(611,"GET_COUPON_ERROR"),

    /**
     * 未分享
     */
    NOT_SHARE(612,"NOT_SHARE"),

    /**
     * 已领取
     */
    ALREADY_GET_GIFT(613,"ALREADY_GET_GIFT"),


    /**
     * 没有足够库存
     */
    NO_ENOUGH_COUPON(614,"NO_ENOUGH_COUPON"),



    /**
     *
     */
    RATE_LIMIT(700,"rate limit");
    
    public final int code;

    public final String msg;

    private RetCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static RetCode valueOf(int code) {
        for (RetCode rc: RetCode.values()) {
            if (rc.code == code)
                return rc;
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(valueOf(606));
    }
    
}
