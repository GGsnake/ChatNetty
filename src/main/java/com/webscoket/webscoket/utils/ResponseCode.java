package com.webscoket.webscoket.utils;

/**
 * 系统返回码
 *
 * @author liujupeng 2015年9月8日 下午2:08:13
 */
public class ResponseCode {

    // 系统公用返回码
    public static final ResponseCode COMMON_SUCCESS = new ResponseCode("000000", "成功");
    public static final ResponseCode COMMON_SYSTEM_ERROR = new ResponseCode("000001", "系统异常");

    // 业务公用返回码
    public static final ResponseCode COMMON_PARAMS_MISSING = new ResponseCode("100000", "请求参数不全");
    public static final ResponseCode COMMON_AUTHORITY_ERROR = new ResponseCode("100001", "无权限");
    public static final ResponseCode COMMON_USER_EXIST = new ResponseCode("100002", "用户已存在");
    public static final ResponseCode COMMON_USER_NOEXIST = new ResponseCode("100003", "用户不存在");
    public static final ResponseCode COMMON_PASSWORD_ERROR = new ResponseCode("100004", "密码错误");
    private String errCode;
    private String errMsg;

    public ResponseCode(String errCode, String errMsg) {
        super();
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    @Override
    public String toString() {
        return "[errCode=" + errCode + ", errMsg=" + errMsg + "]";
    }

}
