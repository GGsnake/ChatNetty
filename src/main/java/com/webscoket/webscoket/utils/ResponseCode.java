package com.webscoket.webscoket.utils;

/**
 * 系统返回码
 *
 * @author liujupeng 2019年5月1日 下午2:08:13
 */
public class ResponseCode {

    // 系统公用返回码
    public static final ResponseCode COMMON_SUCCESS = new ResponseCode("000000", "成功");
    public static final ResponseCode COMMON_SYSTEM_ERROR = new ResponseCode("000001", "系统异常");
    public static final ResponseCode WEBSOCKET_CONNECT_ERROR = new ResponseCode("100063", "网络已断开");
    // 业务公用返回码
    public static final ResponseCode COMMON_PARAMS_MISSING = new ResponseCode("100000", "请求参数不全");
    public static final ResponseCode COMMON_AUTHORITY_ERROR = new ResponseCode("100001", "无权限");
    public static final ResponseCode COMMON_USER_EXIST = new ResponseCode("100002", "用户已存在");
    public static final ResponseCode COMMON_USER_NOEXIST = new ResponseCode("100003", "用户不存在");
    public static final ResponseCode COMMON_PASSWORD_ERROR = new ResponseCode("100004", "密码错误");
    public static final ResponseCode SEND_MESSAGE_FAIL = new ResponseCode("100005", "消息发送失败 请重试");
    public static final ResponseCode CHAT_ADD_FAIL = new ResponseCode("100005", "日志添加失败 请重试");



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
