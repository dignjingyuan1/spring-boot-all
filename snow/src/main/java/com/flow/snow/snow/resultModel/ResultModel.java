package com.flow.snow.snow.resultModel;

import lombok.Data;

@Data
public class ResultModel {
    private int code;
    private String msg;
    private Object result;

    public ResultModel(ResultMsgEnum resultMsgEnum, Object result) {
        this.code = resultMsgEnum.getCode();
        this.msg = resultMsgEnum.getMsg();
        this.result = result;
    }
}
