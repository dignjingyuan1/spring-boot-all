package com.flow.snow.snow.resultModel;


public enum ResultMsgEnum {
    SUCCESS(200,"ok"),
    LOGIN_ERROR(1001, "登录失败！"),
    REG_ERROR(1002, "注册失败！");

    private final int code;
    private final String msg;

    ResultMsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
